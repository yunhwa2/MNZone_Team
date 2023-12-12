package com.mn.peter.controller;

import com.mn.entity.Hospital;
import com.mn.peter.dto.HospitalFormDTO;
import com.mn.peter.service.HospitalImgService;
import com.mn.peter.service.HospitalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/hospital")
public class DoctorController {

    private final HospitalService hospitalService;
    private final HospitalImgService hospitalImgService;

    @GetMapping(value = "admin/new")
    public String saveHospitalToView(Model model){
        model.addAttribute("hospitalFormDTO", new HospitalFormDTO());
        return "";
    }
    @PostMapping(value = "admin/new")
    public String savedHospitalToController(@Valid HospitalFormDTO hospitalFormDTO,
                                            BindingResult bindingResult,
                                            Model model, List<MultipartFile> hospitalImgList){
        if(bindingResult.hasErrors()){
            return "";
        }
        Hospital savedHospital = hospitalService.saveHospital(hospitalFormDTO,hospitalImgList);
        System.err.println("DoctorController.savedHospital.id : " + savedHospital.getHospitalCode());

        return "";
    }


}
