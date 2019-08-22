package com.cwca.service.examination;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cwca.bean.User;
import com.cwca.bean.common.RespBean;
import com.cwca.bean.examination.ApplyFileInfo;
import com.cwca.bean.examination.ApplyInfo;
import com.cwca.bean.examination.DcUser;
import com.cwca.bean.examination.Express;
import com.cwca.constant.Constants;
import com.cwca.enums.ExamEnum;
import com.cwca.enums.MyEnum;
import com.cwca.enums.ResultEnum;
import com.cwca.exception.MyException;
import com.cwca.exception.TemplateException;
import com.cwca.mapper.UserMapper;
import com.cwca.mapper.examination.ApplyFileInfoMapper;
import com.cwca.mapper.examination.ApplyInfoMapper;
import com.cwca.mapper.examination.DcUserMapper;
import com.cwca.mapper.examination.ExpressMapper;
import com.cwca.service.PayInvoiceExpressServ;
import com.cwca.utils.ResultVoUtil;
import com.cwca.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ExamDataService {
    @Autowired
    ApplyInfoMapper applyInfoMapper;

    @Autowired
    ExpressMapper expressMapper;

    @Autowired
    ApplyFileInfoMapper applyFileInfoMapper;

    @Autowired
    private DcUserMapper dcUserMapper;

    @Autowired
    private PayInvoiceExpressServ payInvoiceExpressServ;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ExpressServer expressServer;

    @Value("${exam.printUrl}")
    private String printUrl;

    @Value("${file.staticAccessPath}")
    private String staticAccessPath;

    @Value("${invoice.findOneInvoiceUrl}")
    private String invoiceurl;

    //依据条件获取申请信息
    public List<DataResponseVo> adminFindAllData(ApplyInfo applyInfo) {

        List<DataResponseVo> applyResponseVoList = new ArrayList<>();
        //依据条件获取所有的上传信息
        List<ApplyInfo> applyInfoList=applyInfoMapper.findAllApplyInfo(applyInfo);

        if(applyInfoList!=null&& applyInfoList.size()>0){
            //获取所有管理员信息
            List<User> users=userMapper.getAllHr();
            Map<Integer,String> adminusermap=new HashMap<>();
            for (User user:users){
                adminusermap.put(user.getId(),user.getName());
            }
            //查询快递单号
            Map<String, String> expressmap=expressServer.findSFOrderId(applyInfoList.stream().map(ApplyInfo::getApplyId).collect(Collectors.toList()));

            //依据快递id数组获取所有的快递信息
            List<Express> expressList = expressMapper.getExpressListByIds(applyIdMap(applyInfoList));
            //获取邮寄类型对应关系
            Map<String, Express> typemap = expressListToMap(expressList, applyInfoList);

            //查询支付状态
            Map<String, String> map = checkPayState(applyInfoList);

            //依据用户id数组获取用户姓名
            List<DcUser> dcUsers=dcUserMapper.getDcUsers(applyInfoList.stream().map(ApplyInfo::getUserId).collect(Collectors.toList()));
            Map<Integer,String> userMap=new HashMap<>();
            for (DcUser dcUser:dcUsers){
                userMap.put(dcUser.getUserId(),dcUser.getUsername());
            }

            for (ApplyInfo applyInfotemp : applyInfoList) {
                String expresstypetemp="";
                if (typemap.get(applyInfotemp.getApplyId())!=null){
                    expresstypetemp=typemap.get(applyInfotemp.getApplyId()).getType().trim();
                }
                String paytypetemp=map.get(applyInfotemp.getApplyId()).trim();
                //过滤支付状态
                if (applyInfo.getPayType()!=null&&!applyInfo.getPayType().equals("") ){
                    String payType=applyInfo.getPayType().trim();//支付参数
                    if (!(paytypetemp.equals(payType))){
                        continue;
                    }
                }
                //过滤邮寄类型
               if (applyInfo.getExpressType()!=null&&!applyInfo.getExpressType().equals("")){
                   String expressType=applyInfo.getExpressType().trim();//邮寄参数
                   if (!(expresstypetemp.equals(expressType))){
                       continue;
                   }
               }

                //过滤用户名
                if (applyInfo.getUsername()!=null&&!applyInfo.getUsername().equals("") ){
                    String username=applyInfo.getUsername().trim();//用户参数
                    String tempusername=userMap.get(applyInfotemp.getUserId()).trim();
                    if (!(tempusername.equals(username))){
                        continue;
                    }
                }

                DataResponseVo dataResponseVo = new DataResponseVo();
                BeanUtils.copyProperties(applyInfotemp, dataResponseVo, Constants.excApplyCopyProperties);

                dataResponseVo.setCertificateType(Arrays.asList(StringUtils.split(applyInfotemp.getCertificateType(),",")));
                dataResponseVo.setPayState(map.get(applyInfotemp.getApplyId()));//设置支付类型
                dataResponseVo.setType(expresstypetemp);//设置邮寄类型
                dataResponseVo.setApplyUserName(userMap.get(applyInfotemp.getUserId()).trim());//设置申请人姓名
                dataResponseVo.setAdminusername(adminusermap.get(applyInfotemp.getAdminUserId()));//设置审核人姓名
                if (typemap.get(applyInfotemp.getApplyId())!=null){
                    dataResponseVo.setQzrIdCard(typemap.get(applyInfotemp.getApplyId()).getQzrIdCard());
                    dataResponseVo.setQzrName(typemap.get(applyInfotemp.getApplyId()).getQzrName());
                    dataResponseVo.setQzrPhone(typemap.get(applyInfotemp.getApplyId()).getQzrPhone());
                    dataResponseVo.setQzAddress(typemap.get(applyInfotemp.getApplyId()).getQzAddress());
                }
                String expressid=expressmap.get(applyInfotemp.getApplyId());//快递单号
                if (expressid==null && ! expressid.equals("")){
                    dataResponseVo.setSendExpressStatus("未发货");
                }
                else{
                    dataResponseVo.setSendExpressStatus(expressid);
                }
                applyResponseVoList.add(doCertificate(dataResponseVo, applyInfotemp));
            }
        }

        return applyResponseVoList;
    }


    //依据apply_id获取详细文件信息(单个详情)
    public DataResponseVo getApplyInfoDetail(String applyId, HttpServletRequest request) {
        ApplyInfo applyInfo = applyInfoMapper.getApplyInfo(applyId);
        List<ApplyFileInfo> applyFileInfoList = applyFileInfoMapper.getApplyFileInfoByApplyid(applyInfo);
        List<UpFile> upFileList = new ArrayList<>();
        DataResponseVo dataResponseVo = new DataResponseVo();

        BeanUtils.copyProperties(applyInfo, dataResponseVo, Constants.excApplyCopyProperties);
        dataResponseVo.setCertificateType(Arrays.asList(StringUtils.split(applyInfo.getCertificateType())));
        for (ApplyFileInfo applyFileInfo : applyFileInfoList) {
            UpFile upFile = new UpFile();
            BeanUtils.copyProperties(applyFileInfo, upFile);
            upFile.setFileUrl(staticAccessPath, upFile.getFileUrl(), request);
            upFileList.add(upFile);
        }
        dataResponseVo.setFiles(upFileList);
        return dataResponseVo;
    }


    /* 审核单个证件*/
    @Transactional
    public RespBean passOne(String fileInfoId, Boolean isPass, String node) {
        //依据文件id获取文件记录内容
        Map<String,String> parammap=new HashMap<>();
        parammap.put("fileInfoId",fileInfoId);
        ApplyFileInfo applyFileInfo = applyFileInfoMapper.getApplyFileInfoByFileId(parammap);
        if (isPass) {
            applyFileInfo.setIsPass(true);
        } else {
            applyFileInfo.setIsPass(false);
        }
        applyFileInfo.setNote(node);
        applyFileInfoMapper.updateFileInfo(applyFileInfo);//更新单个证件
        return new RespBean(ResultEnum.SUCCESS);
    }

    /* 审核整条申请记录*/
    @Transactional
    public RespBean passApply(ExamRequestVo examRequestVo,User user) {
        ApplyInfo applyInfo = applyInfoMapper.getApplyInfo(examRequestVo.getApplyId());
        /* 查询申请记录是否审核通过*/
        if (applyInfo.getIsPass() == ExamEnum.PASS.getCode()) {
            return new RespBean(ResultEnum.HASPASS_ERROR);
        }
        List<ApplyFileInfo> applyFileInfoList = applyFileInfoMapper.getApplyFileInfoByApplyid(applyInfo);
        for (ApplyFileInfo applyFileInfo : applyFileInfoList) {
            for (Map<String, String> map : examRequestVo.getList()) {
                String isPass=String.valueOf(map.get("isPass"));
                if (applyFileInfo.getFileInfoId().equals(map.getOrDefault("fileInfoId", null))) {
                    Map<String,String> parammap=new HashMap<>();
                    parammap.put("fileInfoId",applyFileInfo.getFileInfoId());
                    ApplyFileInfo byFileInfoId = applyFileInfoMapper.getApplyFileInfoByFileId(parammap);
                    byFileInfoId.setNote(map.getOrDefault("note", null));
                    byFileInfoId.setIsPass(Boolean.valueOf(isPass));
                    applyFileInfoMapper.updateFileInfo(byFileInfoId);
                }
            }
        }
        int passtatus=applyIsPass(examRequestVo);
        applyInfo.setIsPass(passtatus);
        applyInfo.setAdminUserId(user.getId());
//        applyInfo.setUpdateTime(new Date());
        applyInfoMapper.updateInfo(applyInfo);
        //未通过
        if (passtatus==3){
            return new RespBean(ResultEnum.PASS_ERROR);

        }
        //审核通过
        else {
            return new RespBean(ResultEnum.PASS_SUCCESS);

        }
    }

    /**
     * 初审
     * @param examRequestVo
     * @return
     */
    @Transactional
    public RespBean transfer(ExamRequestVo examRequestVo,User user) {
        ApplyInfo applyInfo = applyInfoMapper.getApplyInfo(examRequestVo.getApplyId());
        List<ApplyFileInfo> byFileDetailId = applyFileInfoMapper.getApplyFileInfoByApplyid(applyInfo);
        //已经通过
        if (applyInfo.getIsPass() != null && applyInfo.getIsPass() == ExamEnum.PASS.getCode()) {
//            return ResultVoUtil.error(-1, "初审已通过,不能移交至" + ExamEnum.FINALLY.getMsg());
            return new RespBean(ResultEnum.FIRST_ERROR);
        }
        for (ApplyFileInfo applyFileInfo : byFileDetailId) {
            for (Map<String, String> map : examRequestVo.getList()) {
                if (applyFileInfo.getFileInfoId().equals(map.getOrDefault("fileInfoId", null))) {
                    String isPass=String.valueOf(map.get("isPass"));
                    if (!Boolean.valueOf(isPass)) {
                        throw new TemplateException(-1,"资料审核未完成,不能移交至国土");
                    }
                    Map<String,String> parammap=new HashMap<>();
                    parammap.put("fileInfoId",applyFileInfo.getFileInfoId());
                    ApplyFileInfo byFileInfoId = applyFileInfoMapper.getApplyFileInfoByFileId(parammap);
                    byFileInfoId.setNote(map.getOrDefault("note", null));
                    byFileInfoId.setIsPass(Boolean.valueOf(isPass));
                    applyFileInfoMapper.updateFileInfo(byFileInfoId);
                }
            }
        }

        applyInfo.setAuditDept(ExamEnum.FINALLY.getCode());
        applyInfo.setAdminUserId(user.getId());
        applyInfoMapper.updateInfo(applyInfo);
//        User user = userDao.findByUserId(save.getUserId());
//        webSocket.sendMessage(user.getUsername());
        return new RespBean(ResultEnum.FIRST_SUCCESS);
    }

    /**
     * 更新applyinfo
     * @param applyId
     * @return
     */
    public RespBean updateApplyInfo(String applyId) {
        ApplyInfo applyInfo = new ApplyInfo();
        applyInfo.setApplyId(applyId);
        applyInfo.setSelf_fetch(true);
        int status=applyInfoMapper.updateInfo(applyInfo);
        if (status == 1){
            return new RespBean(ResultEnum.SUCCESS);
        }
        else {
            return new RespBean(ResultEnum.ERROR);
        }
    }

    /**
     * 逻辑删除applyinfo
     * @param applyId
     * @return
     */
    public RespBean deleteApplyInfo(String applyId,String delete_comment,int id) {
        ApplyInfo applyInfo = new ApplyInfo();
        applyInfo.setApplyId(applyId);
        applyInfo.setDelete_comment(delete_comment);
        applyInfo.setDelete_status(true);
        applyInfo.setDelete_admin(id);
        int status=applyInfoMapper.updateInfo(applyInfo);
        if (status == 1){
            return new RespBean(ResultEnum.SUCCESS);
        }
        else {
            return new RespBean(ResultEnum.ERROR);
        }
    }

    /**
     * 更新applyinfo
     * @param applyId
     * @return
     */
    public ApplyInfo getApplyInfo(String applyId) {

        ApplyInfo applyInfo=applyInfoMapper.getApplyInfo(applyId);
        return applyInfo;
    }

    //打印
    public RespBean print(String applyId) {
        ApplyInfo applyInfo = applyInfoMapper.getApplyInfo(applyId);
//        String payState=applyInfo.getPayType();
//        if (payState.equals("-1")) {
//            throw new MyException(MyEnum.PRINT_PAY_FAIL);
//        }
        Express express = expressMapper.getExpressByApplyId(applyId);
//        User user = userDao.findByUserId(applyInfo.getUserId());
        if (null == applyInfo) {
            throw new MyException(MyEnum.APPLY_INFO_NOT_EXIST);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        Map<String, String> map = new HashMap<>();
        map.put("userid", String.valueOf(applyInfo.getUserId()));
        map.put("orderid", express.getApplyId());
        map.put("d_province", "");
        map.put("d_city", "");
//        map.put("d_company", user.getUsername());
        //邮寄
        if (express.getType().equals("0")) {
            map.put("d_contact", express.getShrName());
            map.put("d_tel", express.getShrPhone());
            map.put("d_address", express.getShAddress());
            map.put("remark", express.getShrRemark());
        }

        map.put("express_type", "1");
        map.put("pay_method", "1");

        log.info("printMap={}", map);
        HttpEntity<Map> billingRequestVoHttpEntity = new HttpEntity<>(map, headers);
        String s = restTemplate.postForObject(printUrl, billingRequestVoHttpEntity, String.class);
        log.info("打印后台返回={}", s);
        // String s = "{\"data\":{\"url\":\"http://127.0.0.1:4040/sf/waybill/print?type=V2.1_poster_100mm150mm&output=print\",\"dto\":\"{\\\"QRCode\\\":\\\"\\\",\\\"abFlag\\\":\\\"\\\",\\\"appId\\\":\\\"XBAQRZZX\\\",\\\"appKey\\\":\\\"NcT9zD3AvXCthKyWPeVP42iRCfnJf5TG\\\",\\\"cargoInfoDtoList\\\":[{\\\"cargo\\\":\\\"主KEY数\\\",\\\"cargoAmount\\\":\\\"\\\",\\\"cargoCount\\\":\\\"1\\\",\\\"cargoTotalWeight\\\":\\\"\\\",\\\"cargoUnit\\\":\\\"只\\\",\\\"cargoWeight\\\":\\\"\\\",\\\"parcelQuantity\\\":\\\"\\\",\\\"remark\\\":\\\"KEY 贵重物品  小心轻放\\\",\\\"sku\\\":\\\"\\\"},{\\\"cargo\\\":\\\"副KEY数\\\",\\\"cargoAmount\\\":\\\"\\\",\\\"cargoCount\\\":\\\"0\\\",\\\"cargoTotalWeight\\\":\\\"\\\",\\\"cargoUnit\\\":\\\"只\\\",\\\"cargoWeight\\\":\\\"\\\",\\\"parcelQuantity\\\":\\\"\\\",\\\"remark\\\":\\\"\\\",\\\"sku\\\":\\\"\\\"}],\\\"codMonthAccount\\\":\\\"\\\",\\\"codValue\\\":\\\"\\\",\\\"codingMapping\\\":\\\"\\\",\\\"codingMappingOut\\\":\\\"\\\",\\\"consignerAddress\\\":\\\"ddddddddd\\\",\\\"consignerCity\\\":\\\"\\\",\\\"consignerCompany\\\":\\\"西部安全认证中心\\\",\\\"consignerCounty\\\":\\\"\\\",\\\"consignerMobile\\\":\\\"15755433333\\\",\\\"consignerName\\\":\\\"111111\\\",\\\"consignerProvince\\\":\\\"\\\",\\\"consignerShipperCode\\\":\\\"\\\",\\\"consignerTel\\\":\\\"15755433333\\\",\\\"deliverAddress\\\":\\\"银川市金凤区新昌西路65号易大紫荆花商务中心A座7层\\\",\\\"deliverCity\\\":\\\"银川市\\\",\\\"deliverCompany\\\":\\\"西部安全认证中心责任有限公司\\\",\\\"deliverCounty\\\":\\\"\\\",\\\"deliverMobile\\\":\\\"4008600271\\\",\\\"deliverName\\\":\\\"西部CA客服部\\\",\\\"deliverProvince\\\":\\\"宁夏回族自治区\\\",\\\"deliverShipperCode\\\":\\\"951\\\",\\\"deliverTel\\\":\\\"4008600271\\\",\\\"destCode\\\":\\\"\\\",\\\"destRouteLabel\\\":\\\"\\\",\\\"destTeamCode\\\":\\\"\\\",\\\"electric\\\":\\\"E\\\",\\\"encryptCustName\\\":\\\"false\\\",\\\"encryptMobile\\\":\\\"false\\\",\\\"expressType\\\":\\\"1\\\",\\\"insureFee\\\":\\\"\\\",\\\"insureValue\\\":\\\"0\\\",\\\"logo\\\":\\\"\\\",\\\"mailNo\\\":\\\"233172902189\\\",\\\"monthAccount\\\":\\\"9510655029\\\",\\\"orderNo\\\":\\\"\\\",\\\"payArea\\\":\\\"\\\",\\\"payMethod\\\":\\\"1\\\",\\\"printIcon\\\":\\\"\\\",\\\"proCode\\\":\\\"\\\",\\\"returnTrackingNo\\\":\\\"\\\",\\\"sftelLogo\\\":\\\"\\\",\\\"sourceTransferCode\\\":\\\"\\\",\\\"topLogo\\\":\\\"\\\",\\\"topsftelLogo\\\":\\\"\\\",\\\"totalFee\\\":\\\"\\\",\\\"xbFlag\\\":\\\"\\\",\\\"zipCode\\\":\\\"951\\\"}\"},\"errMsg\":\"操作成功.\",\"retCode\":0}";
        return new RespBean(ResultEnum.SUCCESS,s);
    }



    /**
     * 所有文件通过后，修改记录的审核状态
     * 判断记录的审核状态
     * @Description
     * @Param [examRequestVo]
     * @return java.lang.Integer
     **/
    public Integer applyIsPass(ExamRequestVo examRequestVo) {
        Boolean flag = false;
        for (Map<String, String> map : examRequestVo.getList()) {
            String isPass=String.valueOf(map.get("isPass"));
            if (!Boolean.valueOf(isPass)) {
                flag = false;
                break;
            }
            flag = true;
        }

        if (!flag) {
            return ExamEnum.NOPASS.getCode();
        }
        return ExamEnum.PASS.getCode();
    }


    private List<String> applyIdMap(List<ApplyInfo> applyInfoList) {
        List<String> list = new ArrayList<>();
        for (ApplyInfo applyInfo : applyInfoList) {
            list.add(applyInfo.getApplyId());
        }
        return list;
    }
    //返回 key 为applyid   value为邮寄类型的 map
    private Map<String, Express> expressListToMap(List<Express> expressList, List<ApplyInfo> applyInfos) {
        Map<String, Express> emap = new HashMap<>();
        for (Express express : expressList) {
            emap.put(express.getApplyId(), express);
        }
        Map<String, Express> typemap = new HashMap<>();
        for (ApplyInfo applyInfo : applyInfos) {
            typemap.put(applyInfo.getApplyId(), emap.get(applyInfo.getApplyId()));
        }

        return typemap;
    }


    //可以用lambda表达式  处理list数据，形成map
    private Map<String, String> checkPayState(List<ApplyInfo> applyInfoList) {
        List<String> list = new ArrayList<>();
        for (ApplyInfo applyInfo : applyInfoList) {
            list.add(applyInfo.getApplyId());
        }
        ResultVO resultVO = payInvoiceExpressServ.checkPayState(list);
        Map<String, String> data = null;
        if (resultVO.getCode() == 0) {
            data = (Map<String, String>) resultVO.getData();
        }
        return data;
    }


    //证书类型: 0建设用地 ,1采矿权 ,2探矿权
    public DataResponseVo doCertificate(DataResponseVo vo, ApplyInfo applyInfo) {
        String certificateType = applyInfo.getCertificateType();
        String cer = "";
        if (certificateType.contains("0")) {
            cer = cer + "国有建设用地使用权,";
        }
        if (certificateType.contains("1")) {
            cer = cer + "采矿权,";
        }
        if (certificateType.contains("2")) {
            cer = cer + "探矿权,";
        }
        vo.setCertificateTypeStr(cer);
        return vo;
    }

    //导出excel表格
    public List<DownloadZcVo> downloadzehngshu(ApplyInfo applyInfoparam){
//        ApplyInfo applyInfoparam=new ApplyInfo();
//        applyInfoparam.setDownload_status(0);
        applyInfoparam.setIsPass(4);//通过
        // 获取要下载的数据
        List<ApplyInfo> applyInfolist=applyInfoMapper.findAllApplyInfo(applyInfoparam);
        List<DownloadZcVo> downloadZcVoList=new ArrayList<>();
        //查询支付状态
        Map<String, String> map = checkPayState(applyInfolist);

        for (ApplyInfo applyInfo:applyInfolist){
            String paytypetemp=map.get(applyInfo.getApplyId()).trim();
            //如果不是支付成功状态，过滤
            if (!paytypetemp.equals("0")){
                continue;
            }
            String handletype=applyInfo.getHandlingType();
            DcUser dcUser=dcUserMapper.getDcUsersByApplyId(applyInfo);
            DownloadZcVo downloadZcVo=new DownloadZcVo();
            BeanUtils.copyProperties(dcUser, downloadZcVo);
            downloadZcVo.setHandlingType(handletype);
            downloadZcVo.setOutime("1年");
            downloadZcVo.setDengjihao("无");
            downloadZcVo.setCn("CN");

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
            String s=restTemplate.getForObject(invoiceurl+"/"+applyInfo.getApplyId(),String.class);
            JSONObject jsonObject=JSON.parseObject(s);
            String code=jsonObject.getString("code");
            //查询失败
            if (code.equals("-1")){
                continue;
            }
            String money=JSON.parseObject(jsonObject.getString("data")).getString("money");
            downloadZcVo.setPrice(money);
            StringBuffer sBuffer = new StringBuffer("GTZYT-");
//            String zczj="GTZYT-";
            if (dcUser.getTdZczj()!=null && !dcUser.getTdZczj().equals("")){
                sBuffer.append("-TD");
            }
            else{
                sBuffer.append("-00");
            }
            if (dcUser.getTkqZczj()!=null && !dcUser.getTkqZczj().equals("")){
                sBuffer.append("-TK");
            }
            else{
                sBuffer.append("-00");
            }
            if (dcUser.getCkqZczj()!=null && !dcUser.getCkqZczj().equals("")){
                sBuffer.append("-CK");
            }
            else{
                sBuffer.append("-00");
            }
            sBuffer.append("-00");
            downloadZcVo.setZczj(sBuffer.toString());

            //sum数据组装
            StringBuffer sumbuffer = new StringBuffer();
            sumbuffer.append("cn=");
            sumbuffer.append(downloadZcVo.getUsername());
            sumbuffer.append(",");
            sumbuffer.append("o=");
            sumbuffer.append(downloadZcVo.getZjbh());
            sumbuffer.append(",");
            sumbuffer.append("ou=机构类型：法定代表人|");
            sumbuffer.append(downloadZcVo.getFrmc());
            sumbuffer.append(",");
            sumbuffer.append("ou=地址：");
            sumbuffer.append(downloadZcVo.getDz());
            sumbuffer.append(",");
            sumbuffer.append("ou=有效期：---|---");
            sumbuffer.append(",");
            sumbuffer.append("ou=颁发单位：");
            sumbuffer.append(downloadZcVo.getIssue_unit());
            sumbuffer.append(",");
            sumbuffer.append("ou=登记号：无,");
            sumbuffer.append("ou=");
            sumbuffer.append(sBuffer.toString());
            downloadZcVo.setSum(sumbuffer.toString());
            downloadZcVoList.add(downloadZcVo);
        }

        //将状态更新为已经下载
//        for (ApplyInfo applyInfo:applyInfolist){
//            applyInfo.setDownload_status(1); // 1:为已下载状态
//            applyInfoMapper.updateInfo(applyInfo);//
//        }

        return downloadZcVoList;
    }

}
