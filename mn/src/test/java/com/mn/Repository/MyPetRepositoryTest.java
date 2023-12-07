package com.mn.Repository;

import com.mn.constant.MyPetCategory;
import com.mn.constant.MyPetGender;
import com.mn.constant.MyPetNeuter;
import com.mn.entity.Member;
import com.mn.entity.MyPet;
import com.mn.seoha.repository.MemberRepository;
import com.mn.yunhwa.repository.MyPetRetository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;
import java.util.List;


@SpringBootTest
@TestPropertySource(locations="classpath:application-test.properties")
public class MyPetRepositoryTest {

    @Autowired
    MyPetRetository myPetRetository;

    @Autowired
    MemberRepository memberRepository;


    public Member saveMember(){
        Member member = new Member();
        member.setId("aaaa1");
        member.setEmail("a1");
        member.setNickName("호");
        Member savedMember = memberRepository.save(member);
        System.out.println(savedMember.toString());
        return savedMember;
    }

    public void createMyPetList(){
        for(int i=1; i<=6; i++){
            MyPet myPet = new MyPet();
            myPet.setMyPetCategory(MyPetCategory.CAT);
            myPet.setName("이호두"+i);
            myPet.setBirth(LocalDate.of(2023,3,31));
            myPet.setWeight("4.6");
            myPet.setMyPetGender(MyPetGender.FEMALE);
            myPet.setMyPetNeuter(MyPetNeuter.NEUTER_YES);
            myPet.setKind("코리안 쇼트헤어");
            myPet.setMember(saveMember());
            MyPet savedMyPet = myPetRetository.save(myPet);
        }
    }

    @Test
    @DisplayName("반려동물 저장 테스트")
    public void createMyPetTest(){
        MyPet myPet = new MyPet();
        myPet.setMyPetCategory(MyPetCategory.CAT);
        myPet.setName("이호두");
        myPet.setBirth(LocalDate.of(2023,3,31));
        myPet.setWeight("4.6");
        myPet.setMyPetGender(MyPetGender.FEMALE);
        myPet.setMyPetNeuter(MyPetNeuter.NEUTER_YES);
        myPet.setKind("코리안 쇼트헤어");
        myPet.setMember(saveMember());
        MyPet savedMyPet = myPetRetository.save(myPet);

        System.out.println(savedMyPet.toString());
    }

    //오류남 내일 수정하기
    @Test
    @DisplayName("내 반려동물 조회 테스트")
    public void findByMyPetNameTest(){
        this.createMyPetList();
        List<MyPet> myPetList = myPetRetository.findByPetName("이호두1");
        for(MyPet myPet : myPetList){
            System.out.println(myPet.toString());
        }
    }



}
