package com.cwca.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author: liforever
 * @Date: 2019/4/29 21:45
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataResponseVo {
    /* 记录id*/
    private String applyId;

    private Integer userId;
    /* 审核是否通过*/
    private Integer isPass;
    /* 办理类型*/
    private String handlingType;
    /* 页面选择的用户类型*/
    private String userType;
    /* 证书类型
    * 证书类型: 0建设用地 ,1采矿权 ,2探矿权
    * */
    private List<String> certificateType;
    private String certificateTypeStr;
    /* 是否经办人 false否 true是*/
    private Boolean isJbr;
    /* 资料*/
    private List<UpFile> files;
    /*申请人姓名*/
    private String applyUserName;
    /* 记录用户类型的统计数据*/
    private Map<String, String> countMap;
    private Integer auditDept;
    private String adminusername;//审核人
    /*邮寄方式  1 自取 0邮寄*/
    private String type;
    /* 支付状态 0支付成功，-1等待支付*/
    private String payState;
    //发货状态/快递单号
    private String sendExpressStatus;

    private String send_type;//送key方式,0邮寄，1自送
    private String express_name;//快递公司
    private String express_num;//快递单号
    private String send_address;//自送地址

    private Boolean self_fetch;//自取状态

    //取证人姓名
    private String qzrName;
    //取证人idcard
    private String qzrIdCard;
    //取证人电话
    private String qzrPhone;
    //取证地址
    private String qzAddress;


    private String create_time;//创建时间
    private String update_time;//更新时间

}
