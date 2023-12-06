package com.mn.yunhwa.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class PetDiaryController {

    @GetMapping("/pet/diary")
    public String petDiarymain(){
        return "yunhwa/petDiary";
    }
}
