package com.cwca.bean.examination;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Data
public class DcUser {
    private Integer userId;
    private String username;//用户名---企业名称
    private String xzq;//行政区
    private String zjbh;//统一社会信用代码
    private String password;//密码
    private String salt;
    private String phone1;//电话1
    private String phone2;//电话2
    private int valid;//有效性
    /* 默认全部 新户*/
    private int isNew;//对比中智接口，判断是否新户  0 旧户，1新户
    private Integer roleId;
    private Integer dept;
    //同步中智  1 已同步 0未同步
    private Integer sync = 0;

    private String dz;
    private String lxr;
    private String frmc;
    private String frsfzh;
    private String yyfwTd;
    private String yyfwCkq;
    private String yyfwTkq;
    private String tdQyzz;
    private String dqsj;
    private String tdZczj;
    private String tkqZczj;
    private String ckqZczj;
    private String tkqJzsj;
    private String ckqJzsj;
    private String frxwyxq;
    private String bz;
    private String issue_unit;//营业执照颁发单位

}
