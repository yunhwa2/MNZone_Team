package com.mn.yunhwa.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MypageController {
    @GetMapping(value="/mypage")
    public String myPage(){

        return "yunhwa/mypage";
    }

}
