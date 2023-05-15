package com.app.dodamdodam.service.member;

import com.app.dodamdodam.entity.banner.BannerApply;
import com.app.dodamdodam.entity.free.FreeBoard;
import com.app.dodamdodam.entity.member.Member;
import com.app.dodamdodam.entity.point.Point;
import com.app.dodamdodam.entity.recruitment.RecruitmentBoard;
import com.app.dodamdodam.repository.banner.BannerRepository;
import com.app.dodamdodam.repository.board.free.FreeBoardRepository;
import com.app.dodamdodam.repository.board.purchase.PurchaseBoardRepository;
import com.app.dodamdodam.repository.board.recruitment.RecruitmentBoardRepository;
import com.app.dodamdodam.repository.member.MemberRepository;
import com.app.dodamdodam.repository.point.PointRepository;
import com.app.dodamdodam.type.MemberStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PointRepository pointRepository;

    @Autowired
    private FreeBoardRepository freeBoardRepository;

    @Autowired
    private PurchaseBoardRepository purchaseBoardRepository;

    @Autowired
    private RecruitmentBoardRepository recruitmentBoardRepository;

    @Autowired
    private BannerRepository bannerRepository;

    /* 로그인 된 유저 정보 */
    @Override
    public Optional<Member> getMemberInfo(Long memberId) {
        return memberRepository.findById(memberId);
    }

    /* 내 포인트 내역 */
    @Override
    public List<Point> getMyPointList(Long memberId) {
        return pointRepository.findPointByMemberId_QueryDSL(memberId);
    }

    /* 내가 작성한 자유 게시글 목록 */
    @Override
    public Page<FreeBoard> getMyFreeBoardList(Pageable pageable, Long memberId) {
        return freeBoardRepository.findFreeBoardListByMemberId_QueryDSL(pageable, memberId);
    }

    /* 내가 작성한 자유게시글 개수 */
    @Override
    public Long getMyFreeBoardListCount(Long memberId) {
        return freeBoardRepository.findFreeBoardListCountByMemberId_QueryDSL(memberId);
    }

    /* 내가 작성한 판매게시글 개수 */
    @Override
    public Long getMyPurchaseBoardListCount(Long memberId) {
        return purchaseBoardRepository.findPurchaseBoardListCountByMemberId_QueryDSL(memberId);
    }

    /* 내가 작성한 모집게시글 개수 */
    @Override
    public Long getMyRecruitmentBoardListCount(Long memberId) {
        return recruitmentBoardRepository.findRecruitmentBoardListCountByMemberId_QueryDSL(memberId);
    }

    /* 내가 참가한 모집게시글 개수 */
    @Override
    public Long getMyRecruitmentedBoardListCount(Long memberId) {
        return recruitmentBoardRepository.findRecruitmentedBoardListCountByMemberId_QueryDSL(memberId);
    }

    /* 회원 비활성화 처리*/
    @Override
    public void setMemberStatusById(Long memberId) {
        memberRepository.findById(memberId).ifPresent(member -> member.setMemberStatus(MemberStatus.WITHDRAWAL));
    }

    /* 회원 정보 수정 */
    /* 페이지를 고치던지 2개 따로 만들던지 해야함 */
    @Override
    public void setMemberInfoById(Long memberId, Member memberInfo) {
        memberRepository.findById(memberId).ifPresent(member -> {
            member.setMemberPassword(memberInfo.getMemberPassword());
            member.setAddress(memberInfo.getAddress());
            member.setMemberEmail(memberInfo.getMemberEmail());
            member.setMemberName(memberInfo.getMemberName());
            member.setMemberPhone(memberInfo.getMemberPhone());
        });
    }

    /* 비밀번호 변경 */
    @Override
    public void setMemberPasswordById(Long memberId, String password) {
        memberRepository.findById(memberId).ifPresent(member -> member.setMemberPassword(password));
    }

    /* 배너 신청 */
    @Override
    public void saveBannerApply(Long memberId, BannerApply bannerApply) {
        memberRepository.findById(memberId).ifPresent(member -> bannerApply.setMember(member));
        bannerRepository.save(bannerApply);
    }

    /* 내가 참가한 모집 게시글 전체 */
    @Override
    public List<RecruitmentBoard> getMyRecruitementedBoardList(Long memberId) {
        return recruitmentBoardRepository.findAllRecruitmentedBoardListByMemberId_QueryDSL(memberId);
    }

}
