package com.mn.seoha.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor

public class LoginController {
    @GetMapping(value = "/members/login")
    public String loginMember(){

        return "/members/login";
    }
}
