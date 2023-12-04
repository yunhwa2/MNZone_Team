package com.mn.entity;


import com.mn.constant.MemberRole;
import com.mn.seoha.dto.MemberFormDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "member")
@Getter
@Setter
@ToString
public class Member {
    @Id
    @Column(name = "member_code")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long code;
    @Column (unique = true)
    private String id;
    private String password;
    private String name;
    //    닉네임 유니크 설정 여부
    private String nickName;
    //    email 유니크 설정 여부
    private String email;
    private LocalDateTime birth;
    private String ph;
    private String address;
    @Enumerated(EnumType.STRING)
    private MemberRole memberRole;

    public Member createMember(MemberFormDTO memberFormDTO, PasswordEncoder passwordEncoder){
        Member member = new Member();

        member.setId(memberFormDTO.getId());
        member.setMemberRole(MemberRole.USER);
        return null;//수정
    }

}
