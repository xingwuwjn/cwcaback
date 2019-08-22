package com.cwca.mapper.examination;


import com.cwca.bean.examination.ApplyFileInfo;
import com.cwca.bean.examination.ApplyInfo;

import java.util.List;
import java.util.Map;

public interface ApplyFileInfoMapper {

    List<ApplyFileInfo> getApplyFileInfoByApplyid(ApplyInfo applyInfo);

    ApplyFileInfo getApplyFileInfoByFileId(Map<String,String> map);

    int updateFileInfo(ApplyFileInfo applyFileInfo);



}
