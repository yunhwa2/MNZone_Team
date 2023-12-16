package com.mn.yunhwa.repository;

import com.mn.constant.MissingKind;
import com.mn.entity.Missing;
import com.mn.entity.QMissing;
import com.mn.yunhwa.dto.MissingSearchDTO;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.thymeleaf.util.StringUtils;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

public class MissingRepositoryCustomImpl implements MissingRepositoryCustom{

    private JPAQueryFactory queryFactory;

    public MissingRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    private BooleanExpression searchMissingKindEq(MissingKind searchMissingKind){
        return searchMissingKind == null ? null : QMissing.missing.missingKind.eq(searchMissingKind);
    }

    //현재 날짜와 시간을 설정하여 해당 시간 이후로 등록된 상품만 조회하도록 조건을 생성
    private BooleanExpression regDtsAfter(String searchDateType) {
        LocalDateTime dateTime = LocalDateTime.now();

        if (StringUtils.equals("all", searchDateType) || searchDateType == null) {
            return null;
        } else if (StringUtils.equals("1d", searchDateType)) {
            dateTime = dateTime.minusDays(1);
        } else if (StringUtils.equals("1w", searchDateType)) {
            dateTime = dateTime.minusWeeks(1);
        } else if (StringUtils.equals("1m", searchDateType)) {
            dateTime = dateTime.minusMonths(1);
        } else if (StringUtils.equals("6m", searchDateType)) {
            dateTime = dateTime.minusMonths(6);
        }

        return QMissing.missing.regTime.after(dateTime);
    }

    //검색어가 포함되어 있는 상품 또는 아이디를 조회
    private BooleanExpression searchMissingByLike(String searchMissingBy, String searchMissingQuery) {

        if(StringUtils.equals("missingTitle", searchMissingBy)) {
            return QMissing.missing.missingTitle.like("%" + searchMissingQuery + "%");
        } else if(StringUtils.equals("missingContent", searchMissingBy)) {
            return QMissing.missing.missingContent.like("%" + searchMissingQuery + "%");
        } else if(StringUtils.equals("missingTitleAndContent", searchMissingBy)) {
           return QMissing.missing.missingTitle.like("%" + searchMissingQuery + "%")
                   .or(QMissing.missing.missingContent.like("%" + searchMissingQuery + "%"));
        }
        return null;
    }

    @Override
    public Page<Missing> getMissingPage(MissingSearchDTO missingSearchDTO, Pageable pageable) {

        List<Missing> results = queryFactory
                .selectFrom(QMissing.missing)
                .where(regDtsAfter(missingSearchDTO.getSearchDateType()),
                        searchMissingKindEq(missingSearchDTO.getSearchMissingKind()),
                        searchMissingByLike(missingSearchDTO.getSearchMissingBy(),missingSearchDTO.getSearchMissingQuery()))
                .orderBy(QMissing.missing.regTime.desc())
                .offset(pageable.getOffset())   //데이터를 가져오도록 시작 인덱스를 설정
                .limit(pageable.getPageSize())  //한번에 가져올 페이지의 개수
                .fetch();

        long total = queryFactory.select(Wildcard.count)
                .from(QMissing.missing)
                .where(regDtsAfter(missingSearchDTO.getSearchDateType()),
                        searchMissingKindEq(missingSearchDTO.getSearchMissingKind()),
                        searchMissingByLike(missingSearchDTO.getSearchMissingBy(), missingSearchDTO.getSearchMissingQuery()))
                .fetchOne();

        //조회된 항목의 리스트, 페이지정보, 전체항목수를 page객체로 반환
        return new PageImpl<>(results, pageable, total);

    }
}
