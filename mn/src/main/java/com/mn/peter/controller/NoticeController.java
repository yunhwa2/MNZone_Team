package com.mn.peter.controller;

import com.mn.peter.dto.NoticeFormDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/notice")
public class NoticeController {


    @GetMapping(value = "/new")
    public String newNotice(Model model, Principal principal){
        NoticeFormDTO noticeFormDTO = new NoticeFormDTO();
        noticeFormDTO.setNoticeAuthor(principal.getName());
        model.addAttribute("noticeFormDTO", noticeFormDTO);

        return "Jiseong/notice/newNotice";
    }

}
