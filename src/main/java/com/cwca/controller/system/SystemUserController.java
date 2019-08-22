package com.cwca.controller.system;

import com.cwca.bean.User;
import com.cwca.bean.common.RespBean;
import com.cwca.controller.common.CommonController;
import com.cwca.enums.ResultEnum;
import com.cwca.service.HrService;
import com.cwca.service.MenuService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by sang on 2018/1/2.
 */
@Api(description = "用户、角色接口")
@RestController
@RequestMapping("/sys/users")
public class SystemUserController extends CommonController {
    @Autowired
    HrService hrService;

    @Autowired
    MenuService menuService;

    @RequestMapping(value = "/id/{hrId}",method = RequestMethod.GET)
    public RespBean getHrById(@PathVariable Integer hrId) {

        User user =hrService.getHrById(hrId);
        return new RespBean(ResultEnum.SUCCESS, user);
    }
    //依据id,删除用户
    @RequestMapping(value = "/{hrId}", method = RequestMethod.DELETE)
    public RespBean deleteHr(@PathVariable Integer hrId) {
        if (hrService.deleteHr(hrId) == 1) {
            return new RespBean(ResultEnum.DELETE_SUCCESS);
        }
        return new RespBean(ResultEnum.DELETE_ERROR);
    }
    //更新用户
    @RequestMapping(value = "/updateuser", method = RequestMethod.PUT)
    public RespBean updateHr(@RequestBody User user) {
        if (hrService.updateHr(user) == 1) {
            return new RespBean(ResultEnum.UPDATE_SUCCESS);
        }
        return new RespBean(ResultEnum.UPDATE_ERROR);
    }
    //依据用户id,设置角色id
    @RequestMapping(value = "/updateuserrole", method = RequestMethod.PUT)
    public RespBean updateHrRoles(Integer hrId, Integer[] rids) {
        if (hrService.updateHrRoles(hrId, rids) == rids.length) {
            return new RespBean(ResultEnum.UPDATE_SUCCESS);
        }
        return new RespBean(ResultEnum.UPDATE_ERROR);
    }
    //依据关键词查询用户
    @RequestMapping("/{keywords}")
    public RespBean getHrsByKeywords(@PathVariable(required = false) String keywords) {
        List<User> users = hrService.getHrsByKeywords(keywords);
        return new RespBean(ResultEnum.SUCCESS, users);
    }

    //用户注册
    @RequestMapping(value = "/reg", method = RequestMethod.POST)
    public RespBean hrReg(String username, String password) {
        int i = hrService.hrReg(username, password);
        if (i == 1) {
            return new RespBean(ResultEnum.REGISTER_SUCCESS);
        } else if (i == -1) {
            return new RespBean(ResultEnum.REGISTER_ERROR);
        }
        return new RespBean(ResultEnum.REGISTER_ERROR);
    }



}
