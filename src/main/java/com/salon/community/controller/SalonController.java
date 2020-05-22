package com.salon.community.controller;

import com.salon.community.cache.TagCache;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SalonController {
    @GetMapping("/salon")
    public String index(Model model) {
        model.addAttribute("tags", TagCache.get());
        return "salon";
    }
}
