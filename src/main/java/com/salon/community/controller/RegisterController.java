package com.salon.community.controller;

import com.salon.community.model.Keys;
import com.salon.community.service.KeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RegisterController {
    @Autowired
    private KeyService keyService;

    @GetMapping("/register")
    public String register() {
        ModelAndView modelAndView = new ModelAndView("register.html");
        modelAndView.addObject("background-image", "../static/image/3.jpg");
        modelAndView.addObject("background-repeat", "no-repeat");
        modelAndView.addObject("background-size", "100% auto");
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam(value = "key", required = false) String key,
                           Model model) {
        model.addAttribute("key", key);
        if (key == null || key == "") {
            model.addAttribute("error4", "邀请码不能为空");
            return "register";
        }
        Keys keys = keyService.selectByKey(key);
        if(keys!=null){
            keyService.ChangeStatus(keys.getId());
        }
        return "redirect:/index";
    }
}
