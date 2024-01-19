package com.mn.yunhwa.repository;

import com.mn.entity.QMyPet;
import com.mn.yunhwa.dto.MyPetMainDTO;
import com.mn.yunhwa.dto.MyPetSearchDTO;
import com.mn.yunhwa.dto.QMyPetMainDTO;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class MyPetRepositoryCustomImpl implements MyPetRepositoryCustom{

    private JPAQueryFactory queryFactory;

    public MyPetRepositoryCustomImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    private BooleanExpression searchMemberCodeEq(Long memberCode){
        return memberCode == null ? null : QMyPet.myPet.member.code.eq(memberCode);
    }

    @Override
    public List<MyPetMainDTO> getAllMyPets(MyPetSearchDTO myPetSearchDTO, Long memberCode) {
        QMyPet myPet = QMyPet.myPet;

        //MainItemDTO 생성자에 변환할 값을 입력
        List<MyPetMainDTO> content =queryFactory.select(
                        new QMyPetMainDTO(myPet.myPetId,
                                myPet.myPetName,
                                myPet.myPetImgUrl,
                                myPet.member
                        )
                ).from(myPet)
                .where(searchMemberCodeEq(memberCode))
                .fetch();
        return content;
    }
}
