package com.mn.notice.controller;

import com.mn.notice.dto.NoticeFormDTO;
import com.mn.notice.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/notice")
public class NoticeController {
    private final NoticeService noticeService;

    @GetMapping(value = "/new")
    public String newNotice(Model model, Principal principal){
        NoticeFormDTO noticeFormDTO = new NoticeFormDTO();
        noticeFormDTO.setNoticeAuthor(principal.getName());
        model.addAttribute("noticeFormDTO", noticeFormDTO);
        model.addAttribute("author",principal.getName());
        return "notice/noticeForm";
    }
    @PostMapping(value = "/new")
    public String saveNotice(Model model, Principal principal,
                             @Valid NoticeFormDTO noticeFormDTO, BindingResult bindingResult){
        String id = principal.getName();
        if(!noticeFormDTO.getNoticeAuthor().equals(id)){
            model.addAttribute("errorMessage","로그인 후 이용해주세요");
            return "notice/notice";
        }else if(bindingResult.hasErrors()){
            return "notice/noticeForm";
        }
        noticeService.saveNotice(noticeFormDTO);

        return"redirect:/notice/list";
    }

}
