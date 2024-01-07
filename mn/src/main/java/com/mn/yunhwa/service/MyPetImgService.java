package com.mn.yunhwa.service;

import com.mn.CommonService.FileService;
import com.mn.entity.MyPetImg;
import com.mn.yunhwa.repository.MyPetImgRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

@Service
@Transactional
@RequiredArgsConstructor
public class MyPetImgService {

    @Value("${myPetImgLocation}")
    private String myPetImgLocation;

    private final MyPetImgRepository myPetImgRepository;
    private final FileService fileService;

    public void saveMyPetImg(MyPetImg myPetImg, MultipartFile myPetImgFile) throws Exception{
        String oriImgName = myPetImgFile.getOriginalFilename();
        String imgName = "";
        String imgUrl = "";

        if(!StringUtils.isEmpty(oriImgName)){
            imgName = fileService.uploadFile(myPetImgLocation,oriImgName,myPetImgFile.getBytes());
            imgUrl = "images/mypet/" + imgName;
        }

        myPetImg.updateMyPetImg(oriImgName,imgName,imgUrl);
        myPetImgRepository.save(myPetImg);
    }
}
