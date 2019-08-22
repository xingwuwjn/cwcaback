package com.cwca.bean;

import lombok.Data;

import java.util.List;

/**
 * 用户名
 */
@Data
public class User {
    private Integer id;
    private String name;
    private String phone;
    private String telephone;
    private String address;
    private Boolean enabled;
    private String username;
    private String password;
    private String remark;
    private List<Role> roles;
    private String userface;

}