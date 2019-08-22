package com.cwca.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author: liforever
 * @Date: 2019/5/8 11:37
 */
@Data
@AllArgsConstructor

public class PageVo {
    private int page;   //页数
    private long totalElement;   //数据
    private int totalPage;  //总页数

    private int pre;
    private int next;

    public PageVo(int page, long totalElement, int totalPage) {
        this.page = page;
        this.totalElement = totalElement;
        this.totalPage = totalPage;
        if (this.page > 0) {
            this.pre = page - 1;
        }
        if (this.page < totalPage) {
            this.next = page + 1;
        }
    }

}