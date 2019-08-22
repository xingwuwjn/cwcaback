package com.cwca.exception;

import com.cwca.enums.ResultEnum;
import lombok.Data;

/**
 * 模板异常
 */
@Data
public class TemplateException extends RuntimeException {
    private Integer code;

    public TemplateException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public TemplateException(Integer code, String message) {
        super(message);
        this.code = code;
    }

}
