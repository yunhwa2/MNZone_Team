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

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MyPetService {

    @Value("${myPetImgLocation}")
    private String myPetImgLocation;

    private final MyPetRepository myPetRepository;
    private final MemberRepository memberRepository;
    // private final MyPetImgRepository myPetImgRepository;
    // private final MyPetImgService myPetImgService;


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


//    private void validateDuplicateMyPet(MyPet myPet){
//        List<MyPet> findMyPet = myPetRepository.findByMyPetByNative(myPet.getMember());
//        if(findMyPet.size() >= 7){
//            throw new IllegalStateException("최대 6마리만 등록할 수 있습니다");
//        }
//    }

    @Transactional(readOnly = true)
    public List<MyPetMainDTO> getAllMyPets(MyPetSearchDTO myPetSearchDTO, Long memberCode){
        return myPetRepository.getAllMyPets( myPetSearchDTO,  memberCode);
    }

}
