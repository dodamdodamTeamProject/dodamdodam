package com.app.dodamdodam.service.board.recruitmentBoard;

import com.app.dodamdodam.domain.*;
import com.app.dodamdodam.entity.member.Member;
import com.app.dodamdodam.entity.purchase.Product;
import com.app.dodamdodam.entity.purchase.PurchaseBoard;
import com.app.dodamdodam.entity.purchase.PurchaseFile;
import com.app.dodamdodam.entity.recruitment.Recruitment;
import com.app.dodamdodam.entity.recruitment.RecruitmentBoard;
import com.app.dodamdodam.entity.recruitment.RecruitmentFile;
import com.app.dodamdodam.search.PurchaseBoardSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;
import java.util.stream.Collectors;

public interface RecruitmentBoardService {

//    내가 작성한 모집 게시글 목록
    public List<RecruitmentBoardFileDTO> getRecruimentBoardListByMemberId(Pageable pageable, Long memberId);

//    내가 참가한 모집 게시글 목록
    public List<RecruitmentBoardFileDTO> getRecruimentedBoardListByMemberId(Pageable pageable, Long memberId);

////    관리자 목록 게시판
//    public Page<RecruitmentBoardFileDTO> showList(Pageable pageable);

    default RecruitmentBoardFileDTO toRecruitmentBoardFileDto(RecruitmentBoard recruitmentBoard){
        return RecruitmentBoardFileDTO.builder()
                .id(recruitmentBoard.getId())
                .boardContent(recruitmentBoard.getBoardContent())
                .boardTitle(recruitmentBoard.getBoardTitle())
                .memberDTO(toMemberDTO(recruitmentBoard.getMember()))
                .recruitmentAddress(recruitmentBoard.getRecruitmentAddress())
                .recruitmentAddressDetail(recruitmentBoard.getRecruitmentAddressDetail())
                .recruitmentDate(recruitmentBoard.getRecruitmentDate())
                .recruitmentDTOS(recruitmentBoard.getRecruitments().stream().map(recruitment -> toRecruitmentDto(recruitment)).collect(Collectors.toList()))
                .recruitmentFileDTOS(recruitmentBoard.getRecruitmentFiles().stream().map(recruitmentFile -> toRecruitmentFileDto(recruitmentFile)).collect(Collectors.toList()))
                .recruitmentOpenChatting(recruitmentBoard.getRecruitmentOpenChatting())
                .recruitmentPassword(recruitmentBoard.getRecruitmentPassword())
                .recruitmentPeopleCount(recruitmentBoard.getRecruitmentPeopleCount())
                .recruitmentStatus(recruitmentBoard.getRecruitmentStatus())
                .recruitmentSubtitle(recruitmentBoard.getRecruitmentSubtitle())
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
                .address(member.getAddress())
                .build();
    }

    default RecruitmentDTO toRecruitmentDto(Recruitment recruitment){
        return RecruitmentDTO.builder().id(recruitment.getId())
                .member(toMemberDTO(recruitment.getMember()))
                .build();
    }

    default RecruitmentFileDTO toRecruitmentFileDto(RecruitmentFile recruitmentFile){
        return RecruitmentFileDTO.builder()
                .fileOriginalName(recruitmentFile.getFileOriginalName())
                .filePath(recruitmentFile.getFilePath())
                .fileSize(recruitmentFile.getFileSize())
                .fileUuid(recruitmentFile.getFileUuid())
                .id(recruitmentFile.getId())
                .build();
    }
}