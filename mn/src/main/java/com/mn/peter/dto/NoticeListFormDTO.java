package com.mn.peter.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
public class NoticeListFormDTO {
    private Long noticeId;
    private String createBy;
    private String noticeTitle;
    private String noticeContent;
    private LocalDateTime regTime;

    @QueryProjection
    public NoticeListFormDTO(Long noticeId,String noticeTitle, String noticeContent, String createBy, LocalDateTime regTime){
        System.err.println("쿼리 주입 : NoticeListFormDTO");
        this.noticeId = noticeId;
        this.noticeTitle = noticeTitle;
        this.noticeContent = noticeContent;
        this.createBy = createBy;
        this.regTime = regTime;
    }
}
