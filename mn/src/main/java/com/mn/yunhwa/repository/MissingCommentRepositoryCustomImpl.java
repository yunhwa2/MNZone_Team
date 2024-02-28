package com.mn.yunhwa.repository;

import com.mn.entity.Missing;
import com.mn.entity.MissingComment;
import com.mn.entity.QMissing;
import com.mn.entity.QMissingComment;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.PageImpl;

import javax.persistence.EntityManager;
import java.util.List;

public class MissingCommentRepositoryCustomImpl implements MissingCommentRepositoryCustom{

    private JPAQueryFactory queryFactory;

    public MissingCommentRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<MissingComment> getMissingMissingId(Long missingId) {
        List<MissingComment> results = queryFactory
                .selectFrom(QMissingComment.missingComment1)
                .where(QMissingComment.missingComment1.missing.missingId.eq(missingId))
                .orderBy(QMissingComment.missingComment1.missingCommentId.asc())
                .fetch();


        //조회된 항목의 리스트, 페이지정보, 전체항목수를 page객체로 반환
        return results;
    }
}
