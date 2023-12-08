package com.mn.peter.controller;

import com.mn.entity.Notice;
import com.mn.peter.dto.NoticeListFormDTO;
import com.mn.peter.dto.NoticeSearchDTO;
import com.mn.peter.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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
    public String notice(NoticeSearchDTO noticeSearchDTO,
                         @PathVariable("page")Optional<Integer> page,
                         Model model
                         ){
        System.err.println("getmapping notice");
        Pageable pageable = PageRequest.of(page.isPresent()?page.get():0,15);

//        Page<Notice> notices
//                =noticeService.getNoticePage2(noticeSearchDTO,pageable);
        Page<NoticeListFormDTO> notices
                =noticeService.getNoticePage(noticeSearchDTO,pageable);

        model.addAttribute("notices",notices);
        model.addAttribute("noticeSearchDTO",noticeSearchDTO);
        model.addAttribute("maxPage",5);
//        System.out.println("notices : "+notices.getTotalElements());
        return "Jiseong/notice/noticeList";
    }
}
