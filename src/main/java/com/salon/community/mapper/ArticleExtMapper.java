package com.salon.community.mapper;

import com.salon.community.model.Article;

import java.util.List;

public interface ArticleExtMapper {
    int incView(Article record);
    int incCommentCount(Article record);
    List<Article> selectRelated(Article article);
}
