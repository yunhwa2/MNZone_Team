package com.mn.yunhwa.dto;

import com.mn.entity.MissingImg;
import org.modelmapper.ModelMapper;

public class MissingImgDTO {
    private Long missingImgId;
    private String missingImgName;
    private String missingOriImgName;
    private String missingImgUrl;
    private String missingRepImgYn;

    private static ModelMapper modelMapper = new ModelMapper();

    public static MissingImgDTO of(MissingImg missingImg){
        return modelMapper.map(missingImg,MissingImgDTO.class);
    }
}
