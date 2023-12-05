package com.mn.peter.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Repository;

@Repository
@Getter
@Setter
@ToString
public class NoticeFormDTO {

    private String noticeAuthor;
    private String noticeTitle;
    private String noticeContent;

}
