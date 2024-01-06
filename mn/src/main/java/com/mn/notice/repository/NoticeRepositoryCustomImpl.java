package com.mn.notice.repository;

import com.mn.constant.NoticeKind;
import com.mn.entity.Notice;
import com.mn.entity.QNotice;
import com.mn.notice.dto.*;
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
    private BooleanExpression searchByLike(String searchQuery){

        System.err.println("repository.ItemRepositoryCustomImpl.searchByLike");

        return QNotice.notice.noticeTitle.like("%"+searchQuery+"%");
    }

    @Override
    public Page<NoticeListFormDTO> getNoticePage(NoticeSearchDTO noticeSearchDTO, Pageable pageable) {

        QNotice notice = QNotice.notice;

        List<NoticeListFormDTO> content = queryFactory.select(
                new QNoticeListFormDTO(
                        notice.noticeId,
                        notice.noticeTitle,
                        notice.noticeContent,
                        notice.createBy,
                        notice.regTime,
                        notice.noticeKind)
                )
                .from(notice)
                .where(searchNoticeKind(noticeSearchDTO.getNoticeSearchKind()),
                        searchByLike(noticeSearchDTO.getNoticeSearchQuery()))

                .orderBy(QNotice.notice.noticeId.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();


        long total = queryFactory.select(Wildcard.count)
                .from(notice)
                .where(searchNoticeKind(noticeSearchDTO.getNoticeSearchKind()),
                        searchByLike(noticeSearchDTO.getNoticeSearchQuery()))
                .fetchOne();


        return new PageImpl<>(content,pageable,total);
    }
    @Override
    public Page<Notice> getNoticePage2(NoticeSearchDTO noticeSearchDTO, Pageable pageable) {
        System.err.println("NoticeRepositoryCustomImpl.getNoticePage()");
        List<Notice> content = queryFactory
                .selectFrom(QNotice.notice)
                .where(searchNoticeKind(noticeSearchDTO.getNoticeSearchKind()),
                        searchByLike(noticeSearchDTO.getNoticeSearchQuery()))
                .orderBy(QNotice.notice.noticeId.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = queryFactory.select(Wildcard.count)
                .from(QNotice.notice)
                .where(searchNoticeKind(noticeSearchDTO.getNoticeSearchKind()),
                        searchByLike(noticeSearchDTO.getNoticeSearchQuery()))
                .fetchOne();

        return new PageImpl<>(content,pageable,total);
    }

    @Override
    public List<NoticeDetailPrevNextDTO> getNoticeDetailPrevNextDTOList(NoticeSearchDTO noticeSearchDTO) {
        QNotice notice = QNotice.notice;
        List<NoticeDetailPrevNextDTO> content =  queryFactory.select(
                new QNoticeDetailPrevNextDTO(notice.noticeId, notice.noticeTitle, notice.regTime)
        )
                .from(notice)
                .where(searchNoticeKind(noticeSearchDTO.getNoticeSearchKind()),
                        searchByLike(noticeSearchDTO.getNoticeSearchQuery()))
                .orderBy(notice.noticeId.desc())
                .fetch();



        return content;
    }
}
