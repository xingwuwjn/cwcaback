package com.cwca.mapper.article;

import com.cwca.bean.article.Article;

import java.util.List;

public interface ArticleMapper {
    //依据条件获取文章
    List<Article> getAllArticle(Article article);
    //更新文章
    int updateArticle(Article article);
    //添加文章
    int addArticle(Article article);
}
