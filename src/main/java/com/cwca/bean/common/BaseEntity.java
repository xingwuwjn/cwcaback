package com.cwca.bean.common;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.util.Date;

/**
 * @Author: liforever
 * @Date: 2019/5/6 17:15
 */
@Data
@MappedSuperclass //JPA在生成表结构的时候需要带上基类中的字段
@EntityListeners(AuditingEntityListener.class) // 实现JPA自动填入时间的关键注解。
public class BaseEntity {

    @CreatedDate
    private Date createTime;
    @LastModifiedDate
    private Date updateTime;
}
