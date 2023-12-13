package com.mn.yunhwa.controller;

import com.mn.yunhwa.dto.MissingFormDTO;
import com.mn.yunhwa.service.MissingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MissingController {

    private final MissingService missingService;

    @GetMapping("/missing")
    public String missingmain(){
        return "yunhwa/missing";
    }

    @GetMapping("/missing/write")
    public String missingWrite(Model model){
        model.addAttribute("missingFormDTO", new MissingFormDTO());
        return "yunhwa/missing_write";
    }

    @PostMapping("/missing/write")
    public String missingNew(@Valid MissingFormDTO missingFormDTO, BindingResult bindingResult, Model model, @RequestParam("missingImgFile")List<MultipartFile> missingImgFileList){
        if(bindingResult.hasErrors()){
            return "yunhwa/missing_write";
        }

        if(missingImgFileList.get(0).isEmpty() && missingFormDTO.getMissingId() == null){
            model.addAttribute("errorMessage","사진 한장은 필수입니다.");
            return "yunhwa/missing_write";
        }

        try{
            missingService.saveMissing(missingFormDTO, missingImgFileList);
        }catch (Exception e){
            model.addAttribute("errorMessage","실종글 등록 중에 에러가 발생하였습니다.");
            return "yunhwa/missing_write";
        }
        return "yunhwa/missing";
    }


}
