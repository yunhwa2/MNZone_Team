package com.mn.yunhwa.dto;

import com.mn.entity.Member;
import com.mn.entity.MissingComment;
import com.mn.entity.MyPet;
import com.mn.entity.MyPetDiary;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@Setter
@Getter
public class MyPetDiaryDTO {

    private Long myPetDiaryId;
    private Long myPetId;
    private Long memberCode;
    private MyPet myPet;
    private Member member;
    private String myPetTitle;
    private String myPetStart;
    private String myPetEnd;
    private String myPetContent;

    private static ModelMapper modelMapper = new ModelMapper();

    public MyPetDiary createMyPetDiary(){

        return modelMapper.map(this, MyPetDiary.class);
    }
}
