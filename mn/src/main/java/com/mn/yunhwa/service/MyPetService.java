package com.mn.yunhwa.service;

import com.mn.entity.MyPet;
import com.mn.seoha.repository.MemberRepository;
import com.mn.yunhwa.dto.MyPetFormDTO;
import com.mn.yunhwa.repository.MyPetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MyPetService {
    private  final MyPetRepository myPetRepository;
    private final MemberRepository memberRepository;
    public MyPet savedMyPet(MyPetFormDTO myPetFormDTO, String id){
        MyPet myPet = new MyPet();

        myPet.setMyPetCategory(myPetFormDTO.getMyPetCategory());
        myPet.setMyPetName(myPetFormDTO.getMyPetName());
        myPet.setMyPetBirth(myPetFormDTO.getMyPetBirth());
        myPet.setMyPetWeight(myPetFormDTO.getMyPetWeight());
        myPet.setMyPetGender(myPetFormDTO.getMyPetGender());
        myPet.setMyPetNeuter(myPetFormDTO.getMyPetNeuter());
        myPet.setMyPetKind(myPetFormDTO.getMyPetKind());
        myPet.setMember(memberRepository.findById(id));
        return myPetRepository.save(myPet);
    }

    private void validateDuplicateMyPet(MyPet myPet){
        List<MyPet> findMyPet = myPetRepository.findByMyPetByNative(myPet.getMember());
        if(findMyPet.size() >= 7){
            throw new IllegalStateException("최대 6마리만 등록할 수 있습니다");
        }
    }


}
