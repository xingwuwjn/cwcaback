package com.cwca.aspect;


import com.cwca.constant.RedisConstant;
import com.cwca.enums.ResultEnum;
import com.cwca.exception.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Aspect
@Component
@Slf4j
public class TemplateAuthorizeAspect {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Pointcut("execution(public * com.cwca.controller..*.*(..))"+
    "&& !execution(public * com.cwca.controller.system.LoginController.*(..))")
    public void verify() {}

    @Around("verify()")
    public Object doVerify(ProceedingJoinPoint pjp) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        HttpServletResponse response=attributes.getResponse();
        //查询cookie
//        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        String token1=request.getHeader("token");//去redis里查询
        String tokenValue = redisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_PREFIX, token1));

        if (token1 == null) {
            log.warn("【请求错误】Cookie中查不到token");
            throw new TemplateException(ResultEnum.TOKEN_ERROR);//抛出认证错误异常
        }


        else if (StringUtils.isEmpty(tokenValue)) {
            log.warn("【token过期/未登录】Redis中查不到token");
            throw new TemplateException(ResultEnum.TOKEN_FAILURE);//抛出认证错误异常
        }
        else{
            return pjp.proceed();

        }
    }


}
