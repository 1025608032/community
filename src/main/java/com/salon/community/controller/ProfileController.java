package com.salon.community.controller;

import com.salon.community.cache.TagCache;
import com.salon.community.dto.PaginationDTO;
import com.salon.community.model.User;
import com.salon.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by codedrinker on 2019/5/15.
 */
@Controller
public class ProfileController {
    @Autowired
    private QuestionService questionService;

    @GetMapping("/profile")
    public String index(HttpServletRequest request,
                        Model model,
                        @RequestParam(name = "page", defaultValue = "1") Integer page,
                        @RequestParam(name = "size", defaultValue = "5") Integer size) {
        User user = (User) request.getSession().getAttribute("user");
        model.addAttribute("section", "home");
        PaginationDTO paginationDTO = questionService.list(user.getId(), page, size);
        model.addAttribute("pagination", paginationDTO);
        model.addAttribute("tags", TagCache.get());
        return "profile";
    }

    @GetMapping("/profile/{action}")
    public String profile(HttpServletRequest request,
                          @PathVariable(name = "action") String action,
                          Model model,
                          @RequestParam(name = "page", defaultValue = "1") Integer page,
                          @RequestParam(name = "size", defaultValue = "5") Integer size) {

        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/";
        }

        if ("".equals(action)) {
            model.addAttribute("section", "home");
        } else if ("article".equals(action)) {
            model.addAttribute("section", "article");
        } else if ("questions".equals(action)) {
        model.addAttribute("section", "questions");
        } else if ("favlist".equals(action)) {
            model.addAttribute("section", "favlist");
        }
        PaginationDTO paginationDTO = questionService.list(user.getId(), page, size);
        model.addAttribute("pagination", paginationDTO);
        return "profile";
    }
}