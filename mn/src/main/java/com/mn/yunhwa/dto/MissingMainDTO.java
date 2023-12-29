package com.mn.yunhwa.dto;

import com.mn.constant.MissingKind;
import com.mn.entity.Member;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter@Setter
public class MissingMainDTO {

    private Long missingId;
    private MissingKind missingKind;
    private String missingTitle;
    private String missingContent;
    private Member member;
    private String missingRepImg;
    private String sightingSpot;
    private String sightingDate;
    private String witnessTel;
    private String feature;

    @QueryProjection
    public MissingMainDTO(Long missingId, MissingKind missingKind, String missingTitle, String missingContent, Member member,
                          String missingRepImg,String sightingSpot, String sightingDate, String witnessTel,String feature) {
        this.missingId = missingId;
        this.missingKind = missingKind;
        this.missingTitle = missingTitle;
        this.missingContent = missingContent;
        this.member = member;
        this.missingRepImg=missingRepImg;
        this.sightingSpot = sightingSpot;
        this.sightingDate = sightingDate;
        this.witnessTel = witnessTel;
        this.feature = feature;
    }
}
