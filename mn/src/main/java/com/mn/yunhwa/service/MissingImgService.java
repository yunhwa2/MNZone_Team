//package com.mn.yunhwa.service;
//
//import com.mn.CommonService.FileService;
//import com.mn.entity.MissingImg;
//import com.mn.yunhwa.repository.MissingImgRepository;
//import lombok.RequiredArgsConstructor;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.multipart.MultipartFile;
//import org.thymeleaf.util.StringUtils;
//
//import javax.persistence.EntityNotFoundException;
//
//@Service
//@Transactional
//@RequiredArgsConstructor
//public class MissingImgService {
//
//    @Value("${missingImgLocation}")
//    private String missingImgLocation;
//
//    private final MissingImgRepository missingImgRepository;
//
//    private final FileService fileService;
//
//    public void saveMissingImg(MissingImg missingImg, MultipartFile missingImgFile) throws Exception{
//        String missingOriImgName = missingImgFile.getOriginalFilename();
//        String missingImgName = "";
//        String missingImgUrl = "";
//
//        if(!StringUtils.isEmpty(missingOriImgName)){
//            missingImgName = fileService.uploadFile(missingImgLocation, missingOriImgName, missingImgFile.getBytes());
//            missingImgUrl="/images/missing/" + missingImgName;
//        }
//
//        missingImg.updateMissingImg(missingOriImgName,missingImgName,missingImgUrl);
//        missingImgRepository.save(missingImg);
//    }
//
//    public void updateMissingImg(Long missingImgId, MultipartFile missingImgFile) throws Exception{
//        if(!missingImgFile.isEmpty()){
//            MissingImg savedMissingImg = missingImgRepository.findById(missingImgId).orElseThrow(EntityNotFoundException::new);
//
//            if(!StringUtils.isEmpty(savedMissingImg.getMissingImgName())){
//                fileService.deleteFile(missingImgLocation+"/"+savedMissingImg.getMissingImgName());
//            }
//
//            String missingOriImgName = missingImgFile.getOriginalFilename();
//            String missimgImgName = fileService.uploadFile(missingImgLocation,missingOriImgName,missingImgFile.getBytes());
//            String missingImgUrl = "/images/missing/"+missimgImgName;
//
//            savedMissingImg.updateMissingImg(missingOriImgName,missimgImgName,missingImgUrl);
//        }
//    }
//}
