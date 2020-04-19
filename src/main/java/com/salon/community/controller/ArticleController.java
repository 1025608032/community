package com.salon.community.controller;

import com.salon.community.cache.TagCache;
import com.salon.community.dto.ArticleDTO;
import com.salon.community.dto.Comment2DTO;
import com.salon.community.dto.PaginationDTO;
import com.salon.community.enums.CommentTypeEnum;
import com.salon.community.service.ArticleService;
import com.salon.community.service.Comment2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private Comment2Service commentService;

    @GetMapping("/article")
    public String column(Model model,
                         @RequestParam(name = "page", defaultValue = "1") Integer page,
                         @RequestParam(name = "size", defaultValue = "5") Integer size) {
        PaginationDTO pagination = articleService.list(page, size);
        model.addAttribute("pagination", pagination);
        model.addAttribute("tags", TagCache.get());
        return "article";
    }

    @GetMapping("/article/{id}")
    public String article(@PathVariable(name = "id") Long id, Model model) {
        ArticleDTO article = articleService.getById(id);
        List<ArticleDTO> relatedArticles = articleService.selectRelated(article);
        List<Comment2DTO> comments = commentService.listByTargetId(id, CommentTypeEnum.QUESTION);
        //累加阅读数
        articleService.incView(id);
        model.addAttribute("article", article);
        model.addAttribute("comments", comments);
        model.addAttribute("relatedArticles", relatedArticles);
        model.addAttribute("tags", TagCache.get());
        return "article";
    }
}
