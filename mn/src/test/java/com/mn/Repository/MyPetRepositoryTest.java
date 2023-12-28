package com.mn.Repository;

import com.mn.constant.MyPetCategory;
import com.mn.constant.MyPetGender;
import com.mn.constant.MyPetNeuter;
import com.mn.entity.Member;
import com.mn.entity.MyPet;
import com.mn.seoha.repository.MemberRepository;
import com.mn.yunhwa.repository.MyPetRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;


@SpringBootTest
@Transactional
@TestPropertySource(locations="classpath:application-test.properties")
public class MyPetRepositoryTest {

    @Autowired
    MyPetRepository myPetRepository;

    @Autowired
    MemberRepository memberRepository;

    static Member savedMember2;

    public void saveMember(){
        Member member = new Member();
        member.setId("aaaa1");
        member.setEmail("a1");
        member.setNickName("호");
        Member savedMember = memberRepository.save(member);
        System.out.println(savedMember.toString());
        savedMember2 = savedMember;

    }

    public void createMyPetList(){
        saveMember();
        for(int i=1; i<=6; i++){
            MyPet myPet = new MyPet();
            myPet.setMyPetCategory(MyPetCategory.CAT);
            myPet.setMyPetName("이호두"+i);
            myPet.setMyPetBirth(LocalDate.of(2023,3,31));
            myPet.setMyPetWeight("4.6");
            myPet.setMyPetGender(MyPetGender.FEMALE);
            myPet.setMyPetNeuter(MyPetNeuter.NEUTER_YES);
            myPet.setMyPetKind("코리안 쇼트헤어");
            myPet.setMember(savedMember2);
            MyPet savedMyPet = myPetRepository.save(myPet);
        }
    }

    @Test
    @DisplayName("반려동물 저장 테스트")
    public void createMyPetTest(){
        saveMember();
        MyPet myPet = new MyPet();
        myPet.setMyPetCategory(MyPetCategory.CAT);
        myPet.setMyPetName("이호두");
        myPet.setMyPetBirth(LocalDate.of(2023,3,31));
        myPet.setMyPetWeight("4.6");
        myPet.setMyPetGender(MyPetGender.FEMALE);
        myPet.setMyPetNeuter(MyPetNeuter.NEUTER_YES);
        myPet.setMyPetKind("코리안 쇼트헤어");
        myPet.setMember(savedMember2);
        System.out.println(myPet.toString());
        MyPet savedMyPet = myPetRepository.save(myPet);


        System.out.println(savedMyPet.toString());
    }


    @Test
    @DisplayName("내 반려동물 조회 테스트")
    public void findByMyPetTest(){
        this.createMyPetList();
        List<MyPet> myPetList = myPetRepository.findByMember(savedMember2);
        for(MyPet myPet : myPetList){
            System.out.println(myPet.toString());
        }
    }

    @Test
    @DisplayName("nativeQuery 속성을 이용한 내 반려동물 조회 테스트")
    public void findByMyPetByNative(){
        this.createMyPetList();
        List<MyPet> myPetList = myPetRepository.findByMyPetByNative(savedMember2);
        for(MyPet myPet : myPetList){
            System.out.println(myPet.toString());
        }

    }



}
