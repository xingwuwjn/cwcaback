package com.cwca.service;



import com.cwca.vo.DataResponseVo;
import com.cwca.vo.ExamRequestVo;
import com.cwca.vo.ExamResponseVo;
import com.cwca.vo.ResultVO;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.util.Map;

/**
 * @Author: liforever
 * @Date: 2019/4/30 10:29
 */
public interface IExamDataServ {

    //根据登陆账号信息查询所属审核信息
//    ExamResponseVo adminFindAllData(Integer page, Integer dept);

    //根据applyid 查询信息
    DataResponseVo adminFindOneData(String applyId, HttpServletRequest request);

    //审核单个文件
    ResultVO passOne(String fileInfoId, Boolean isPass, String applyId, String node);

    //审核单条申请记录
    ResultVO passApply(ExamRequestVo examRequestVo);

    //用户类型 统计
    Map<String, BigInteger> getCount(Integer dept);

    //移交RA
    ResultVO transfer(ExamRequestVo examRequestVo);

    //打印
    ResultVO print(String applyId, String payState);

}
