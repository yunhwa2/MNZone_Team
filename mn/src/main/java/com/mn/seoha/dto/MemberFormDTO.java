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
    @NotBlank
    private String id;

    @NotBlank
    private String name;

    @NotBlank
    private String nickName;

    @Email
    private String email;

    @NotEmpty
    @Length(min=4,max=12)
    private String password;

    @NotBlank
    private String sido;

    @NotBlank
    private String gugun;

    private String year;

    private String month;

    private String day;

    @NotEmpty
    private String ph;


}
