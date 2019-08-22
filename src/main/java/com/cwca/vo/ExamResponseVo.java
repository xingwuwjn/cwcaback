package com.cwca.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//import org.apache.shiro.SecurityUtils;

import java.util.List;

/**
 * 返回 分页数据
 *
 * @Author: liforever
 * @Date: 2019/5/8 11:27
 */
@Data
public class ExamResponseVo {

//    private Integer dept = ((User) SecurityUtils.getSubject().getSession().getAttribute("currentUser")).getDept();
    private PageVo pageVo;
    private List<DataResponseVo> data;

    public ExamResponseVo(PageVo pageVo, List<DataResponseVo> data) {
        this.pageVo = pageVo;
        this.data = data;
    }
}
