package com.cwca.controller.common;


import com.cwca.bean.User;
import com.cwca.constant.RedisConstant;
import com.cwca.service.HrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class CommonController {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private HrService hrService;

    //获取当前登录用户
    public User getCurrentUser(HttpServletRequest request){
//        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        String token1=request.getHeader("token");
        if (token1 !=null){
        String token=String.format(RedisConstant.TOKEN_PREFIX, token1);
        String username = redisTemplate.opsForValue().get(token);
        return hrService.loadUserByUsername(username);}
        else {
            return null;
        }
    }
}
