package com.cwca.mapper.examination;


import com.cwca.bean.User;
import com.cwca.bean.examination.ApplyInfo;

import java.util.List;

public interface ApplyInfoMapper {
    //依据条件获取申请信息
    List<ApplyInfo> findAllApplyInfo(ApplyInfo applyInfo);

    ApplyInfo getApplyInfo(String applyId);

    int updateInfo(ApplyInfo ApplyInfo);
}
