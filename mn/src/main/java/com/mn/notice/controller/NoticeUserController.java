package com.mn.notice.controller;

import com.mn.notice.dto.NoticeDetailFormDTO;
import com.mn.notice.dto.NoticeListFormDTO;
import com.mn.notice.dto.NoticeSearchDTO;
import com.mn.notice.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/notice")
public class NoticeUserController {

    private final NoticeService noticeService;


    @GetMapping(value = {"/list", "/list/{page}"})
    public String notice(NoticeSearchDTO noticeSearchDTO,@PathVariable("page")Optional<Integer> page,Model model){
        System.out.println("getmapping notice");
        Pageable pageable = PageRequest.of(page.isPresent()?page.get():0,15);

//        Page<Notice> notices
//                =noticeService.getNoticePage2(noticeSearchDTO,pageable);
        Page<NoticeListFormDTO> notices
                =noticeService.getNoticePage(noticeSearchDTO,pageable);

        model.addAttribute("notices",notices);
        model.addAttribute("noticeSearchDTO",noticeSearchDTO);
        model.addAttribute("maxPage",5);
//        System.out.println("notices : "+notices.getTotalElements());
        return "notice/noticeList";
    }

    @GetMapping(value = "/detail/{noticeId}")
    public String noticeDetail(Model model,@PathVariable("noticeId") Long noticeId){

        noticeService.plusVisitCount(noticeId);

        NoticeDetailFormDTO noticeDetailFormDTO = noticeService.getNoticeDetail(noticeId);
        model.addAttribute("noticeDetailFormDTO", noticeDetailFormDTO);
        return "notice/noticeDetail";
    }
}
