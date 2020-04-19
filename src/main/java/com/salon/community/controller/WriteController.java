package com.salon.community.controller;

import com.salon.community.cache.TagCache;
import com.salon.community.dto.QuestionDTO;
import com.salon.community.model.Article;
import com.salon.community.model.User;
import com.salon.community.service.ArticleService;
import com.salon.community.service.QuestionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class WriteController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private QuestionService questionService;

    @GetMapping("/write")
    public String write(Model model) {
        model.addAttribute("tags", TagCache.get());
        return "write";
    }

    @GetMapping("/write/{id}")
    public String edit(@PathVariable(name = "id") Long id,
                       Model model) {
        QuestionDTO question = questionService.getById(id);
        model.addAttribute("title", question.getTitle());
        model.addAttribute("description", question.getDescription());
        model.addAttribute("tag", question.getTag());
        model.addAttribute("id", question.getId());
        model.addAttribute("tags", TagCache.get());
        return "write";
    }

    @PostMapping("/write")
    public String doArticleCreate(@RequestParam(value = "title", required = false) String title,
                                  @RequestParam(value = "description", required = false) String description,
                                  @RequestParam(value = "tag", required = false) String tag,
                                  @RequestParam(value = "id", required = false) Long id,
                                  HttpServletRequest request,
                                  Model model) {
        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("tag", tag);
        model.addAttribute("tags", TagCache.get());

        if (title == null || title == "") {
            model.addAttribute("error3", "标题不能为空");
            return "write";
        }
        if (description == null || description == "") {
            model.addAttribute("error3", "问题描述不能为空");
            return "write";
        }
        if (tag == null || tag == "") {
            model.addAttribute("error3", "标签不能为空");
            return "write";
        }
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            model.addAttribute("error3", "用户未登录");
            return "write";
        }
        String invalid = TagCache.filterInvalid(tag);
        if (StringUtils.isNotBlank(invalid)) {
            model.addAttribute("error3", "输入非法标签:" + invalid);
            return "write";
        }

        Article article = new Article();
        article.setTitle(title);
        article.setDescription(description);
        article.setTag(tag);
        article.setId(id);
        article.setCreator(user.getId());
        articleService.createOrUpdate(article);
        return "redirect:/article";
    }
}