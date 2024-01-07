package com.mn.yunhwa.controller;

import com.mn.constant.MyPetCategory;
import com.mn.entity.MyPet;
import com.mn.yunhwa.dto.MyPetFormDTO;
import com.mn.yunhwa.service.MyPetService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class MyPetController {

    private final MyPetService myPetService;
    //private final MyPetImgService myPetImgService;


    @GetMapping("/mypet/register")
    public String petRegister(Model model,HttpSession session) {
        MyPetFormDTO myPetFormDTO = new MyPetFormDTO();

        model.addAttribute("MyPetFormDTO", myPetFormDTO);
        Object memberCode = session.getAttribute("memberCode");

        if (memberCode != null) {
            model.addAttribute("memberCode", memberCode);
        } else {
            return "/members/login";
        }
        return "yunhwa/myPetForm";
    }

    @PostMapping("mypet/register")
    public String petRegisterIn(@Valid MyPetFormDTO myPetFormDTO, BindingResult bindingResult, Model model, @RequestParam("myPetImgFile") MultipartFile myPetImgFile){
        System.out.println("MyPetController.petRegister() 진입");
        System.out.println(myPetFormDTO);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String id = authentication.getName();

        if(bindingResult.hasErrors()){
            System.out.println(bindingResult);
            System.out.println("반려동물 등록중 예외발생");
            return "yunhwa/myPetForm";
        }

        try {
           Long petId = myPetService.savedMyPet(myPetFormDTO,myPetImgFile);

        }catch (Exception e){
            e.printStackTrace();
            model.addAttribute("errorMessage","반려동물 등록중 에러가 발생하였습니다.");
            return  "yunhwa/myPetForm";
        }
        return  "yunhwa/myPage";
    }
}
