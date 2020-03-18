package com.salon.community.controller;

import com.salon.community.dto.PaginationDTO;
import com.salon.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AskController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/ask")
    public String ask(
                      Model model,
                      @RequestParam(name = "page", defaultValue = "1") Integer page,
                      @RequestParam(name = "size", defaultValue = "5") Integer size) {
        PaginationDTO pagination = questionService.list(page, size);
        model.addAttribute("pagination", pagination);
        return "ask";
    }

}


