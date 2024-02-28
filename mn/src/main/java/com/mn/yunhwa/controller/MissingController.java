package com.mn.yunhwa.controller;

import com.mn.entity.Missing;
import com.mn.entity.MissingComment;
import com.mn.yunhwa.dto.MissingCommentDTO;
import com.mn.yunhwa.dto.MissingFormDTO;
import com.mn.yunhwa.dto.MissingMainDTO;
import com.mn.yunhwa.dto.MissingSearchDTO;
import com.mn.yunhwa.service.MissingService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class MissingController {

    private final MissingService missingService;

    @GetMapping("/missing/write")
    public String missingForm(Model model, HttpSession session) {
        model.addAttribute("missingFormDTO", new MissingFormDTO());
        Object memberCode = session.getAttribute("memberCode");

        if (memberCode != null) {
            model.addAttribute("memberCode", memberCode);
        } else {
            return "/members/login";
        }
        return "yunhwa/missingForm";
    }

    @PostMapping("/missing/write")
    public String missingNew(@Valid MissingFormDTO missingFormDTO, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "yunhwa/missingForm";
        }

        try {
            String saveYn = missingService.saveMissing(missingFormDTO);
            if (saveYn == "N") {
                model.addAttribute("errorMessage", "사진 1장은 필수입니다.");
                return "yunhwa/missingForm";
            } else {
                return "redirect:/missing";
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", "실종글 등록 중에 에러가 발생하였습니다.");
            return "yunhwa/missingForm";
        }
    }

    @GetMapping("/missing/write/{missingId}")
    public String missingDtl(@PathVariable("missingId") Long missingId, Model model) {

        try {
            MissingFormDTO missingFormDTO = missingService.getMissingDtl(missingId);
            model.addAttribute("missingFormDTO", missingFormDTO);
        } catch (EntityNotFoundException e) {
            model.addAttribute("errorMessage", "존재하지 않는 글입니다.");
            model.addAttribute("missingFormDTO", new MissingFormDTO());
            return "yunhwa/missingForm";
        }
        return "yunhwa/missingForm";
    }

    @PostMapping("missing/write/{missingId}")
    public String missingUpdate(@Valid MissingFormDTO missingFormDTO, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "yunhwa/missingForm";
        }

        try {
            String updateMissingId = missingService.updateMissing(missingFormDTO);
            return "redirect:/missing";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "글 수정 중 에러가 발생하였습니다.");
            return "yunhwa/missingForm";
        }
    }

    @GetMapping(value = {"/missing", "/missing/{page}"})
    public String missingMain(MissingSearchDTO missingSearchDTO, @PathVariable("page") Optional<Integer> page, Model model) {
        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 9);
        Page<MissingMainDTO> missingMainDTOS = missingService.getMissingMainPage(missingSearchDTO, pageable);
        model.addAttribute("missings", missingMainDTOS);
        model.addAttribute("missingSearchDTO", missingSearchDTO);
        model.addAttribute("total", missingMainDTOS.getTotalElements());
        long missingCount = missingService.countMissing();
        model.addAttribute("missingCount", missingCount);
        model.addAttribute("maxPage", 5);

        return "yunhwa/missing";
    }

    @GetMapping("/missing/content/{missingId}")
    public String missingDtl(Model model, @PathVariable("missingId") Long missingId) {
        MissingFormDTO missingFormDTO = missingService.getMissingDtl(missingId);
        model.addAttribute("missing", missingFormDTO);

        // 기존 댓글 목록 설정
        List<MissingComment> comments = missingService.getCommentsByMissingId(missingId);
        model.addAttribute("comments", comments);

        // 새로운 댓글 객체 생성 (폼 처리용)
        model.addAttribute("newComment", new MissingComment());


        return "yunhwa/missingDtl";
    }


    @PostMapping("/missing/content/{missingId}")
    public String addComment(@ModelAttribute("newComment") MissingCommentDTO newComment, @PathVariable Long missingId) {
        try {
            // 새로운 댓글을 저장하고, 저장 후 리다이렉트할 페이지 URL 반환
            Missing missing = missingService.getMissingByMissingId(missingId);
            newComment.setMissing(missing);
            missingService.saveComment(newComment);
            return "redirect:/missing/content/" + missingId;

        }catch (Exception e){
            System.out.println("실종 댓글 작성중 예외 발생");
            e.printStackTrace();
            return "redirect:/missing";
        }

    }

    @GetMapping("/missing/delete")
    public String missingDelete(Long missingId) {
        missingService.deleteByMissingId(missingId);
        return "redirect:/missing";
    }


    @GetMapping("/missing/content/comment/delete")
    public String missingCommentDelete(Long missingCommentId, Long missingId) {
        missingService.deleteByMissingCommentId(missingCommentId);
        return "redirect:/missing/content/" + missingId;
    }

    @PostMapping("/missing/content/comment/update")
    public String missingCommentUpdate(@Valid MissingCommentDTO missingCommentDTO, BindingResult bindingResult, Model model, Long missingId) {
        missingService.updateByMissingCommentId(missingCommentDTO.getMissingCommentId(),missingCommentDTO.getMissingComment());
        return "redirect:/missing/content/" + missingId;
    }




}