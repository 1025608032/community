package com.salon.community.controller;

import com.salon.community.dto.AccesstokenDTO;
import com.salon.community.dto.GithubUser;
import com.salon.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AuthorController {

    @Autowired
    private GithubProvider githubProvider;
    @Value("${github.client_id}")
    private String clientID;
    @Value("${github.client_secret}")
    private String client_secret;
    @Value("${github.redirect_uri}")
    private String redirect_uri;
    @GetMapping("/login")
    public String login(@RequestParam(name="code") String code,
                        @RequestParam(name="state") String state,
                        HttpServletRequest resquest){
        AccesstokenDTO accesstokenDTO = new AccesstokenDTO();
        accesstokenDTO.setClient_id(clientID);
        accesstokenDTO.setClient_secret(client_secret);
        accesstokenDTO.setCode(code);
        accesstokenDTO.setRedirect_uri(redirect_uri);
        accesstokenDTO.setState(state);
        String accessToken = githubProvider.getAccessToken(accesstokenDTO);
        GithubUser user = githubProvider.getUser(accessToken);
        if(user != null){
            //登录成功，写cookie和session
            resquest.getSession().setAttribute("user",user);
            return "redirect:/";
        }else{
            //登录失败，重新登录
            return "redirect:/";
        }
    }
}
