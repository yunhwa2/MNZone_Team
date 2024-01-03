package com.mn.yunhwa.dto;

import com.mn.constant.MissingKind;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MissingSearchDTO {

    private String searchDateType;

    private MissingKind searchMissingKind;

    private String searchMissingBy;

    private String searchMissingQuery="";



}
