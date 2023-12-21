package com.mn.yunhwa.controller;

import com.mn.entity.Missing;
import com.mn.yunhwa.dto.MissingFormDTO;
import com.mn.yunhwa.dto.MissingMainDTO;
import com.mn.yunhwa.dto.MissingSearchDTO;
import com.mn.yunhwa.service.MissingService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class MissingController {

    private final MissingService missingService;

//    @GetMapping("/missing/main")
//    public String missingmain(){
//        return "yunhwa/missing";
//    }

    @GetMapping("/missing/write")
    public String missingForm(Model model){
        model.addAttribute("missingFormDTO", new MissingFormDTO());
        return "yunhwa/missingForm";
    }

    @PostMapping("/missing/write")
    public String missingNew(@Valid MissingFormDTO missingFormDTO, BindingResult bindingResult, Model model, @RequestParam("missingImgFile")List<MultipartFile> missingImgFileList){
            if(bindingResult.hasErrors()){
            return "yunhwa/missingForm";
        }

        if(missingImgFileList.get(0).isEmpty() && missingFormDTO.getMissingId() == null){
            model.addAttribute("errorMessage","사진 한장은 필수입니다.");
            return "yunhwa/missingForm";
        }

        try{
            Long missingId = missingService.saveMissing(missingFormDTO, missingImgFileList);
            return "redirect:/missing";
        }catch (Exception e){
            model.addAttribute("errorMessage","실종글 등록 중에 에러가 발생하였습니다.");
            return "yunhwa/missingForm";
        }
    }

    @GetMapping("/missing/write/{missingId}")
    public String missingDtl(@PathVariable("missingId") Long missingId, Model model){

        try{
            MissingFormDTO missingFormDTO = missingService.getMissingDtl(missingId);
            model.addAttribute("missingFormDTO",missingFormDTO);
        }catch (EntityNotFoundException e){
            model.addAttribute("errorMessage","존재하지 않는 글입니다.");
            model.addAttribute("missingFormDTO",new MissingFormDTO());
            return "yunhwa/missingForm";
        }
        return "yunhwa/missingForm";
    }

    @PostMapping("missing/write/{missingId}")
    public String missingUpdate(@Valid MissingFormDTO missingFormDTO, BindingResult bindingResult, @RequestParam("missingImgFile") List<MultipartFile> missingImgFileList, Model model){
        if(bindingResult.hasErrors()){
            return "yunhwa/missingForm";
        }

        if(missingImgFileList.get(0).isEmpty() && missingFormDTO.getMissingId() == null){
            model.addAttribute("errorMessage","사진 한장은 필수입니다.");
            return "yunhwa/missingForm";
        }

        try {
            long updateMissingId = missingService.updateMissing(missingFormDTO,missingImgFileList);
            return "redirect:/missing";
        }catch (Exception e){
            model.addAttribute("errorMessage","글 수정 중 에러가 발생하였습니다.");
            return "yunhwa/missingForm";
        }
    }


    @GetMapping(value = "/missing")
    public String missingMain(MissingSearchDTO missingSearchDTO, Optional<Integer> page, Model model){
        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0,9);
        Page<MissingMainDTO> missingMainDTOS = missingService.getMissingMainPage(missingSearchDTO,pageable);
        model.addAttribute("missings",missingMainDTOS);
        model.addAttribute("missingSearchDTO",missingSearchDTO);

        long missingCount = missingService.countMissing();
        model.addAttribute("missingCount", missingCount);
        model.addAttribute("maxPage",9);

        return "yunhwa/missing";
    }

    @GetMapping("/missing/{missingId}")
    public String missingDtl(Model model, @PathVariable("missingId") Long missingId){
        MissingFormDTO missingFormDTO = missingService.getMissingDtl(missingId);
        model.addAttribute("missing",missingFormDTO);
        return "yunhwa/missingDtl";
    }



}
