package com.mn.yunhwa.dto;

import com.mn.constant.MyPetCategory;
import com.mn.constant.MyPetGender;
import com.mn.constant.MyPetNeuter;
import com.mn.entity.Member;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Setter@Getter
public class MyPetMainDTO {

    private Long MyPetId;

    private String myPetName;

    private String myPetImgUrl;

    private Member member;

    @QueryProjection
    public MyPetMainDTO(Long myPetId, String myPetName, String myPetImgUrl, Member member) {
        this.MyPetId = myPetId;
        this.myPetName = myPetName;
        this.myPetImgUrl = myPetImgUrl;
        this.member = member;
    }


}
