package com.mn.yunhwa.service;

import com.mn.CommonService.FileService;
import com.mn.constant.MissingKind;
import com.mn.entity.*;
import com.mn.seoha.repository.MemberRepository;
import com.mn.yunhwa.dto.*;
import com.mn.yunhwa.repository.MyPetRepository;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Transactional
@RequiredArgsConstructor
public class MyPetService {

    @Value("${myPetImgLocation}")
    private String myPetImgLocation;

    private final MyPetRepository myPetRepository;
    private final MemberRepository memberRepository;

    private final FileService fileService;



    public Long savedMyPet(MyPetFormDTO myPetFormDTO, MultipartFile myPetImgFile)throws Exception{
        MyPet myPet = myPetFormDTO.createMyPet();

        String oriImgName = myPetImgFile.getOriginalFilename();
        String imgName = "";
        String imgUrl = "";

        if(!StringUtils.isEmpty(oriImgName)){
            imgName = fileService.uploadFile(myPetImgLocation,oriImgName,myPetImgFile.getBytes());
            imgUrl = "images/mypet/" + imgName;
            myPet.setMyPetImgUrl(imgUrl);
        }

        myPetRepository.save(myPet);

        return myPet.getMyPetId();
    }

    @Transactional(readOnly = true)
    public MyPetFormDTO getMyPetDtl(Long myPetId){
        MyPet myPet = myPetRepository.findById(myPetId).orElseThrow(EntityNotFoundException::new);
        MyPetFormDTO myPetFormDTO = MyPetFormDTO.of(myPet);
        return myPetFormDTO;
    }

    public void updateMyPet(MyPetFormDTO myPetFormDTO,MultipartFile myPetImgFile) throws Exception {
        MyPet myPet = myPetRepository.findById(myPetFormDTO.getMyPetId())
                .orElseThrow(EntityNotFoundException::new);
        myPet.setMyPetCategory(myPetFormDTO.getMyPetCategory());
        myPet.setMyPetName(myPetFormDTO.getMyPetName());
        myPet.setMyPetBirth(myPetFormDTO.getMyPetBirth());
        myPet.setMyPetWeight(myPetFormDTO.getMyPetWeight());
        myPet.setMyPetGender(myPetFormDTO.getMyPetGender());
        myPet.setMyPetNeuter(myPetFormDTO.getMyPetNeuter());
        myPet.setMyPetImgUrl(myPetFormDTO.getMyPetImgUrl());
        myPet.setMyPetKind(myPetFormDTO.getMyPetKind());

        String oriImgName = myPetImgFile.getOriginalFilename();
        String imgName = "";
        String imgUrl = "";

        if(!StringUtils.isEmpty(oriImgName)){
            imgName = fileService.uploadFile(myPetImgLocation,oriImgName,myPetImgFile.getBytes());
            imgUrl = "images/mypet/" + imgName;
            myPet.setMyPetImgUrl(imgUrl);
        }

        myPetRepository.save(myPet);
    }


    @Transactional(readOnly = true)
    public List<MyPetMainDTO> getAllMyPets(MyPetSearchDTO myPetSearchDTO, Long memberCode){
        return myPetRepository.getAllMyPets( myPetSearchDTO,  memberCode);
    }


}
