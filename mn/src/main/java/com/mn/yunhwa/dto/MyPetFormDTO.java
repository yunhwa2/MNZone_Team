package com.mn.yunhwa.dto;

import com.mn.constant.MyPetCategory;
import com.mn.constant.MyPetGender;
import com.mn.constant.MyPetNeuter;
import com.mn.entity.Member;
import com.mn.entity.Missing;
import com.mn.entity.MyPet;
import com.mn.entity.MyPetImg;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.UniqueElements;
import org.modelmapper.ModelMapper;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter @Setter
public class MyPetFormDTO {

    private Long MyPetId;

    @NotBlank(message = "멍냥이의 이름을 알려주세요")
    private String myPetName;

    @NotNull
    private MyPetCategory myPetCategory;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate myPetBirth;

    @NotEmpty
    @Length(min=4, max=16, message = "멍냥이의 몸무게를 알려주세요")
    private String myPetWeight;

    @NotNull
    private MyPetGender myPetGender;

    @NotNull
    private MyPetNeuter myPetNeuter;

    @NotEmpty
    private String myPetKind;

    private String myPetImgUrl;

//    private MyPetImgDTO myPetImgDTO;
//
//    private Long myPetImgId;

    private Long memberCode;

    private Member member;

    private static ModelMapper modelMapper = new ModelMapper();

    public MyPet createMyPet() {
        MyPet myPet = modelMapper.map(this, MyPet.class);
//        if (myPetImgDTO != null) {
//            MyPetImg myPetImg = modelMapper.map(myPetImgDTO, MyPetImg.class);
//            myPet.setMyPetImg(myPetImg);
//        }

        Member member = new Member();
        member.setCode(this.getMemberCode());
        myPet.setMember(member);

        return myPet;
    }

    public static MyPetFormDTO of(MyPet myPet){
        MyPetFormDTO myPetFormDTO = modelMapper.map(myPet,MyPetFormDTO.class);
        myPetFormDTO.setMemberCode(myPet.getMember().getCode());
//        if(myPet.getMyPetImg() != null){
//            myPetFormDTO.setMyPetImgDTO(modelMapper.map(myPet.getMyPetImg(), MyPetImgDTO.class));
//            myPetFormDTO.setMyPetImgId(myPet.getMyPetImg().getMyPetImgId());
//        }
        return myPetFormDTO;
    }

}
