package com.cwca.controller.system;

import com.alibaba.fastjson.JSONObject;
import com.cwca.bean.User;
import com.cwca.bean.common.RespBean;
import com.cwca.constant.RedisConstant;
import com.cwca.controller.common.CommonController;
import com.cwca.enums.ResultEnum;
import com.cwca.exception.TemplateException;
import com.cwca.service.HrService;
import com.cwca.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Created by wjn on 2018/12/23.
 */
@Api(description = "登录、退出接口")
@RestController
public class LoginController extends CommonController {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    HrService hrService;

    @Autowired
    MenuService menuService;

    @ApiOperation(value = "登录验证" ,  notes="登录验证")
    @PostMapping("/dblogin")
    public RespBean dblogin(@RequestBody JSONObject jsonParam,
                            HttpServletResponse response) {

        String username = jsonParam.getString("username");
        String password = jsonParam.getString("password");
        User user = hrService.dbUser(username, password);

        if (user != null) {
            String token = UUID.randomUUID().toString();
            Integer expire = RedisConstant.EXPIRE;
//            //1. 设置token至cookie
//            CookieUtil.set(response, CookieConstant.TOKEN, token, expire);
            //2. 设置token至redis
            redisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_PREFIX, token), username, expire, TimeUnit.SECONDS);
            return new RespBean(200,token, user);
        } else {
            throw new TemplateException(ResultEnum.AUTHORIZE_ERROR);
        }
    }

    @ApiOperation(value = "退出" ,  notes="退出登录")
    @PostMapping("/logout")
    public RespBean logout(HttpServletRequest request) {
        //1. 从header里查询
        String token=request.getHeader("token");
        if (token != null) {
            //2. 清除redis token
            redisTemplate.opsForValue().getOperations().delete(String.format(RedisConstant.TOKEN_PREFIX, token));

        }
        return new RespBean(ResultEnum.LOGOUT_SUCCESS);
    }

}
