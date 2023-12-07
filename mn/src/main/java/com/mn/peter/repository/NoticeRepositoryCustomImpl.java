package com.mn.peter.repository;

import com.mn.entity.Notice;
import com.mn.peter.dto.NoticeFormDTO;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;

public class NoticeRepositoryCustomImpl implements NoticeRepositoryCustom{
    private JPAQueryFactory queryFactory;

    public NoticeRepositoryCustomImpl(EntityManager em){
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<Notice> getNoticePage(NoticeFormDTO noticeFormDTO, Pageable pageable) {


        return null;
    }
}
