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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.Optional;


@RequiredArgsConstructor
@Controller
@Transactional
public class MainController {

    private final MissingService missingService;

    @GetMapping(value = "/")
    public String main(Model model, Principal principal, HttpServletRequest httpServletRequest){

        try{

        }catch (Exception e){
            model.addAttribute("memberFormDTO", new MemberFormDTO());
            return "index";
        }
        model.addAttribute("memberFormDTO", new MemberFormDTO());
        return "index";
    }

    //이거 공지 컨트롤러롤 옮기기
    @GetMapping(value = "/notice")
    public String notice(){
        return "Jiseong/notice/notice." +
                "";
    }

    //

}
