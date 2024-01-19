package com.mn.yunhwa.controller;

import com.mn.yunhwa.dto.MyPetMainDTO;
import com.mn.yunhwa.dto.MyPetSearchDTO;
import com.mn.yunhwa.service.MyPetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

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

    @GetMapping("/mypet/diary")
    public String petDiaryDtl(){
        return "yunhwa/petDiary";
    }

}
