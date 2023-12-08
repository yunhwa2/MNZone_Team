package com.mn.yunhwa.controller;

import com.mn.seoha.dto.MemberFormDTO;
import com.mn.yunhwa.dto.MyPetFormDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MyPetController {
    @GetMapping("/mypet/register")
    public String petRegister(Model model){
        System.out.println("get방식 - MyPetFormDTO view에 전달");

        MyPetFormDTO myPetFormDTO = new MyPetFormDTO();
        model.addAttribute("MyPetFormDTO",myPetFormDTO);
        return "yunhwa/MyPet";
    }
}
