package com.mn.Service;

import com.mn.constant.MyPetCategory;
import com.mn.constant.MyPetGender;
import com.mn.constant.MyPetNeuter;
import com.mn.entity.MyPet;
import com.mn.seoha.dto.MemberFormDTO;
import com.mn.seoha.service.MemberService;
import com.mn.yunhwa.dto.MyPetFormDTO;
import com.mn.yunhwa.service.MyPetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@SpringBootTest
@Transactional
@TestPropertySource(locations="classpath:application-test.properties")
public class MyPetServiceTest {

    @Autowired
    MyPetService myPetService;

    @Autowired
    MemberService memberService;

    @Autowired
    PasswordEncoder passwordEncoder;

    //하다 말았음
    public MyPet savedMyPet(MyPet myPet){
        MyPetFormDTO myPetFormDTO = new MyPetFormDTO();
        myPetFormDTO.setMyPetName("해피");
        myPetFormDTO.setMyPetCategory(MyPetCategory.DOG);
        myPetFormDTO.setMyPetBirth(LocalDate.of(2023,10,1));
        myPetFormDTO.setMyPetNeuter(MyPetNeuter.NEUTER_NO);
        myPetFormDTO.setMyPetGender(MyPetGender.MAN);
        myPetFormDTO.setMyPetWeight("2.7");
        myPetFormDTO.setMyPetKind("믹스견");

        return MyPet.createMyPet(myPetFormDTO);




    }
}
