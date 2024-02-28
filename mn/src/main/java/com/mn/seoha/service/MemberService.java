package com.mn.seoha.service;

import com.mn.CommonService.FileService;
import com.mn.entity.Member;
import com.mn.seoha.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import java.io.IOException;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {
    @Value("${memberImgLocation}")
    private String memberImgLocation;
    private static MemberRepository memberRepository;

    private final FileService fileService;

    @Autowired
    public MemberService(MemberRepository memberRepository, FileService fileService) {
        this.memberRepository = memberRepository;
        this.fileService = fileService;
    }

    public Member saveMember(Member member, MultipartFile memberImgFile) throws Exception {
        validateDuplicateMember(member);

        String oriImgName = memberImgFile.getOriginalFilename();
        String imgName = "";
        String imgUrl = "";

        if(!StringUtils.isEmpty(oriImgName)){
            imgName = fileService.uploadFile(memberImgLocation,oriImgName,memberImgFile.getBytes());
            imgUrl = "images/member/" + imgName;
            member.setMemberImgUrl(imgUrl);
        }

        return memberRepository.save(member);
    }

    private void validateDuplicateMember(Member member){

        Member findMember = memberRepository.findById(member.getId());

        if(findMember != null){
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }


    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        Member member = memberRepository.findById(id);
        if(member == null){
            throw new UsernameNotFoundException(id);
        }
        return User.builder()
                .username(member.getId())
                .password(member.getPassword())
                .roles(member.getMemberRole().toString())
                .build();
    }

    public static Member findByUsername(String username) {
        return memberRepository.findById(username);
    }
}
