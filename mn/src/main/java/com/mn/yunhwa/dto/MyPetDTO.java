package com.mn.yunhwa.dto;

import com.mn.constant.MyPetCategory;
import com.mn.entity.Member;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class MyPetDTO {

    private Long petCode;

    private String name;

    private MyPetCategory myPetCategory;

    private LocalDateTime birth;

    private String weight;

    private boolean gender;

    private boolean neuterIsYn;

    private String kind;

    private Member member;


    public
}
