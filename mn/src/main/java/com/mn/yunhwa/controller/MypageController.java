package com.mn.yunhwa.controller;


import com.mn.yunhwa.dto.*;
import com.mn.yunhwa.service.MyPetService;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MypageController {

    private final MyPetService myPetService;


    @GetMapping("/mypet/register")
    public String petRegister(Model model, HttpSession session) {
        MyPetFormDTO myPetFormDTO = new MyPetFormDTO();

        model.addAttribute("myPetFormDTO", myPetFormDTO);
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
            myPetService.savedMyPet(myPetFormDTO,myPetImgFile);

        }catch (Exception e){
            e.printStackTrace();
            model.addAttribute("errorMessage","반려동물 등록중 에러가 발생하였습니다.");
            return  "yunhwa/myPetForm";
        }
        return "redirect:/mypage";
    }

    @GetMapping("/mypage")
    public String myPageMain(MyPetSearchDTO myPetSearchDTO, HttpSession session, Model model, @ModelAttribute("error") String errorMessage){
        Object memberCodeObject = session.getAttribute("memberCode");
        Long memberCode = memberCodeObject != null ? Long.valueOf(memberCodeObject.toString()) : null;

        List<MyPetMainDTO> myPetList = myPetService.getAllMyPets(myPetSearchDTO,memberCode);

        model.addAttribute("mypets",myPetList);

        if (errorMessage != null && !errorMessage.isEmpty()) {
            model.addAttribute("error", errorMessage);
        }

        return "yunhwa/myPage";
    }

    @GetMapping("mypet/register/{myPetId}")
    public String myPetDtl(@PathVariable("myPetId") Long myPetId, Model model){
        try {
            MyPetFormDTO myPetFormDTO = myPetService.getMyPetDtl(myPetId);
            model.addAttribute("myPetFormDTO", myPetFormDTO);
        } catch (EntityNotFoundException e) {
            model.addAttribute("errorMessage", "존재하지 않는 반려동물입니다.");
            model.addAttribute("myPetFormDTO", new MyPetFormDTO());
            return "yunhwa/myPetForm";
        }
        return "yunhwa/myPetForm";
    }


    @PostMapping("mypet/register/{myPetId}")
    public String myPetUpdate(@Valid MyPetFormDTO myPetFormDTO, BindingResult bindingResult, Model model, @RequestParam("myPetImgFile") MultipartFile myPetImgFile) {
        if (bindingResult.hasErrors()) {
            return "yunhwa/myPetForm";
        }

        try {
             myPetService.updateMyPet(myPetFormDTO,myPetImgFile);
            return "redirect:/mypage";

        } catch (Exception e) {
            model.addAttribute("errorMessage", "반려동물 정보 수정 중 에러가 발생하였습니다.");
            return "yunhwa/myPetForm";
        }


    }


    @GetMapping("/mypage/mypost")
    public String myPost(){
        return "yunhwa/myPost";
    }

}
