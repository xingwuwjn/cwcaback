package com.cwca.controller.system;

import com.cwca.bean.*;
import com.cwca.bean.common.RespBean;
import com.cwca.enums.ResultEnum;
import com.cwca.service.*;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 角色和菜单相关控制器
 */
@Api(description = "角色、菜单接口")
@RestController
@RequestMapping("/sys/basic")
public class SystemRoleMenuController {

    @Autowired
    RoleService roleService;

    @Autowired
    MenuService menuService;

    @Autowired
    MenuRoleService menuRoleService;
    
    //删除角色
    @RequestMapping(value = "/role/{rid}", method = RequestMethod.DELETE)
    public RespBean deleteRole(@PathVariable Integer rid) {
        if (roleService.deleteRoleById(rid) == 1) {
            return new RespBean(ResultEnum.DELETE_SUCCESS);
        }
        return new RespBean(ResultEnum.DELETE_ERROR);
    }
    //添加角色
    @RequestMapping(value = "/addrole", method = RequestMethod.POST)
    public RespBean addNewRole(String role, String roleZh) {
        if (roleService.addNewRole(role, roleZh) == 1) {
            return new RespBean(ResultEnum.ADD_ERROR);
        }
        return new RespBean(ResultEnum.ADD_ERROR);
    }
    //依据角色id获取菜单
    @RequestMapping(value = "/menuTree/{rid}", method = RequestMethod.GET)
    public RespBean menuTree(@PathVariable Integer rid) {
        Map<String, Object> map = new HashMap<>();
        List<Menu> menus = menuService.menuTree();
        map.put("menus", menus);
        List<Integer> selMids = menuService.getMenusByRid(rid);
        map.put("mids", selMids);
        return new RespBean(ResultEnum.SUCCESS,map);
    }
    //依据角色id更新菜单
    @RequestMapping(value = "/updateMenuRole", method = RequestMethod.PUT)
    public RespBean updateMenuRole(Integer rid, Integer[] mids) {
        if (menuRoleService.updateMenuRole(rid, mids) == mids.length) {
            return new RespBean(ResultEnum.UPDATE_SUCCESS);
        }
        return new RespBean(ResultEnum.UPDATE_ERROR);
    }
    //获取所有角色
    @RequestMapping("/roles")
    public RespBean allRoles() {

        List<Role> roles=roleService.roles();
        return new RespBean(ResultEnum.SUCCESS,roles);
    }
}
