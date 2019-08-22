package com.cwca.exception;


import com.cwca.bean.common.RespBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 异常捕获
 */
@ControllerAdvice
@Slf4j
public class TemplateExceptionHandler {
    /**
     * 全局异常捕捉处理
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Map errorHandler(Exception ex) {
        log.info(ex.getMessage());
        System.out.println(ex.getMessage());
        Map<String, Object> map = new HashMap<>();
        map.put("status", 500);
        map.put("msg", "操作失败!");
        return map;
    }

    /**
     * 模板异常处理
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = TemplateException.class)
    public RespBean templateException(TemplateException e){

        return new RespBean(e.getCode(),e.getMessage(),null);
    }

}
