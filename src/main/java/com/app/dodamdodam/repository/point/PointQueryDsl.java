package com.app.dodamdodam.repository.point;

import com.app.dodamdodam.entity.inquiry.Inquiry;
import com.app.dodamdodam.entity.point.Point;
import com.app.dodamdodam.search.point.AdminPointSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PointQueryDsl {
//    memberId로 point 내역 조회
    public List<Point> findPointByMemberId_QueryDSL(Long memberId);

    //문의사항 관리자 페이징
    public Page<Point> findAllWithPaging(Pageable pageable);

//  어드민 포인트 정보 검색
    public Page<Point> findPointMemberIdWithSearch_QueryDSL(AdminPointSearch pointSearch, Pageable pageable);
}
