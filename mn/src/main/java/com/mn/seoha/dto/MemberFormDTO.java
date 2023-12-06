package com.mn.seoha.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Repository("memberFormDTO")
@Getter
@Setter
@ToString
public class MemberFormDTO {
    @NotBlank(message ="아이디를 입력해 주세요.")
    private String id;

    @NotBlank(message="이름을 입력해 주세요.")
    private String name;

    @NotBlank(message="닉네임을 입력해 주세요.")
    private String nickName;

    @NotEmpty(message="email을 입력해 주세요.")
    @Email(message="이메일 형식으로 입력해 주세요")
    private String email;

    @NotEmpty(message="비밀번호를 입력해 주세요.")
    @Length(min=4,max=16,message="비밀번호는 4글자 이상, 16글자 이하로 입력해 주세요")
    private String password;

    @NotEmpty(message="지역을 입력해 주세요.")
    private String sido;

    private String gugun;

    private String year;

    private String month;

    private String day;

    @NotEmpty(message="전화번호를 입력해주세요.")
    private String ph;


}
