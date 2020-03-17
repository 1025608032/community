package com.salon.community.controller;

import com.salon.community.mapper.QuestionMapper;
import com.salon.community.model.Question;
import com.salon.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {
    @Autowired
    private QuestionMapper questionMapper;

    @GetMapping("/index")
    public String index(HttpServletRequest request) {
        return "index";
    }

    @PostMapping("/index")
    public String doQuestionPublish(
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "tag", required = false) String tag,
            HttpServletRequest request,
            Model model) {
        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("tag", tag);

        if (title == null || title == "") {
            model.addAttribute("error1", "标题不能为空");
            return "index";
        }
        if (description == null || description == "") {
            model.addAttribute("error1", "问题补充不能为空");
            return "index";
        }
        if (tag == null || tag == "") {
            model.addAttribute("error1", "标签不能为空");
            return "index";
        }

        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            model.addAttribute("error1", "用户未登录");
            return "index";
        }

        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getId());
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreate());

        questionMapper.create(question);
        return "redirect:ask";
    }

}
