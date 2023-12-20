package com.mn.controller;

import com.mn.seoha.dto.MemberFormDTO;
import com.mn.yunhwa.dto.MissingMainDTO;
import com.mn.yunhwa.dto.MissingSearchDTO;
import com.mn.yunhwa.service.MissingService;
import jdk.jshell.spi.ExecutionControlProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.Optional;


@RequiredArgsConstructor
@Controller
@Transactional
public class MainController {

    private final MissingService missingService;



    @GetMapping(value = "/")
    public String main(Model model, Principal principal){

        try{
            System.err.println(principal.getName());
            String name = principal.getName();
            model.addAttribute("userName",name);
        }catch (Exception e){
            model.addAttribute("memberFormDTO", new MemberFormDTO());
            return "index";
        }
        model.addAttribute("memberFormDTO", new MemberFormDTO());
        return "index";
    }



    @GetMapping(value = "/notice")
    public String notice(){
        System.err.println("getmapping notice");
        return "Jiseong/notice/notice." +
                "";
    }



}
