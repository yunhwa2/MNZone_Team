package com.mn.seoha.service;

import com.mn.entity.Member;
import com.mn.seoha.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    MemberRepository memberRepository;


    public Member createMember(Member member){
        validateMember(member);
        return memberRepository.save(member);
    }

    public void validateMember(Member member){
        if(null!=memberRepository.findById(member.getId())){
            System.out.println("null!=findById");
            throw new IllegalStateException("존재하는 이메일 입니다.");
        }
    }


}
