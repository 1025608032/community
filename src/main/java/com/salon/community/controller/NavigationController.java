package com.salon.community.controller;

import com.salon.community.cache.TagCache;
import com.salon.community.model.Question;
import com.salon.community.model.User;
import com.salon.community.service.QuestionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class NavigationController {
    @Autowired
    private QuestionService questionService;

    @PostMapping("/navigation")
    public String doQuestionCreate(@RequestParam(value = "title", required = false) String title,
                                   @RequestParam(value = "description", required = false) String description,
                                   @RequestParam(value = "tag", required = false) String tag,
                                   HttpServletRequest request,
                                   Model model) {
        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("tag", tag);
        model.addAttribute("tags", TagCache.get());

        if (title == null || title == "") {
            model.addAttribute("error1", "标题不能为空");
            return "index";
        }
        if (tag == null || tag == "") {
            model.addAttribute("error1", "标签不能为空");
            return "index";
        }
        if (description == null || description == "") {
            model.addAttribute("error1", "问题描述不能为空");
            return "index";
        }
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            model.addAttribute("error1", "用户未登录");
            return "index";
        }
        String invalid = TagCache.filterInvalid(tag);
        if (StringUtils.isNotBlank(invalid)) {
            model.addAttribute("error1", "输入非法标签:" + invalid);
            return "index";
        }

        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getId());
        //question.setId(null);
        questionService.createOrUpdate(question);
        return "redirect:/ask";
    }
}
