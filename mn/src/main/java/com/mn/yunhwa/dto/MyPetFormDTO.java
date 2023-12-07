package com.mn.yunhwa.dto;

import com.mn.constant.MyPetCategory;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Getter @Setter
public class MyPetFormDTO {

    @NotBlank(message = "멍냥이의 이름을 알려주세요")
    private String name;

    @NotBlank
    private MyPetCategory myPetCategory;

    @NotEmpty
    private LocalDateTime birth;

    @NotEmpty
    @Length(min=4, max=16, message = "멍냥이의 몸무게를 알려주세요")
    private String weight;

    @NotEmpty
    private boolean gender;

    @NotEmpty
    private boolean neuterIsYn;

    @NotEmpty
    private String kind;


}
