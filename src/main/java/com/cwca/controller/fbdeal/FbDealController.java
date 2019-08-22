package com.cwca.controller.fbdeal;


import com.cwca.bean.common.RespBean;
import com.cwca.bean.fbdeal.Fbdeal;
import com.cwca.bean.fbdeal.FbdealParam;
import com.cwca.enums.ResultEnum;
import com.cwca.service.fbdeal.FbdealService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(description = "Fb商品接口")
@Slf4j
@RestController
@RequestMapping("/fbdeal")
public class FbDealController {


    @Autowired
    FbdealService fbdealService;

    /**
     * 获取文章列表（分页形式)
     * @return
     */
    @ApiOperation(value = "获取fb广告列表" ,  notes="获取fb广告列表")
    @PostMapping(value = "/list")
    public RespBean getFbdeals(@RequestBody FbdealParam fbdeal){
        int currentPage=fbdeal.getPage();
        int pageSize=fbdeal.getLimit();
        fbdeal.getCreate_time();
        Page page=PageHelper.startPage(currentPage, pageSize);
        List<Fbdeal> fbdeals=fbdealService.getAllFbdeal(fbdeal);
        for(Fbdeal fbdeal1:fbdeals){
            String firstimage=fbdeal1.getFirstimage();
            if (!firstimage.contains("http") && !firstimage.contains("https")){
                fbdeal1.setFirstimage("http://"+firstimage);
            }
        }
        return new RespBean(ResultEnum.SUCCESS,fbdeals,page.getTotal());
    }

}
