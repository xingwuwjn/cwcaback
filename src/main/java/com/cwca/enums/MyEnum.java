package com.cwca.enums;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @Author: liforever
 * @Date: 2019/5/7 11:21
 *
 */
@NoArgsConstructor
@AllArgsConstructor
public enum MyEnum {
    UNKNOWN("10000", "未知错误"),
    USER_EXIT_LOGGED("10009", "已有账号登陆,退出后重试"),
    USER_HAS_LOGGED("20000", "该账号被踢下线,请重新登陆"),
    USER_NOT_EXIST("20001", "用户不存在"),
    USER_LOGIN_FAIL("20002", "用户账号或密码不正确"),
    USER_EXIST("20003", "用户已存在"),
    SESSION_USER_FAIL("30001", "请登录..."),
    APPLY_INFO_NOT_EXIST("40001", "申请信息获取失败"),
    USER_APPLY_NOT_MATCH("40002", "该用户不存在该条申请记录"),

    USER_APPLY_NOT_PASS("40003", "审核未通过，不能发起支付"),

    USER_INFO_SYNC_ERROR("40004", "用户信息同步失败"),

    INVOICE_INFO_GET_ERROR("40005", "发票信息获取失败"),
    INVOICE_INFO_SAVE_ERROR("40005", "发票信息保存失败"),
    FILE_IS_EMPTY("50000", "文件大小为空"),
    PRINT_PAY_FAIL("50001", "未支付，不能打印"),
    EXPRESS_GET_FAIL("60000", "快递信息查询失败"),


    CAN_NOT_CHANGE_FILE("60001", "文件已通过审核，不能重新上传");

    private String code;
    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
