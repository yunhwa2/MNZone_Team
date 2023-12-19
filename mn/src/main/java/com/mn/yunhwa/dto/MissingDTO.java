package com.mn.yunhwa.dto;

import com.mn.constant.MissingKind;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class MissingDTO {

    private Long missingId;
    private MissingKind missingKind;
    private String missingTitle;
    private String missingContent;
    private String missingImgUrl;

}
