package com.mn.entity;


import com.mn.constant.MemberRole;
import com.mn.constant.OAuthType;
import com.mn.constant.Role;
import com.mn.seoha.dto.MemberFormDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    @Column (unique = true)
    private String nickName;

    @Column (unique = true)
    private String email;

    private LocalDateTime birth;

    private String ph;

    private String address;

    @Enumerated(EnumType.STRING)
    private MemberRole memberRole;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private OAuthType oauth;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "parent_member_code")
    private Member member;

    @Column
    private String memberImgUrl;



    public static Member createMember(MemberFormDTO memberFormDTO, PasswordEncoder passwordEncoder){
        Member member = new Member();
        System.out.println("entitiy.createMember(memberFormDTO:) : " + memberFormDTO);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-ddHHmm");

        member.setId(memberFormDTO.getId());
        member.setMemberRole(MemberRole.USER);
        String password = passwordEncoder.encode(memberFormDTO.getPassword());
        member.setPassword(password);
        member.setPh(memberFormDTO.getPh());
        member.setAddress(memberFormDTO.getSido()+memberFormDTO.getGugun());
        member.setBirth(LocalDateTime.parse((memberFormDTO.getYear()+"-"+memberFormDTO.getMonth()+"-"+memberFormDTO.getDay()+"0000"),formatter));
        member.setEmail(memberFormDTO.getEmail());
        member.setName(memberFormDTO.getName());
        member.setNickName(memberFormDTO.getNickName());
        member.setMemberImgUrl(memberFormDTO.getMemberImgUrl());
        //member.setRole(Role.USER);
        member.setRole(Role.ADMIN);

        return member;
    }

}
