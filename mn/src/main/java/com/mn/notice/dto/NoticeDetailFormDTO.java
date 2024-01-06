package com.mn.notice.dto;

import com.mn.constant.NoticeKind;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class NoticeDetailFormDTO {
    private String noticeTitle;
    private Long noticeId;
    private NoticeDetailPrevNextDTO prevDTO;
    private NoticeDetailPrevNextDTO nextDTO;
    private LocalDateTime noticeRegTime;
    //private List<String> noticeContent;
    private String noticeContent;
    private NoticeKind noticeKind;
    private String createBy;
    private Integer noticeVisitCount;
}
