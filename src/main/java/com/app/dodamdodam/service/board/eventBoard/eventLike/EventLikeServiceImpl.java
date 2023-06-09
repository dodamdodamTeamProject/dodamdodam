//package com.app.dodamdodam.service.board.eventBoard.eventLike;
//
//
//import com.app.dodamdodam.entity.event.EventBoard;
//import com.app.dodamdodam.entity.event.EventLike;
//import com.app.dodamdodam.entity.member.Member;
//import com.app.dodamdodam.repository.board.event.board.EventBoardRepository;
//import com.app.dodamdodam.repository.board.event.like.EventLikeRepository;
//import com.app.dodamdodam.repository.member.MemberRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Primary;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//import static org.aspectj.bridge.MessageUtil.fail;
//
//@Service
//@Qualifier("eventLike") @Primary
//@RequiredArgsConstructor
//public class EventLikeServiceImpl implements com.app.dodamdodam.service.board.eventBoard.eventLike.EventLikeService {
//
//    private final EventLikeRepository eventLikeRepository;
//
//    private final EventBoardRepository eventBoardRepository;
//
//    private final MemberRepository memberRepository;
//
//    @Override
//    public void insertHeart(Long eventBoardId, Long memberId) {
//        Optional<Member> member = memberRepository.findById(memberId);
//        Optional<EventBoard> eventBoard = eventBoardRepository.findById(eventBoardId);
//
//        if(!member.isPresent() || !eventBoard.isPresent()) fail("멤버 혹은 리뷰 보드가 존재하지 않음");
//
//        if(!eventLikeRepository.checkMemberLikesEventBoard_QueryDSL(memberId, eventBoardId)){
//            Integer eventLikeNumber = eventBoard.get().getEventLikeNumber();
//
//            EventLike eventLike = new EventLike(eventBoard.get(), member.get());
//            eventLikeRepository.save(eventLike);
//
//            eventBoard.get().setEventLikeCount(++eventLikeNumber);
//        }
//    }
//}
