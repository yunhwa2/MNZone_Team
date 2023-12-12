package com.mn.peter.service;

import com.mn.constant.PmAm;
import com.mn.entity.Hospital;
import com.mn.peter.dto.HospitalFormDTO;
import com.mn.peter.repository.HospitalRepository;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.MultipartStream;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HospitalService {

    private final HospitalRepository hospitalRepository;
    private final HospitalImgService hospitalImgService;
    public boolean chkPmAm(PmAm pmAm){
        if(pmAm.equals(PmAm.AM)){
            return true;
        } return false;
    }
    public LocalTime TransToLocalTime(PmAm pmAm,Integer hour, Integer min){
           if(chkPmAm(pmAm)){
               return LocalTime.of(hour,min);
           }else{
               return LocalTime.of(hour+12,min);
           }
    }
    public Hospital saveHospital(HospitalFormDTO hospitalFormDTO, List<MultipartFile> hospitalImgList){
        Hospital hospital = new Hospital();
        hospital.setHospitalName(hospitalFormDTO.getHospitalName());
        hospital.setHospitalAddress(hospitalFormDTO.getHospitalAddress());
        hospital.setOpenTime(TransToLocalTime(hospitalFormDTO.getOpenPmAm(),hospitalFormDTO.getOpenTimeHour(),hospitalFormDTO.getOpenTimeMin()));
        hospital.setCloseTime(TransToLocalTime(hospitalFormDTO.getClosePmAm(),hospitalFormDTO.getCloseTimeHour(),hospitalFormDTO.getCloseTimeMin()));
        hospital.setTel(hospitalFormDTO.getTel());
        System.err.println("HospitalEntity 저장");
        Hospital savedHospital = hospitalRepository.save(hospital);
        for(MultipartFile m : hospitalImgList){
            hospitalImgService.savedHospitalImg(savedHospital.getHospitalCode(), m);
        }

        return savedHospital;
    }

}
