package com.cwca.service;


import com.cwca.bean.examination.Express;
import com.cwca.vo.ResultVO;

import java.util.List;
import java.util.Map;

/**
 * @Author: liforever
 * @Date: 2019/6/13 9:22
 */
public interface ExpressServ {
//    void save(PayVo payVo);

    Express findOne(String applyId);

    List<Express> findAll(List<String> applyIds);

    //连库查询快递单号
    Map<String, String> findSFOrderId(List<String> applyId);
}
