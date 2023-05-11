package com.app.dodamdodam.service.member;

import com.app.dodamdodam.domain.MemberDTO;
import com.app.dodamdodam.entity.member.Member;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface MemberService extends UserDetailsService {
    /* 로그인 된 유저 정보 가져오기 */
    public Optional<Member> getMemberInfo(Long memberId);


    //    회원가입
    public void join(MemberDTO memberDTO, PasswordEncoder passwordEncoder);

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

    default Member toMemberEntity(MemberDTO memberDTO){
        return Member.builder().id(memberDTO.getId())
                .memberEmail(memberDTO.getMemberEmail())
                .memberName(memberDTO.getMemberName())
                .memberId(memberDTO.getMemberId())
                .memberPassword(memberDTO.getMemberPassword())
                .memberPhone(memberDTO.getMemberPhone())
                .memberStatus(memberDTO.getMemberStatus())
                .address(memberDTO.getAddress())
                .build();
    }




}
