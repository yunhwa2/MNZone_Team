package com.mn.seoha.service;

import com.mn.entity.Member;
import com.mn.seoha.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    MemberRepository memberRepository;


    public Member saveMember(Member member){
        validateMember(member);
        return memberRepository.save(member);
    }

    private void validateMember(Member member){
//        if(null!=memberRepository.findById(member.getId())){
//            System.out.println("null!=findById");
//            throw new IllegalStateException("존재하는 이메일 입니다.");
//        }

        Member findMember = memberRepository.findById(member.getId());

        if(findMember != null){
            throw new IllegalStateException("존재하는 이메일 입니다.");
        }

    }


}
