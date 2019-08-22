package com.cwca.bean.fbdeal;

import com.cwca.bean.common.PageParam;
import lombok.Data;

@Data
public class FbdealParam extends PageParam {
    private Integer minlikes;//点赞
    private Integer mincomments;//评论数
    private Integer minviews;//浏览数
    private Integer minshares;//分享数
    private Integer maxlikes;//点赞
    private Integer maxcomments;//评论数
    private Integer maxviews;//浏览数
    private Integer maxshares;//分享数
    private String keyword;//关键词
    private String adtype;//广告类型
    private String action;//按钮类型
    private String sortname;//排序字段
    private String create_time;//创建时间

}
