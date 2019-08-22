package com.cwca.bean.common;

import com.cwca.enums.ResultEnum;

/**
 * 返回数据包装类
 */
public class RespBean {
    private Integer status;//状态码
    private String msg;//描述
    private Long total;//总数
    private Object data;//数据


    public RespBean() {
    }

    public static RespBean build() {
        return new RespBean();
    }

    public RespBean(ResultEnum resultEnum) {
        this.status = resultEnum.getCode();
        this.msg = resultEnum.getMessage();
        this.data = null;
    }

    public RespBean(ResultEnum resultEnum,Object data) {
        this.status = resultEnum.getCode();
        this.msg = resultEnum.getMessage();
        this.data = data;
    }

    public RespBean(ResultEnum resultEnum,Object data,Long total) {
        this.status = resultEnum.getCode();
        this.msg = resultEnum.getMessage();
        this.data = data;
        this.total=total;
    }

    public RespBean(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public Integer getStatus() {

        return status;
    }

    public RespBean setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public RespBean setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Object getData() {
        return data;
    }

    public RespBean setData(Object data) {
        this.data = data;
        return this;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "RespBean{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
