package com.mn.seoha.service;

import com.mn.entity.Member;
import com.mn.seoha.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

    private  final MemberRepository memberRepository;


    public Member saveMember(Member member){
        validateDuplicateMember(member);
        return memberRepository.save(member);
    }

    private void validateDuplicateMember(Member member){
//        if(null!=memberRepository.findById(member.getId())){
//            System.out.println("null!=findById");
//            throw new IllegalStateException("존재하는 이메일 입니다.");
//        }

        Member findMember = memberRepository.findById(member.getId());

        if(findMember != null){
            throw new IllegalStateException("존재하는 이메일 입니다.");
        }

    }


    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        System.err.println("Service.MemberService.loadUserByUsername");
        Member member = memberRepository.findById(id);
        if(member ==null){
            throw new UsernameNotFoundException(id);
        }
        return User.builder().username(member.getEmail()).password(member.getPassword()).roles(member.getMemberRole().toString()).build();
    }
}
