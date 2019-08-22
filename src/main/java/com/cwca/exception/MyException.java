package com.cwca.exception;


import com.cwca.enums.MyEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 前端无法处理后台返回的统一错误响应
 * 自定义前端 异常类
 *
 * @Author: liforever
 * @Date: 2019/5/7 10:35
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class MyException extends RuntimeException {
    private String status;

    public MyException(String status, String msg) {
        super(msg);
        this.status = status;
    }

    public MyException(MyEnum myEnum) {
        super(myEnum.getMsg());
        this.status = myEnum.getCode();
    }
}
