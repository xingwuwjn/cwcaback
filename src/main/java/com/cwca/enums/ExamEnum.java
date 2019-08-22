package com.cwca.enums;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @Author: liforever
 * @Date: 2019/4/29 11:36
 *
 * 审核相关
 */
@NoArgsConstructor
@AllArgsConstructor
public enum ExamEnum {
    FIRST(0, "初审"),
    FINALLY(1, "终审"),

    WAIT(2, "待审核"),
    NOPASS(3, "未通过"),
    PASS(4, "通过");
    private Integer code;
    private String msg;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
