package com.mn.yunhwa.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class AddMyPetController {
    @GetMapping("/pet/register")
    public String petRegister(){
        return "yunhwa/addMyPet";
    }
}
