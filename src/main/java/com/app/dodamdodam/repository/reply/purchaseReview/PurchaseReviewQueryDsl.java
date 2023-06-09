package com.app.dodamdodam.repository.reply.purchaseReview;

import com.app.dodamdodam.entity.purchase.PurchaseReview;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface PurchaseReviewQueryDsl {
    //    댓글 무한스크롤
    public Slice<PurchaseReview> findAllPurchaseReviewByBoardId(Long boardId, Pageable pageable);

//    상품, 게시글별 댓글 수
    public Long findReviewCountByBoardId_QueryDSL(Long boardId);
}
