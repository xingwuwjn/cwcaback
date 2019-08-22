package com.cwca.vo;


import com.cwca.bean.common.PageParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * @Author: liforever
 * @Date: 2019/5/8 12:32
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamRequestVo extends PageParam {
//    private String page;   //页数
    private String applyId;   //申请id
    private String fileInfoId; //文件信息id
    private Boolean isPass;  //是否通过
    private Integer dept;    //0：初审， 1：终审
    private List<Map<String, String>> list;
    private String note;
    private Integer userId;

//    public ExamRequestVo() {
//        User currentUser = (User) SecurityUtils.getSubject().getSession().getAttribute("currentUser");
//        if (null != currentUser) {
//            this.dept = currentUser.getDept();
//            this.userId = currentUser.getUserId();
//        } else {
//            throw new MyException(MyEnum.SESSION_USER_FAIL);
//        }
//    }
}
