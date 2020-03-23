package com.salon.community.controller;

import com.salon.community.dto.PaginationDTO;
import com.salon.community.dto.QuestionDTO;
import com.salon.community.model.Question;
import com.salon.community.model.User;
import com.salon.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AskController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/ask")
    public String index(Model model,
                        @RequestParam(name = "page", defaultValue = "1") Integer page,
                        @RequestParam(name = "size", defaultValue = "5") Integer size) {
        PaginationDTO pagination = questionService.list(page, size);
        model.addAttribute("pagination", pagination);
        return "ask";
    }

    @GetMapping("/ask/{id}")
    public String question(@PathVariable(name = "id") Integer id,
                           @RequestParam(value = "title", required = false) String title,
                           @RequestParam(value = "description", required = false) String description,
                           @RequestParam(value = "tag", required = false) String tag,
                           Model model) {
        QuestionDTO question = questionService.getById(id);
        model.addAttribute("question", question);
        model.addAttribute("id", question.getId());
        return "ask";
    }

    @PostMapping(value = {"ask", "/ask/{id}"})
    public String doQuestionEdit(@PathVariable(name = "id") Integer id,
                                 @RequestParam(value = "title", required = false) String title,
                                 @RequestParam(value = "description", required = false) String description,
                                 @RequestParam(value = "tag", required = false) String tag,
                                 HttpServletRequest request,
                                 Model model) {
        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("tag", tag);

        QuestionDTO questionDTO = questionService.getById(id);
        model.addAttribute("question", questionDTO);

        if (title == null || title == "") {
            model.addAttribute("error1", "标题不能为空");
            return "ask";
        }
        if (tag == null || tag == "") {
            model.addAttribute("error1", "标签不能为空");
            return "ask";
        }
        if (description == null || description == "") {
            model.addAttribute("error1", "问题补充不能为空");
            return "ask";
        }
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            model.addAttribute("error1", "用户未登录");
            return "ask";
        }

        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setId(id);
        questionService.createOrUpdate(question);
        return "redirect:/ask/{id}";
    }
}


