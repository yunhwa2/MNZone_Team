package com.mn.yunhwa.repository;

import com.mn.entity.MissingComment;
import com.mn.entity.MyPetDiary;
import com.mn.entity.QMissingComment;
import com.mn.entity.QMyPetDiary;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;

public class MyPetDiaryRepositoryCustomImpl implements MyPetDiaryRepositoryCustom{

    private JPAQueryFactory queryFactory;

    public MyPetDiaryRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<MyPetDiary> getMyPetMyPetId(Long myPetId) {
        List<MyPetDiary> results = queryFactory
                .selectFrom(QMyPetDiary.myPetDiary)
                .where(QMyPetDiary.myPetDiary.myPet.myPetId.eq(myPetId))
                .fetch();
        return results;
    }
}
