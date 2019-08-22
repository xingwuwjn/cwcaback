package com.cwca.mapper.examination;


import com.cwca.bean.examination.ApplyInfo;
import com.cwca.bean.examination.DcUser;

import java.util.List;

public interface DcUserMapper {

    //依据userid获取用户
    List<DcUser> getDcUsers(List<Integer> userids);

    DcUser getDcUsersByApplyId(ApplyInfo applyInfo);

}
