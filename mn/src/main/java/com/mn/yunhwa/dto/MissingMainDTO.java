package com.mn.yunhwa.dto;

import com.mn.constant.MissingKind;
import com.mn.entity.Member;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class MissingMainDTO {

    private Long missingId;
    private MissingKind missingKind;
    private String missingTitle;
    private String missingContent;
    private String missingImgUrl;
    private Member member;

    @QueryProjection
    public MissingMainDTO(Long missingId, MissingKind missingKind, String missingTitle, String missingContent, String missingImgUrl) {
        this.missingId = missingId;
        this.missingKind = missingKind;
        this.missingTitle = missingTitle;
        this.missingContent = missingContent;
        this.missingImgUrl = missingImgUrl;
//        this.member = member;
    }
}
