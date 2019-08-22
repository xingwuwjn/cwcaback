package com.cwca.service.examination;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.cwca.bean.examination.Express;
import com.cwca.enums.MyEnum;
import com.cwca.exception.MyException;
import com.cwca.mapper.examination.ExpressMapper;
import com.cwca.service.ExpressServ;
import com.cwca.utils.FastJsonUtil;
import com.cwca.utils.ResultVoUtil;
import com.cwca.vo.PayVo;
import com.cwca.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import sun.security.util.KeyUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: liforever
 * @Date: 2019/6/13 9:22
 *
 * 快递信息业务类
 */
@Service
@Slf4j
public class ExpressServer implements ExpressServ {

    @Autowired
    private ExpressMapper expressMapper;

    @Value("${exam.findSFOrderIdUrl}")
    private String findSFOrderIdUrl;

    @Autowired
    private RestTemplate restTemplate;



    @Override
    public Express findOne(String applyId) {
        Express express = expressMapper.getExpressByApplyId(applyId);
        if (express == null) {
            throw new MyException(MyEnum.EXPRESS_GET_FAIL);
        }
        return express;
    }
    //依据申请id列表获取所有的快递信息
    @Override
    public List<Express> findAll(List<String> applyIds) {
        return expressMapper.getExpressListByIds(applyIds);
    }

    //通过申请id获取
    @Override
    public Map<String, String> findSFOrderId(List<String> applyIds) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        Map<String,List<String>> map = new HashMap<>();
        map.put("applyIds", applyIds);

        HttpEntity<Map> billingRequestVoHttpEntity = new HttpEntity<>(map, headers);
        Map<String, String> data = null;
        ResponseEntity<ResultVO> resultVOResponseEntity = restTemplate.postForEntity(findSFOrderIdUrl, billingRequestVoHttpEntity, ResultVO.class);
        if (resultVOResponseEntity.getBody() == null) {
            return data;
        }
        //return ResultVoUtil.success(resultVOResponseEntity.getBody());
        ResultVO resultVO=resultVOResponseEntity.getBody();
        if (resultVO.getCode() == null && resultVO.getMsg() == null) {
            data = (Map<String, String>) resultVO.getData();
            log.info("orderId={}", FastJsonUtil.map2Json(data));
        }
        return data;
    }


}
