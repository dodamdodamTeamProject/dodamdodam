package com.app.dodamdodam.repository.point;

import com.app.dodamdodam.entity.member.Member;
import com.app.dodamdodam.entity.member.QGrade;
import com.app.dodamdodam.entity.member.QMember;
import com.app.dodamdodam.entity.point.Point;
import com.app.dodamdodam.search.point.AdminPointSearch;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.app.dodamdodam.entity.free.QFreeBoard.freeBoard;
import static com.app.dodamdodam.entity.member.QMember.member;
import static com.app.dodamdodam.entity.point.QPoint.point;

@Slf4j
@RequiredArgsConstructor
public class PointQueryDslImpl implements PointQueryDsl {
    @Autowired
    private JPAQueryFactory query;

    @Override
    public List<Point> findPointByMemberId_QueryDSL(Long memberId) {
        return query.select(point).from(point).where(point.member.id.eq(memberId)).orderBy(point.id.desc()).fetch();
    }

    @Override //관리자 목록 조회
    public Page<Point> findAllWithPaging(Pageable pageable) {
        List<Point> pointList = query.select(point)
                .from(point)
                .orderBy(point.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = query.select(point.count())
                .from(point)
                .fetchOne();
        return new PageImpl<>(pointList,pageable, count);
    }

    @Override //관리자 - 멤버 id와 이름 조회
    public Page<Point> findPointMemberIdWithSearch_QueryDSL(AdminPointSearch adminPointSearchpointSearch, Pageable pageable) {
        BooleanExpression searchAll = null;
        if (adminPointSearchpointSearch.getMemberId() != null || adminPointSearchpointSearch.getMemberName() != null || adminPointSearchpointSearch.getPointAmount() != null) {
            BooleanExpression searchId = adminPointSearchpointSearch.getMemberId() != null ? point.member.memberId.contains(adminPointSearchpointSearch.getMemberId()) : null;
            BooleanExpression searchName = adminPointSearchpointSearch.getMemberName() != null ? point.member.memberName.contains(adminPointSearchpointSearch.getMemberName()) : null;
            BooleanExpression searchAmount = adminPointSearchpointSearch.getPointAmount() != null ? point.pointAmount.eq(adminPointSearchpointSearch.getPointAmount()) : null;


            searchAll = searchId.or(searchName).or(searchAmount);
        }

        
        
        List<Point> points = query.select(point)
                .from(point)
                .join(point.member)
                .fetchJoin()
                .where(searchAll)
                .orderBy(point.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        Long count = query.select(point.count()).from(point).where(searchAll).fetchOne();

        return new PageImpl<>(points, pageable, count);
    }


}
