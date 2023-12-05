package com.mn.controller;

import com.mn.seoha.dto.MemberFormDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@RequiredArgsConstructor
@Controller
public class MainController {

    @GetMapping(value = "/")
    public String main(Model model){
        model.addAttribute("memberFormDTO", new MemberFormDTO());
        return "mainHome";
    }
    @GetMapping(value = "/notice")
    public String notice(){
        System.err.println("getmapping notice");

        return "Jiseong/notice/notice." +
                "";
    }

}
