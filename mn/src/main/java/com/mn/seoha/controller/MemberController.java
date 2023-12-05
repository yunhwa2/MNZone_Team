package com.mn.seoha.controller;

import com.mn.entity.Member;
import com.mn.seoha.dto.MemberFormDTO;
import com.mn.seoha.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/members")
public class MemberController {


    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;
    @GetMapping(value = "/register")
    public String memberForm(Model model){
        System.err.println("get방식 - memberFormDTO view에 전달");

        MemberFormDTO memberFormDTO = new MemberFormDTO();
        model.addAttribute("memberFormDTO",memberFormDTO);
        return "members/t_member22";
    }

    @PostMapping(value = "/register")
    public String signIn(Model model,@Valid MemberFormDTO memberFormDTO, BindingResult bindingResult){
        System.err.println("memberController.register()진입");
        System.err.println(memberFormDTO);

        try{
            if(bindingResult.hasErrors()){
                System.err.println("mainhomeerr");
                return "members/t_member22";
            }
            Member member = Member.createMember(memberFormDTO,passwordEncoder);
            System.err.println(member);
            Member savedMember = memberService.saveMember(member);


        }catch (Exception e){
            e.printStackTrace();
            System.out.println("MemberController./register 중 예외 발생");
            model.addAttribute("errorMessage", e.getMessage());
            return "members/t_login";
        }
        return "mainHome";
    }


    @GetMapping(value = "login/error")
    public String loginErrorPage(Model model){
        model.addAttribute("memberFormDTO",new MemberFormDTO());
        return "mainHome";
    }


}
