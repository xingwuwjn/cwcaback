package com.cwca.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: liforever
 * @Date: 2019/6/4 9:15
 *
 * 支付开票快递  集中处理vo
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PayVo {

    private String gmfBank;
    private String gmfAddress;
    private String gmfId;
    //接收短信
    private String gmfPhone;
    //购买方电话
    private String gmfTelphone;
    private String type;
    private String years;
    private String qzrName;
    private String qzrIdCard;
    private String gmfName;
    private String applyId;
    private String shrRemark;
    private String shAddress;
    private String qzrPhone;
    private String shrName;
    private String shrPhone;
    private String gmfAccount;
    private String qzAddress;
}
