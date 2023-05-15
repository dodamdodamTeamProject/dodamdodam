package com.app.dodamdodam.service.board.freeBoard;

import com.app.dodamdodam.domain.*;
import com.app.dodamdodam.entity.free.FreeBoard;
import com.app.dodamdodam.entity.free.FreeFile;
import com.app.dodamdodam.entity.free.FreeReply;
import com.app.dodamdodam.entity.member.Member;
import com.app.dodamdodam.entity.purchase.Product;
import com.app.dodamdodam.entity.purchase.PurchaseFile;
import com.app.dodamdodam.type.CategoryType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public interface FreeBoardService {
    /* 자유 게시글 전체 목록 */
    public List<FreeBoardFileDTO> getAllFreeBoards(Pageable pageable);
//    public Page<FreeBoard> getAllFreeBoards(Pageable pageable);

    /* 자유 게시글 카테고리 별 분류해서 가져오기 */
    public Page<FreeBoard> getFreeBoardsByCategoryType(CategoryType categoryType, Pageable pageable);

    /* 내가 작성한 자유게시글 목록 가져오기(memberId로 자유게시글 조회) */
    public List<FreeBoardFileDTO> getFreeBoardsByMemberId(Pageable pageable, Long memberId);

    /* 자유 게시글 상세 */
    public Optional<FreeBoard> getFreeBoardById(Long boardId);

    /* 자유 게시글 수정 */
    public void updateFreeBoard(FreeBoard freeBoard);

    /* 자유 게시글 삭제 */
    public void deleteFreeBoard(FreeBoard freeBoard);


    /* toDTO */
    default FreeBoardFileDTO toFreeBoardFileDTO(FreeBoard freeBoard){
        return FreeBoardFileDTO.builder().id(freeBoard.getId())
                .boardTitle(freeBoard.getBoardTitle())
                .boardContent(freeBoard.getBoardContent())
                .memberDTO(toMemberDTO(freeBoard.getMember()))
                .createdDate(freeBoard.getCreatedDate())
                .updatedDate(freeBoard.getUpdatedDate())
                .freeCategory(freeBoard.getFreeCategory())
                .likeCount(freeBoard.getLikeCount())
                .freeFileDTOS(
                        freeBoard.getFreeFiles().stream().map(e -> toFreeFileDTO(e)).collect(Collectors.toList())
                )
                .build();
    }

    default FreeBoardReplyDTO toFreeBoardReplyDTO(FreeBoard freeBoard){
        return FreeBoardReplyDTO.builder().id(freeBoard.getId())
                .boardTitle(freeBoard.getBoardTitle())
                .boardContent(freeBoard.getBoardContent())
                .freeReplyDTOS(
                        freeBoard.getFreeReplies().stream().map(freeReply -> toFreeReplyDTO(freeReply)).collect(Collectors.toList())
                )
                .build();
    }

    default FreeReplyDTO toFreeReplyDTO(FreeReply freeReply){
        return FreeReplyDTO.builder().id(freeReply.getId())
                .replyContent(freeReply.getReplyContent())
                .memberDTO(toMemberDTO(freeReply.getMember()))
                .build();
    }

    default MemberDTO toMemberDTO(Member member){
        return MemberDTO.builder().id(member.getId())
                .memberEmail(member.getMemberEmail())
                .memberName(member.getMemberName())
                .memberId(member.getMemberId())
                .memberPassword(member.getMemberPassword())
                .memberPhone(member.getMemberPhone())
                .memberStatus(member.getMemberStatus())
                .memberPoint(member.getMemberPoint())
                .participationCount(member.getParticipationCount())
                .recruitmentedCount(member.getParticipationCount())
                .address(member.getAddress())
                .build();
    }

    default FreeFileDTO toFreeFileDTO(FreeFile freeFile){
        return FreeFileDTO.builder().id(freeFile.getId())
                .fileOriginalName(freeFile.getFileOriginalName())
                .filePath(freeFile.getFilePath())
                .fileUuid(freeFile.getFileUuid())
                .fileSize(freeFile.getFileSize())
                .build();
    }

}