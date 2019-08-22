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
 * 资料审核
 *
 * @Author: liforever
 * @Date: 2019/4/29 9:56
 */
@Data
public class ApplyFileInfo extends BaseEntity {
    @Id
    private String fileInfoId;
    private String applyId;
    private Integer userId;
    private String dictName;
    private String fileUrl;
    private Integer fileSize;
    /* 审核人*/
    private Integer auditorId;
    /* 文件是否通过 false否 true是*/
    private Boolean isPass;

    /* 文件类型*/
    private String type;
    /* 备注*/
    private String note;

}
