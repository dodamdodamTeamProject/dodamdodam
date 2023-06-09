package com.app.dodamdodam.repository.board.free;

import com.app.dodamdodam.entity.free.FreeBoard;
import com.app.dodamdodam.search.FreeBoardSearch;
import com.app.dodamdodam.search.board.AdminFreeBoardSearch;
import com.app.dodamdodam.type.CategoryType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface FreeBoardQueryDsl {
    //    세션에 담긴 id 값 받아와서 내가 작성한 자유 게시글 리스트 가져오기
    public Page<FreeBoard> findFreeBoardListByMemberId_QueryDSL(Pageable pageable, Long memberId);

    //    자유 게시글 전체 개수 가져오기
    public Long findFreeBoardListCountByMemberId_QueryDSL(Long memberId);

    //    자유 게시글 전체 리스트 가져오기
    public Page<FreeBoard> findAllFreeBoardList_QueryDSL(Pageable pageable);

    //    자유 게시글 전체 리스트 Category에 따라 분류해서 가져오기
    public Page<FreeBoard> findFreeBoardListByCategoryType_QueryDSL(Pageable pageable, CategoryType categoryType);

    //    내가 작성한 자유 게시글 리스트 Category에 따라 분류해서 가져오기
    public Page<FreeBoard> findFreeBoardListByCategoryTypeAndMemberId_QueryDSL(Pageable pageable, CategoryType categoryType, Long memberId);

    //  어드민 자유게시판 검색
    public Page<FreeBoard> findAdmindFreeBoardWithPaging_QueryDSL(AdminFreeBoardSearch adminFreeBoardSearch, Pageable pageable);

    //    자유게시글 상세 페이지
    public Optional<FreeBoard> findFreeBoardAndFreeFilesById_QueryDSL(Long boardId);

    //    자유게시글 상세 페이지 댓글
    public Optional<FreeBoard> findFreeBoardAndFreeRepliesById_QueryDSL(Long boardId);

    //    자유게시판 검색
    public Page<FreeBoard> findFreeBoardBySearchWithPaging_QueryDSL(FreeBoardSearch freeBoardSearch, CategoryType categoryType, Pageable pageable);
//    public Page<FreeBoard> findFreeBoardWithPaging_QueryDSL(FreeBoardSearch freeBoardSearch, Pageable pageable);

//    자유게시판 좋아요 Top5
    public List<FreeBoard> findFreeBoardListByLikeCount_QueryDSL();

//    자유 게시판 최근 게시물 5개
    public List<FreeBoard> findRecentFreeBoardList_QueryDSL();

//    댓글 id로 자유게시글 접근해서 그 안에 달린 댓글 개수 가져오기
    public Integer findReplyCountByReplyId_QueryDSL(Long replyId);
}
