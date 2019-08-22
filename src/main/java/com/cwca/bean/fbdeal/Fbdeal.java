package com.cwca.bean.fbdeal;


import com.cwca.bean.common.PageParam;
import lombok.Data;

@Data
public class Fbdeal extends PageParam {
    private int id; //自增id
    private int likes;//点赞
    private int comments;//评论数
    private int views;//浏览数
    private int shares;//分享数
    private int incrementlikes;//增加的点赞数
    private int incrementviews;//增加的评论数
    private int incrementcomments;//增加的浏览数
    private int incrementshares;//增加的分享数
    private String url;//帖子url
    private String adsurl;//站点url
    private String dealurl;//产品url
    private String title;//标题
    private String posttime;//帖子更新时间
    private String message;//文案
    private String firstimage;//缩略图
    private String images;//图片文案
    private String video;//视频文案
    private Float price;//价格
    private String button_type;//按钮类型
    private String create_time;//创建时间

}
