package com.mn.yunhwa.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class PetDiaryController {

    @GetMapping("/mypet/alldiary")
    public String petDiarymain(){
        return "yunhwa/allDiary";
    }

    @GetMapping("/mypet/diary")
    public String petDiaryDtl(){
        return "yunhwa/petDiary";
    }

}
