package com.cwca.controller.examination;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cwca.bean.User;
import com.cwca.bean.common.RespBean;
import com.cwca.bean.examination.ApplyInfo;
import com.cwca.bean.examination.Express;
import com.cwca.controller.common.CommonController;
import com.cwca.enums.ResultEnum;
import com.cwca.log.LogConstants;
import com.cwca.log.LogGenerator;
import com.cwca.service.examination.ExamDataService;
import com.cwca.service.examination.ExpressServer;
import com.cwca.utils.DateUtil;
import com.cwca.utils.FastJsonUtil;
import com.cwca.vo.*;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: liforever
 * @Date: 2019/4/30 10:50
 */
@Controller
@RequestMapping("/exam")
public class ExamDataController extends CommonController {

//    @Autowired
//    private IExamDataServ iExamDataServ;

    @Autowired
    private ExamDataService examDataService;

    @Autowired
    private ExpressServer expressServer;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${invoice.findInvoiceResultUrl}")
    private String invoiceResulturl;

    /**
     * 后台申请信息查询
     * @Description
     * @Param [model, request, examRequestVo]
     * @return java.lang.String
     **/
    @PostMapping("/applyInfoList")
    @ResponseBody
    public RespBean findApplyInfoList(HttpServletRequest request,@RequestBody ApplyInfo applyInfo) {
        int currentPage=applyInfo.getPage();
        int pageSize=applyInfo.getLimit();
        String date[]=applyInfo.getApplyinfo_time();
        if (date!=null){
            applyInfo.setStart_time(date[0]);
            applyInfo.setEnd_time(DateUtil.getSpecifiedDayAfter(date[1]));
        }
        LogGenerator.genLog(request, null, LogConstants.ActionName.EXAM_INFO_GET, applyInfo);

        //获取不同用户类型的数量
//        Map<String, BigInteger> count = iExamDataServ.getCount(Integer.valueOf(examRequestVo.getDept()));
//        model.addAttribute("count", count);
        Page page=PageHelper.startPage(currentPage, pageSize);
        List<DataResponseVo> dataResponseVos = examDataService.adminFindAllData(applyInfo);
        return new RespBean(ResultEnum.SUCCESS,dataResponseVos,page.getTotal());
    }

    /**
     * 返回单条记录
     * @Description
     * @Param [model, request, examRequestVo]
     * @return java.lang.String
     **/
    @PostMapping("/applyProcess")
    @ResponseBody
    public RespBean toApplyProcess(HttpServletRequest request, @RequestBody JSONObject jsonObject) {
        String applyId=jsonObject.getString("applyId");
        LogGenerator.genLog(request, null, LogConstants.ActionName.TO_APPLY_PROCESS, applyId);
        DataResponseVo dataResponseVo = examDataService.getApplyInfoDetail(applyId, request);
        return new RespBean(ResultEnum.SUCCESS,dataResponseVo);
    }

    /**
     * 审核通过单个文件  ，弃用
     * @Description
     * @Param [request, examRequestVo]
     * @return com.cwca.application.common.Vo.ResultVO
     **/
    @GetMapping("/passOneExam")
    @ResponseBody
    public RespBean passExam(HttpServletRequest request, ExamRequestVo examRequestVo) {
        LogGenerator.genLog(request, null, LogConstants.ActionName.EXAM_ONE, examRequestVo);
        RespBean respBean= examDataService.passOne(examRequestVo.getFileInfoId(), examRequestVo.getIsPass(), examRequestVo.getNote());
        return respBean;
    }

    //{"applyId":"155920577811646719","list":["{\"id\":\"155920578088430531\",\"note\":\"\",\"isPass\":\"true\"}","{\"id\":\"155920578088433742\",\"note\":\"\",\"isPass\":\"false\"}","{\"id\":\"155920578088439222\",\"note\":\"\",\"isPass\":\"true\"}"]}
    /**
     * 记录审核
     * @Description
     * @Param [request, jsonObject]
     * @return com.cwca.application.common.Vo.ResultVO
     **/
    @PostMapping("/passApplyExam")
    @ResponseBody
    public RespBean passApply(HttpServletRequest request, @RequestBody JSONObject jsonObject) {
        User user=super.getCurrentUser(request);
        LogGenerator.genLog(request, null, LogConstants.ActionName.Exam_Apply, jsonObject);
        ExamRequestVo examRequestVo = new ExamRequestVo();
        examRequestVo.setApplyId(jsonObject.getString("applyId"));
        JSONArray list = jsonObject.getJSONArray("list");
        ArrayList<Map<String, String>> mapArrayList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            HashMap<String, String> stringStringHashMap = FastJsonUtil.json2Map(list.getString(i));
            mapArrayList.add(stringStringHashMap);
        }
        examRequestVo.setList(mapArrayList);
        RespBean respBean = examDataService.passApply(examRequestVo,user);
        return respBean;
    }

    /**
     * 申请信息移交至终审，需所有文件审核通过
     * @Description
     * @Param [request, jsonObject]
     * @return com.cwca.application.common.Vo.ResultVO
     **/
    @PostMapping("/transfer")
    @ResponseBody
    public RespBean transfer(HttpServletRequest request, @RequestBody JSONObject jsonObject) {
        User user=super.getCurrentUser(request);
        LogGenerator.genLog(request, null, LogConstants.ActionName.TRANSFOR, jsonObject);
        ExamRequestVo examRequestVo = new ExamRequestVo();
        examRequestVo.setApplyId(jsonObject.getString("applyId"));
        JSONArray list = jsonObject.getJSONArray("list");
        ArrayList<Map<String, String>> mapArrayList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            HashMap<String, String> stringStringHashMap = FastJsonUtil.json2Map(list.getString(i));
            mapArrayList.add(stringStringHashMap);
        }
        examRequestVo.setList(mapArrayList);
        RespBean respBean = examDataService.transfer(examRequestVo,user);
        return respBean;
    }

    /**
     * 打印面单
     * @Description
     * @Param [request, applyId, payState 支付状态]
     * @return com.cwca.application.common.Vo.ResultVO
     **/
    @PostMapping("/print")
    @ResponseBody
    public RespBean print(HttpServletRequest request, @RequestBody JSONObject jsonObject) {
        String applyId=jsonObject.getString("applyId");
        LogGenerator.genLog(request, null, LogConstants.ActionName.PRINT, applyId);
        return examDataService.print(applyId);
    }

    /**
     * 获取具体的express
     * @Description
     * @Param [request, applyId, payState 支付状态]
     * @return com.cwca.application.common.Vo.ResultVO
     **/
    @PostMapping("/oneexpress")
    @ResponseBody
    public RespBean oneexpress(HttpServletRequest request, @RequestBody JSONObject jsonObject) {
        String applyId=jsonObject.getString("applyId");
        LogGenerator.genLog(request, null, LogConstants.ActionName.ONEEXPRESS, applyId);
        Express express=expressServer.findOne(applyId);
        return new RespBean(ResultEnum.SUCCESS,express);
    }

    /**
     * 更新applyinfo
     * @Description
     * @Param [request, applyId, payState 支付状态]
     * @return com.cwca.application.common.Vo.ResultVO
     **/
    @PostMapping("/updateapplyinfo")
    @ResponseBody
    public RespBean updateApplyinfo(HttpServletRequest request, @RequestBody JSONObject jsonObject) {
        String applyId=jsonObject.getString("applyId");
        LogGenerator.genLog(request, null, "updateApplyinfo", applyId);
        return examDataService.updateApplyInfo(applyId);
    }

    /**
     * 删除applyinfo
     * @Description
     * @Param [request, applyId, payState 支付状态]
     * @return com.cwca.application.common.Vo.ResultVO
     **/
    @PostMapping("/deleteapplyinfo")
    @ResponseBody
    public RespBean deleteApplyinfo(HttpServletRequest request, @RequestBody JSONObject jsonObject) {
        User user=super.getCurrentUser(request);
        int id=user.getId();
        String applyId=jsonObject.getString("applyId");
        String delete_comment=jsonObject.getString("delete_comment");
        if (delete_comment.equals("")){
            return new RespBean(ResultEnum.ERROR);
        }
        LogGenerator.genLog(request, null, "deleteApplyinfo", applyId);
        return examDataService.deleteApplyInfo(applyId,delete_comment,id);
    }

//    public  RespBean changepaystatus(HttpServletRequest request, @RequestBody JSONObject jsonObject){
////        String applyId=jsonObject.getString("applyId");
////        LogGenerator.genLog(request, null, "changepaystatus", applyId);
////
////    }

    /**
     * 导出证书数据
     * @return
     */
    @PostMapping("/downloadzehngshu")
    @ResponseBody
    public RespBean downloadzehngshu(@RequestBody ApplyInfo applyInfo){
        String date[]=applyInfo.getApplyinfo_time();
        if (date!=null){
            applyInfo.setStart_time(date[0]);
            applyInfo.setEnd_time(DateUtil.getSpecifiedDayAfter(date[1]));
        }
        List<DownloadZcVo> downloadZcVoList=examDataService.downloadzehngshu(applyInfo);
        if (downloadZcVoList==null || downloadZcVoList.size()==0){
            return new RespBean(ResultEnum.DOWNLOAD_ERROR,downloadZcVoList);
        }
        return new RespBean(ResultEnum.SUCCESS,downloadZcVoList);
    }

    /**
     * 查询发票信息
     * @return
     */
    @PostMapping("/getInvoices")
    @ResponseBody
    public RespBean getInvoices( @RequestBody JSONObject jsonObject){
        String applyId=jsonObject.getString("applyId");
        String s=restTemplate.getForObject(invoiceResulturl+"/"+applyId,String.class);
        JSONObject jsonObjecttemp=JSON.parseObject(s);
        String code=jsonObjecttemp.getString("code");
        //查询失败
        if (code.equals("-1")){
            return new RespBean(ResultEnum.SEARCH_ERROR);
        }
        String url=JSON.parseObject(jsonObjecttemp.getString("data")).getString("cUrl");
        return new RespBean(ResultEnum.SUCCESS,url);
    }


    /**
     * 查询发票信息
     * @return
     */
    @PostMapping("/getOneApplyInfo")
    @ResponseBody
    public RespBean getoneApplyInfo( @RequestBody JSONObject jsonObject){
        String applyId=jsonObject.getString("applyId");
        ApplyInfo applyInfo=examDataService.getApplyInfo(applyId);
        return new RespBean(ResultEnum.SUCCESS,applyInfo);
    }


}
