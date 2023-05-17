package com.app.dodamdodam.service.board.recruitmentBoard;

import com.app.dodamdodam.domain.RecruitmentBoardFileDTO;
import com.app.dodamdodam.domain.RecruitmentFileDTO;
import com.app.dodamdodam.entity.recruitment.RecruitmentBoard;
import com.app.dodamdodam.repository.board.recruitment.RecruitmentBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecruitmentBoardServiceImpl implements RecruitmentBoardService {
    @Autowired
    private RecruitmentBoardRepository recruitmentBoardRepository;

    @Override
    public List<RecruitmentBoardFileDTO> getRecruimentBoardListByMemberId(Pageable pageable, Long memberId) {
        return recruitmentBoardRepository.findRecruitmentBoardListByMemberId_QueryDSL(pageable,memberId)
                .stream().map(recruitmentBoard -> toRecruitmentBoardFileDto(recruitmentBoard)).collect(Collectors.toList());
    }

    @Override
    public List<RecruitmentBoardFileDTO> getRecruimentedBoardListByMemberId(Pageable pageable, Long memberId) {
        return recruitmentBoardRepository.findRecruitmentedBoardListByMemberId_QueryDSL(pageable,memberId)
                .stream().map(recruitmentBoard -> toRecruitmentBoardFileDto(recruitmentBoard)).collect(Collectors.toList());
    }

//    @Override
//    public Page<RecruitmentBoardFileDTO> showList(Pageable pageable) {
//            Page<RecruitmentBoard> recruitmentBoardPage = recruitmentBoardRepository.findAllWithPaging(PageRequest.of(1, 10));
//          //  List<RecruitmentFileDTO> recruitmentFileDTOS = recruitmentBoardPage.get().map(this::toRecruitmentFileDto).collect(Collectors.toList());
//            return new PageImpl<>(recruitmentFileDTOS, pageable, recruitmentBoardPage.getTotalElements());
//        }
}