package com.mn.peter.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoticeSearchDTO {
    private String noticeSearchKind;
    private String noticeSearchBy;
    private String noticeSearchQuery="";
}
