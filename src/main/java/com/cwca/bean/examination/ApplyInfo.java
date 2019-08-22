package com.cwca.bean.examination;


import com.cwca.bean.common.BaseEntity;
import com.cwca.bean.common.PageParam;
import com.cwca.enums.ExamEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 申请资料信息
 *
 * @Author: liforever
 * @Date: 2019/4/29 9:50
 */
@Data
@Slf4j
public class ApplyInfo extends PageParam {
    private String applyId;
    /* 用户id*/
    private Integer userId;
    //审核人id
    private Integer adminUserId;
    /* 办理类型    0新立，1变更，2更新，3丢失补办 4丢失补办更新*/
    private String handlingType;
    /* 页面选择的用户类型*/
    private String userType;
    /* 证书类型*/
    private String certificateType;
    /* 是否经办人 false否 true是  给默认值*/
    private Boolean isJbr;
    /* files 标识*/
    // private String fileDetailId;
    /* 本次申请是否通过 3未通过，4通过，2待审核*/
    private Integer isPass;

    /* 审核  默认初审-->0 审核   移交至终审-->1*/
    private Integer auditDept;

    private Boolean self_fetch;//自取状态

    private  String send_type;//送key方式

    private String express_name;//快递公司名称

    private String express_num;//快递单号

    private String send_address;//自送地址

    //创建时间
    private String create_time;
    //结束时间
    private String applyinfo_time[];
    //更新时间
    private String update_time;

    //删除相关
    private Boolean delete_status;
    private String delete_comment;
    private Integer delete_admin;

    //前台
    // 传入参数-邮寄类型
    private String expressType;
    //传入参数-支付类型
    private String payType;
    //用户名
    private String username;

    private String start_time;//开始时间
    private String end_time;//结束时间

}
