package com.cwca.service.examination;


import com.cwca.service.PayInvoiceExpressServ;
import com.cwca.utils.ResultVoUtil;
import com.cwca.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * RestTemplate接口调用服务类
 *
 * @Author: liforever
 * @Date: 2019/5/15 14:36
 */
@Service
@Slf4j
public class PayInvoiceExpressServImpl implements PayInvoiceExpressServ {

    @Value("${pay.checkPayStatusUrl}")
    private String checkPayStatusUrl;

    @Autowired
    private RestTemplate restTemplate;



    @Override
    public ResultVO checkPayState(List<String> applyIds) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        Map<String,List<String>> map = new HashMap<>();
        map.put("applyIds", applyIds);

        HttpEntity<Map> billingRequestVoHttpEntity = new HttpEntity<>(map, headers);

        ResponseEntity<ResultVO> resultVOResponseEntity = restTemplate.postForEntity(checkPayStatusUrl, billingRequestVoHttpEntity, ResultVO.class);
        if (resultVOResponseEntity.getBody() == null) {
            return ResultVoUtil.error(-1, "");
        }
        return resultVOResponseEntity.getBody();
    }

}
