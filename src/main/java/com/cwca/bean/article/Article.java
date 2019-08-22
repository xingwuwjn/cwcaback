package com.cwca.bean.article;


import com.alibaba.fastjson.annotation.JSONField;
import com.cwca.bean.common.PageParam;
import lombok.Data;


/**
 * 文章测试bean
 */
@Data
public class Article extends PageParam {
    private int id;//自增id
    private String create_time;//创建时间
    private String author;//作者
    private String  reviewer;//评论数
    private String title;//标题
    private Integer importance;//重要性
    private Integer readings;//浏览数
    private Integer status;//状态
    private Integer type;//类型
    @JSONField(serialize=false)
    private String articledate[];
    @JSONField(serialize=false)
    private String end_time;//结束时间(搜索用)
}
