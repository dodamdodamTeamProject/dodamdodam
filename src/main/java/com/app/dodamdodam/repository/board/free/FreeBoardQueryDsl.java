package com.app.dodamdodam.repository.board.free;

import com.app.dodamdodam.entity.free.FreeBoard;
import com.app.dodamdodam.entity.member.Member;
import com.app.dodamdodam.entity.point.Point;
import com.app.dodamdodam.entity.recruitment.RecruitmentBoard;
import com.app.dodamdodam.type.CategoryType;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FreeBoardQueryDsl {
//    세션에 담긴 id 값 받아와서 내가 작성한 자유 게시글 리스트 가져오기
    public List<FreeBoard> findFreeBoardListByMemberId(Pageable pageable, Long memberId);

//    자유 게시글 전체 리스트 가져오기
    public List<FreeBoard> findAllFreeBoardList(Pageable pageable);

//    자유 게시글 전체 리스트 Category에 따라 분류해서 가져오기
    public List<FreeBoard> findFreeBoardListByCategoryType(Pageable pageable, CategoryType categoryType);

//    내가 작성한 자유 게시글 리스트 Category에 따라 분류해서 가져오기
    public List<FreeBoard> findFreeBoardListByCategoryTypeAndMemberId(Pageable pageable, CategoryType categoryType, Long memberId);

}
