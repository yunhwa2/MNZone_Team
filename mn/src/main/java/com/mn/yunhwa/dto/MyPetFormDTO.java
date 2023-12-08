package com.mn.yunhwa.dto;

import com.mn.constant.MyPetCategory;
import com.mn.constant.MyPetGender;
import com.mn.constant.MyPetNeuter;
import com.mn.entity.MyPet;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.UniqueElements;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter @Setter
public class MyPetFormDTO {

    @NotBlank(message = "멍냥이의 이름을 알려주세요")
    private String myPetName;

    @NotBlank
    private MyPetCategory myPetCategory;

    @NotEmpty
    private LocalDate myPetBirth;

    @NotEmpty
    @Length(min=4, max=16, message = "멍냥이의 몸무게를 알려주세요")
    private String myPetWeight;

    @NotEmpty
    private MyPetGender myPetGender;

    @NotEmpty
    private MyPetNeuter myPetNeuter;

    @NotEmpty
    private String myPetKind;

    private static ModelMapper modelMapper = new ModelMapper();

    public MyPet createMyPet(){
        return modelMapper.map(this,MyPet.class);
    }
}
