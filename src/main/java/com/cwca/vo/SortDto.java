package com.cwca.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: liforever
 * @Date: 2019/5/8 9:23
 */
@Data
@AllArgsConstructor
public class SortDto {

    //排序方式
    private String orderType;

    //排序字段
    private String orderField;


    //默认为DESC排序
    public SortDto(String orderField) {
        this.orderField = orderField;
        this.orderType = "asc";
    }
}