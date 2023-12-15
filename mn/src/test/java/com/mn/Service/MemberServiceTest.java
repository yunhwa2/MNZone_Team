package com.mn.Service;

import com.mn.entity.Member;
import com.mn.seoha.dto.MemberFormDTO;
import com.mn.seoha.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
public class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    PasswordEncoder passwordEncoder;

    //테스트를 위한 멤버를 생성
    public Member createMember() {
        MemberFormDTO memberFormDTO = new MemberFormDTO();
        memberFormDTO.setId("test");
        memberFormDTO.setName("홍길동");
        memberFormDTO.setNickName("도사");
        memberFormDTO.setEmail("test@naver.com");
        memberFormDTO.setPassword("1234567");
        memberFormDTO.setSido("울산");
        memberFormDTO.setGugun("남구");
        memberFormDTO.setYear("1998");
        memberFormDTO.setMonth("06");
        memberFormDTO.setDay("12");
        memberFormDTO.setPh("01055556666");
        return Member.createMember(memberFormDTO, passwordEncoder);
    }

    @Test
    @DisplayName("회원가입 테스트")
    public void saveMemberTest() {
        Member member = createMember();
        Member savedMember = memberService.saveMember(member);

        //jUnitTest문법 (assertTrue, assertFalse, assertNull..)
        assertEquals(member.getId(), savedMember.getId());
        assertEquals(member.getName(), savedMember.getName());
        assertEquals(member.getNickName(), savedMember.getNickName());
        assertEquals(member.getEmail(), savedMember.getEmail());
        assertEquals(member.getPassword(), savedMember.getPassword());
        assertEquals(member.getAddress(), savedMember.getAddress());
        assertEquals(member.getBirth(), savedMember.getBirth());
        assertEquals(member.getPh(), savedMember.getPh());
    }

    @Test
    @DisplayName("중복 회원 가입 테스트")
    public void saveDuplicateMemberTest(){
        Member member1 = createMember();
        Member member2 = createMember();
        memberService.saveMember(member1);

        try{
            memberService.saveMember(member2);
        } catch(IllegalStateException e) {
            assertTrue(e instanceof IllegalStateException);
            assertEquals("이미 가입된 회원입니다.", e.getMessage());
        }
    }
}
