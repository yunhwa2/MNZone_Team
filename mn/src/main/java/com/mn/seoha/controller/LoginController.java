package com.mn.seoha.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class LoginController {

    @GetMapping(value ="/members/login")
    public String loginMember(){
        return "members/t_login";
    }

}
