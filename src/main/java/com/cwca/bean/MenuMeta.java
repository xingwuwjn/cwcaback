package com.cwca.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by wjn on 2018/11/26.
 */
@Data
public class MenuMeta implements Serializable {

    private boolean nocache;//会不会被 <keep-alive> 缓存
    private String icon;//侧边栏图标
    private String title;//侧边栏标题
}
