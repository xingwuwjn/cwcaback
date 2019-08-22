package com.cwca.bean.examination;

import com.cwca.bean.common.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author: liforever
 * @Date: 2019/6/3 9:40
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DynamicUpdate
@Table(name = "dc_express")
@EqualsAndHashCode(callSuper = true)
public class Express extends BaseEntity {

    @Id
    //应用申请id
    private String applyId;
    //订单号
    private String orderNo;
    //时限
    private String years;
    //邮寄类型
    private String type;
    //取证人姓名
    private String qzrName;
    //取证人电话
    private String qzrPhone;
    //取证人idcard
    private String qzrIdCard;
    //取证地址
    private String qzAddress;
    //收货人姓名
    private String shrName;
    //收货人电话
    private String shrPhone;
    //收货人地址
    private String shAddress;
    //收货人备注
    private String shrRemark;


}
