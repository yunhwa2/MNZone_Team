package com.mn.peter.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotBlank;

@Repository
@Getter
@Setter
@ToString
public class NoticeFormDTO {
    private final String writeClassField = "공지등록";

    private String noticeAuthor;
    @NotBlank(message = "제목을 입력 해주세요.")
    private String noticeTitle;
    @NotBlank(message = "내용을 입력 해주세요.")
    private String noticeContent;

}
