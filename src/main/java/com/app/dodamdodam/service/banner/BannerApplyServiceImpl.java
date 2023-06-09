package com.app.dodamdodam.service.banner;

import com.app.dodamdodam.domain.BannerDTO;
import com.app.dodamdodam.domain.FreeBoardFileDTO;
import com.app.dodamdodam.entity.banner.BannerApply;
import com.app.dodamdodam.entity.free.FreeBoard;
import com.app.dodamdodam.entity.recruitment.RecruitmentBoard;
import com.app.dodamdodam.repository.banner.BannerRepository;
import com.app.dodamdodam.search.banner.AdminBannerSearch;
import com.app.dodamdodam.type.BannerType;
import com.app.dodamdodam.type.MemberStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class BannerApplyServiceImpl implements BannerApplyService {
    private final BannerRepository bannerRepository;

    @Override //관리자 배너 목록 불러오기
    public Page<BannerDTO> showList(Pageable pageable) {
        Page<BannerApply> bannerApplyPage = bannerRepository.findAllWithPaging(pageable);
        List<BannerDTO> bannerDTOS = bannerApplyPage.get().map(this::toBannerDTO).collect(Collectors.toList());
        return new PageImpl<>(bannerDTOS, pageable, bannerApplyPage.getTotalElements());
    }

    @Override //관리자 배너 상세보기
    public BannerDTO getAdminBannerDetail(Long id) {
        Optional<BannerApply> bannerApply = bannerRepository.findById(id);
        if (bannerApply.isPresent()) {
            return toBannerDTO(bannerApply.get());
        } else {
            // 값이 없을 경우에 대한 처리 로직
            // 예를 들어, null을 반환하거나 예외를 throw할 수 있습니다.
            return null; // 혹은 예외 처리 코드 작성
        }
    }

    /*관리자 배너 검색하기 */
    @Override
    public Page<BannerDTO> showAdminBannerWithSearch_QueryDSL(Pageable pageable, AdminBannerSearch adminBannerSearch) {
        Page<BannerApply> bannerApplyPage = bannerRepository.findAdminBannerApplyWithPaging_QueryDSL(adminBannerSearch, pageable);
        List<BannerDTO> bannerDTOS = bannerApplyPage.getContent().stream()
                .map(this::toBannerDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(bannerDTOS, pageable, bannerApplyPage.getTotalElements());
    }

    @Override //관리자 삭제
    public void deleteAdminBannerList(List<Long> bannerApplyIds) {
        for (Long bannerApplyId : bannerApplyIds) {
            bannerRepository.deleteById(bannerApplyId);
        }
    }

    /*관리자 상태 변경*/
    public void setBannerStatus(List<Long> ids, BannerType bannerStatus) {
        for (Long id : ids) {
            bannerRepository.findById(id).ifPresent(bannerApply -> {
                bannerApply.setBannerStatus(BannerType.COMPLETE);
                bannerRepository.save(bannerApply); // 변경된 상태를 저장
            });
            log.info("set 변경");
        }
    }





}
