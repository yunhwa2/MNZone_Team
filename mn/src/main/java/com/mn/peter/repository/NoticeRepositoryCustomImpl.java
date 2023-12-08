package com.mn.peter.repository;

import com.mn.constant.NoticeKind;
import com.mn.entity.Notice;
import com.mn.entity.QNotice;
import com.mn.peter.dto.NoticeListFormDTO;
import com.mn.peter.dto.NoticeSearchDTO;
import com.mn.peter.dto.QNoticeListFormDTO;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.thymeleaf.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.List;

public class NoticeRepositoryCustomImpl implements NoticeRepositoryCustom{
    private JPAQueryFactory queryFactory;

    public NoticeRepositoryCustomImpl(EntityManager em){
        this.queryFactory = new JPAQueryFactory(em);
    }

    private BooleanExpression searchNoticeKind(String noticeKind){
        System.err.println("repository.ItemRepositoryCustomImpl.searchNoticeKind");
        NoticeKind noticeKind1;
        if(StringUtils.equals("ALL",noticeKind)||noticeKind==null){
             return null;
        }else if(StringUtils.equals("ANNOUNCEMENT",noticeKind)){
             noticeKind1 = NoticeKind.ANNOUNCEMENT;
        }else if(StringUtils.equals("SYSTEM",noticeKind)){
             noticeKind1 = NoticeKind.SYSTEM;
        }else if(StringUtils.equals("SERVICE",noticeKind)){
             noticeKind1 = NoticeKind.SERVICE;
        }else{
             noticeKind1 = NoticeKind.EVENT;
        }

        return QNotice.notice.noticeKind.eq(noticeKind1);
    }
    private BooleanExpression searchByLike(String searchBy, String searchQuery){

        System.err.println("repository.ItemRepositoryCustomImpl.searchByLike");
        if(StringUtils.equals("noticeTitle",searchBy)){
            return QNotice.notice.noticeTitle.like("%"+searchQuery+"%");
        }else if(StringUtils.equals("createdBy", searchBy)){
            return QNotice.notice.createBy.like("%"+searchQuery+"%");
        }

        return null;
    }

    @Override
    public Page<NoticeListFormDTO> getNoticePage(NoticeSearchDTO noticeSearchDTO, Pageable pageable) {

        QNotice notice = QNotice.notice;

        List<NoticeListFormDTO> content = queryFactory.select(
                new QNoticeListFormDTO(
                        notice.noticeTitle,
                        notice.noticeContent,
                        notice.createBy,
                        notice.regTime)
                )
                .from(notice)
                .where(searchNoticeKind(noticeSearchDTO.getNoticeSearchKind()),
                        searchByLike(noticeSearchDTO.getNoticeSearchBy(), noticeSearchDTO.getNoticeSearchQuery()))
                .orderBy(QNotice.notice.noticeId.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();


        long total = queryFactory.select(Wildcard.count)
                .from(notice)
                .where(searchNoticeKind(noticeSearchDTO.getNoticeSearchKind()),
                        searchByLike(noticeSearchDTO.getNoticeSearchBy(), noticeSearchDTO.getNoticeSearchQuery()))
                .fetchOne();

        return null;
    }
    @Override
    public Page<Notice> getNoticePage2(NoticeSearchDTO noticeSearchDTO, Pageable pageable) {
        System.err.println("NoticeRepositoryCustomImpl.getNoticePage()");
        List<Notice> content = queryFactory
                .selectFrom(QNotice.notice)
                .where(searchNoticeKind(noticeSearchDTO.getNoticeSearchKind()),
                        searchByLike(noticeSearchDTO.getNoticeSearchBy(), noticeSearchDTO.getNoticeSearchQuery()))
                .orderBy(QNotice.notice.noticeId.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = queryFactory.select(Wildcard.count)
                .from(QNotice.notice)
                .where(searchNoticeKind(noticeSearchDTO.getNoticeSearchKind()),
                        searchByLike(noticeSearchDTO.getNoticeSearchBy(), noticeSearchDTO.getNoticeSearchQuery()))
                .fetchOne();
        System.err.println("total : " +total);
        return new PageImpl<>(content,pageable,total);
    }
}
