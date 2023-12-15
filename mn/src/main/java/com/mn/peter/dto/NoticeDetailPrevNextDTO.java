package com.mn.peter.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class NoticeDetailPrevNextDTO {
    private Long noticeId;
    private String noticeTitle;
    private LocalDateTime regTime;

    @QueryProjection
    public NoticeDetailPrevNextDTO(Long noticeId, String title, LocalDateTime regTime){
        this.noticeId = noticeId;
        this.noticeTitle = title;
        this.regTime = regTime;
    }


}
