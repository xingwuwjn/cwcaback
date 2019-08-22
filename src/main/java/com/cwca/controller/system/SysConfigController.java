package com.cwca.controller.system;

import com.cwca.bean.Menu;
import com.cwca.bean.User;
import com.cwca.bean.common.RespBean;
import com.cwca.controller.common.CommonController;
import com.cwca.enums.ResultEnum;
import com.cwca.service.MenuService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Api(description = "菜单接口")
@RestController
public class SysConfigController extends CommonController {
    @Autowired
    MenuService menuService;
    /**
     * 获取当前登录用户的菜单树
     * @param request
     * @return
     */
    @GetMapping("/sysmenu")
    public RespBean sysmenu(HttpServletRequest request) {
        User user =super.getCurrentUser(request);
        if (user ==null){
            return null;
        }
        List<Menu> menuList= menuService.getMenusByHrId(user.getId());
        return new RespBean(ResultEnum.SUCCESS,menuList);
    }
}
