package com.salon.community.controller;

import com.salon.community.dto.QuestionDTO;
import com.salon.community.model.Question;
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
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id") Integer id,
                           Model model) {
        QuestionDTO question = questionService.getById(id);
        model.addAttribute("question", question);
        model.addAttribute("title", question.getTitle());
        model.addAttribute("description", question.getDescription());
        model.addAttribute("tag", question.getTag());
        model.addAttribute("id", question.getId());
        return "question";
    }

    @PostMapping("/question/{Eid}")
    public String doQuestionEdit(
            @PathVariable(name = "Eid") Integer Eid,
            @RequestParam(value = "Etitle", required = false) String Etitle,
            @RequestParam(value = "Edescription", required = false) String Edescription,
            @RequestParam(value = "Etag", required = false) String Etag,
//            @RequestParam(value = "Eid", required = false) Integer Eid,
            HttpServletRequest request,
            Model model) {
        model.addAttribute("Etitle", Etitle);
        model.addAttribute("Edescription", Edescription);
        model.addAttribute("Etag", Etag);

        if (Etitle == null || Etitle == "") {
            model.addAttribute("Eerror1", "标题不能为空");
            return "/question/{Eid}";
        }
        if (Edescription == null || Edescription == "") {
            model.addAttribute("Eerror1", "问题补充不能为空");
            return "/question/{Eid}";
        }
        if (Etag == null || Etag == "") {
            model.addAttribute("Eerror1", "标签不能为空");
            return "/question/{Eid}";
        }

        Question question = new Question();
        question.setTitle(Etitle);
        question.setDescription(Edescription);
        question.setTag(Etag);
        question.setId(Eid);
        questionService.createOrUpdate(question);
        return "redirect:/question/{Eid}";
    }
}
