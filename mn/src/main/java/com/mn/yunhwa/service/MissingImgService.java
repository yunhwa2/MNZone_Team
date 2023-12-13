package com.mn.yunhwa.service;

import com.mn.entity.MissingImg;
import com.mn.service.FileService;
import com.mn.yunhwa.repository.MissingImgRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

@Service
@Transactional
@RequiredArgsConstructor
public class MissingImgService {

    @Value("${missingImgLocation}")
    private String missingImgLocation;

    private final MissingImgRepository missingImgRepository;

    private final FileService fileService;

    public void saveMissingImg(MissingImg missingImg, MultipartFile missingImgFile) throws Exception{
        String missingOriImgName = missingImgFile.getOriginalFilename();
        String missingImgName = "";
        String missingImgUrl = "";

        if(!StringUtils.isEmpty(missingOriImgName)){
            missingImgName = fileService.uploadFile(missingImgLocation, missingOriImgName, missingImgFile.getBytes());
            missingImgUrl="/images/missing/" + missingImgName;
        }

        missingImg.updateMissingImg(missingOriImgName,missingImgName,missingImgUrl);
        missingImgRepository.save(missingImg);
    }
}
