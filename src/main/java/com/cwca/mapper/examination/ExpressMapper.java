package com.cwca.mapper.examination;


import com.cwca.bean.examination.Express;

import java.util.List;

public interface ExpressMapper {

    //依据id列表获取快递信息
    List<Express> getExpressListByIds(List<String> applyids);

    //依据applyid获取Express
    Express getExpressByApplyId(String applyId);
}
