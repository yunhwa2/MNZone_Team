package com.mn.yunhwa.repository;

import com.mn.constant.MissingKind;
import com.mn.entity.Missing;
import com.mn.entity.QMissing;
import com.mn.entity.QMissingImg;
import com.mn.yunhwa.dto.MissingMainDTO;
import com.mn.yunhwa.dto.MissingSearchDTO;
import com.mn.yunhwa.dto.QMissingMainDTO;
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

    //검색어가 포함되어 있는 제목 / 내용 / 제목이나 내용
    private BooleanExpression searchMissingByLike(String searchMissingBy, String searchMissingQuery) {
        if (StringUtils.equals("missingTitle", searchMissingBy) && !StringUtils.isEmptyOrWhitespace(searchMissingQuery)) {
            return QMissing.missing.missingTitle.like("%" + searchMissingQuery + "%");
        } else if (StringUtils.equals("missingContent", searchMissingBy) && !StringUtils.isEmptyOrWhitespace(searchMissingQuery)) {
            return QMissing.missing.missingContent.like("%" + searchMissingQuery + "%");
        } else if (StringUtils.equals("missingTitleAndContent", searchMissingBy) && !StringUtils.isEmptyOrWhitespace(searchMissingQuery)) {
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

        long total = queryFactory
                .select(Wildcard.count)
                .from(QMissing.missing)
                .where(regDtsAfter(missingSearchDTO.getSearchDateType()),
                        searchMissingKindEq(missingSearchDTO.getSearchMissingKind()),
                        searchMissingByLike(missingSearchDTO.getSearchMissingBy(), missingSearchDTO.getSearchMissingQuery()))
                .fetchOne();

        //조회된 항목의 리스트, 페이지정보, 전체항목수를 page객체로 반환
        return new PageImpl<>(results, pageable, total);

    }

    @Override
    public Page<MissingMainDTO> getMissingMainPage(MissingSearchDTO missingSearchDTO, Pageable pageable) {
        System.out.println("missingRepositoryImpl 실행");

        QMissing missing = QMissing.missing;
        QMissingImg missingImg = QMissingImg.missingImg;

        //MainItemDTO 생성자에 변환할 값을 입력
        List<MissingMainDTO> content =queryFactory.select(
                        new QMissingMainDTO(missing.missingId,
                                            missing.missingKind,
                                            missing.missingTitle,
                                            missing.missingContent,
                                            missingImg.missingImgUrl)
                ).from(missingImg)
                .join(missingImg.missing,missing)    //itemImg와 item을 조인
                .where(missingImg.missingRepImgYn.eq("Y"))    //대표 이미지만 불러옴
                .orderBy(missing.missingId.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        //전체 아이템의 개수를 조회
        long total = queryFactory.select(Wildcard.count)
                .from(missingImg)
                .join(missingImg.missing,missing)
                .where(missingImg.missingRepImgYn.eq("Y"))
                .where(missingTitleLike(missingSearchDTO.getSearchMissingQuery()))
                .fetchOne();

        return new PageImpl<>(content, pageable,total);
    }

    private  BooleanExpression missingTitleLike(String searchMissingQuery){
        return StringUtils.isEmpty(searchMissingQuery) ? null : QMissing.missing.missingTitle.like("%" + searchMissingQuery+"%");
    }
}
