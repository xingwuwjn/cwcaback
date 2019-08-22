package com.cwca.vo;

import lombok.Data;

/**
 * 响应对象最外层
 *
 * @Author: liforever
 * @Date: 2019/4/22 20:04
 */
@Data
public class ResultVO<T> {
    /* 错误码*/
    private Integer code;
    /* 消息*/
    private String msg;

    private T data;

}
