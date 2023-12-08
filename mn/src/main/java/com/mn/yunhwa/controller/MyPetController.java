package com.mn.yunhwa.controller;

import com.mn.entity.MyPet;
import com.mn.seoha.dto.MemberFormDTO;
import com.mn.yunhwa.dto.MyPetFormDTO;
import com.mn.yunhwa.repository.MyPetRepository;
import com.mn.yunhwa.service.MyPetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class MyPetController {

    private final MyPetService myPetService;

    @GetMapping("/mypet/register")
    public String petRegister(Model model){
        System.out.println("get방식 - MyPetFormDTO view에 전달");

        MyPetFormDTO myPetFormDTO = new MyPetFormDTO();
        model.addAttribute("MyPetFormDTO",myPetFormDTO);
        return "yunhwa/myPet";
    }

    @PostMapping("mypet/register")
    public String petRegisterIn(Model model, @Valid MyPetFormDTO myPetFormDTO, BindingResult bindingResult){
        System.out.println("MyPetController.petregister() 진입");
        System.out.println(myPetFormDTO);

        try {
            if(bindingResult.hasErrors()){
                System.out.println("반려동물 등록중 예외발생");
                return "yunhwa/myPet";
            }

        }catch (Exception e){
            e.printStackTrace();
            return  "yunhwa/myPet";
        }
        return  "yunhwa/myPage";
    }
}
