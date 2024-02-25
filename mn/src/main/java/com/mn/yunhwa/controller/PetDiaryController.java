package com.mn.yunhwa.controller;

import com.mn.entity.Member;
import com.mn.entity.MissingComment;
import com.mn.entity.MyPet;
import com.mn.entity.MyPetDiary;
import com.mn.seoha.service.MemberService;
import com.mn.yunhwa.dto.*;
import com.mn.yunhwa.service.MyPetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class PetDiaryController {

    private final MyPetService myPetService;


    @GetMapping("/mypet/alldiary")
    public String petDiarymain(MyPetSearchDTO myPetSearchDTO, HttpSession session, Model model, @ModelAttribute("error") String errorMessage){
        Object memberCodeObject = session.getAttribute("memberCode");
        Long memberCode = memberCodeObject != null ? Long.valueOf(memberCodeObject.toString()) : null;

        List<MyPetMainDTO> myPetList = myPetService.getAllMyPets(myPetSearchDTO,memberCode);

        model.addAttribute("mypets",myPetList);

        if (errorMessage != null && !errorMessage.isEmpty()) {
            model.addAttribute("error", errorMessage);
        }

        return "yunhwa/allDiary";

    }

    @GetMapping("/mypet/diary/{myPetId}")
    public String petDiaryDtl(@PathVariable("myPetId") Long myPetId, Model model){
        MyPetFormDTO myPetFormDTO = myPetService.getMyPetDtl(myPetId);
        model.addAttribute("myPet", myPetFormDTO);

        List<MyPetDiary> myPetDiary = myPetService.getMyPetDiaryDtlByMyPetId(myPetId);
        model.addAttribute("myPetDiaries", myPetDiary);

        return "yunhwa/petDiary";
    }

    @PostMapping("/saveDiary")
    @ResponseBody
    public String saveDiary(@RequestBody MyPetDiaryDTO myPetDiaryDTO) {
        Member member = new Member();
        member.setCode(myPetDiaryDTO.getMemberCode());
        myPetDiaryDTO.setMember(member);

        MyPet myPet = new MyPet();
        myPet.setMyPetId(myPetDiaryDTO.getMyPetId());
        myPetDiaryDTO.setMyPet(myPet);

        myPetService.saveMyPetDiary(myPetDiaryDTO);

        // 데이터를 저장하고 성공적으로 저장되었다는 응답을 클라이언트에게 전송합니다.
        return "redirect:/mypet/diary/" + myPetDiaryDTO.getMyPetId();
    }

}
