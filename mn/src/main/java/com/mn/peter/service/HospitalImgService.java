package com.mn.peter.service;

import com.mn.CommonService.FileService;
import com.mn.entity.HospitalImg;
import com.mn.peter.repository.HospitalImgRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HospitalImgService {

    private final HospitalImgRepository hospitalImgRepository;
    private final FileService fileService;

    public HospitalImg savedHospitalImg(Long hospitalId, MultipartFile hospitalFileImg){

        try{
            byte[] fileData = hospitalFileImg.getBytes();

        }catch (IOException e){
            e.printStackTrace();
            System.err.println("HospitalImg저장 중 예외 발생");
        }
        return null;
    }


}
