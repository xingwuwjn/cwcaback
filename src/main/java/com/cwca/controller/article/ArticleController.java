package com.cwca.controller.article;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.cwca.bean.article.Article;
import com.cwca.bean.common.RespBean;
import com.cwca.enums.ResultEnum;
import com.cwca.service.article.ArticleService;
import com.cwca.utils.DateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(description = "文章接口")
@Slf4j
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    ArticleService articleService;

    /**
     * 获取文章列表（分页形式)
     * @return
     */
    @ApiOperation(value = "获取文章列表" ,  notes="获取文章列表")
    @PostMapping(value = "/list")
    public RespBean getArticles(@RequestBody Article article){
        int currentPage=article.getPage();
        int pageSize=article.getLimit();
        String articledate[]=article.getArticledate();
        if (articledate!=null&& !articledate.equals("")){
            article.setCreate_time(articledate[0]);
            article.setEnd_time(DateUtil.getSpecifiedDayAfter(articledate[1]));
        }
        Page page=PageHelper.startPage(currentPage, pageSize);
        List<Article> articles=articleService.getAllArticle(article);
        return new RespBean(ResultEnum.SUCCESS,articles,page.getTotal());
    }

    /**
     * 添加文章列表
     * @param article
     * @return
     */
    @ApiOperation(value = "添加文章" ,  notes="添加文章")
    @PostMapping(value = "/addarticle")
    public RespBean addArticles(@RequestBody Article article){
        articleService.addArticle(article);
        return new RespBean(ResultEnum.ADD_SUCCESS);
    }

    /**
     * 更新文章列表
     * @param article
     * @return
     */
    @ApiOperation(value = "更新文章" ,  notes="更新文章")
    @PutMapping(value = "/updatearticle")
    public RespBean updateArticles(@RequestBody Article article){
        articleService.updateArticle(article);
        return new RespBean(ResultEnum.UPDATE_SUCCESS);
    }



}
