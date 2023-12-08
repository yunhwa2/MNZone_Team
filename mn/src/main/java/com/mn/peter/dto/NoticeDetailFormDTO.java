package com.mn.peter.dto;

import com.mn.constant.NoticeKind;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
public class NoticeDetailFormDTO {
    private String noticeTitle;
    private Long noticeId;
    private LocalDateTime noticeRegTime;
    private List<String> noticeContent;
    private NoticeKind noticeKind;
    private String createBy;
}
