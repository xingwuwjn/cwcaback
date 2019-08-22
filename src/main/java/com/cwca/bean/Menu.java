package com.cwca.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wjn on 2018/11/26.
 */
@Data
public class Menu implements Serializable {
    private Integer id;//id
    private String redirect;//在面包屑中点击重定向去的地址
    private String path;//链接
    private String component;//组件名称
    private String name;//路由名称
    private Integer parentId;//父id
    private Boolean hidden;//侧边栏是否显示
    private List<Role> roles;//角色
    private List<Menu> children;
    private MenuMeta meta;
}
