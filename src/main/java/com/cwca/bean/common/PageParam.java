package com.cwca.bean.common;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;


/**
 * 分页公共bean
 */
//@JSONType(ignores ={"page", "limit"})
@Data
public class PageParam {
    @JSONField(serialize=false)
    private int page;//页数
    @JSONField(serialize=false)
    private int limit;//限制
    private String orderName;//排序字段名
    private Boolean isAsc;//是否正序
}
