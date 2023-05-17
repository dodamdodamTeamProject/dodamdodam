package com.app.dodamdodam.controller;

import com.app.dodamdodam.domain.*;
import com.app.dodamdodam.entity.inquiry.Inquiry;
import com.app.dodamdodam.service.board.freeBoard.FreeBoardService;
import com.app.dodamdodam.service.board.purchase.PurchaseBoardService;
import com.app.dodamdodam.service.inquiry.InquiryService;
import com.app.dodamdodam.service.member.MemberService;
import com.app.dodamdodam.service.point.PointService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admins/*")
@Slf4j
public class AdminController {
    private final InquiryService inquiryService;
    private final PointService pointService;
    private final FreeBoardService freeBoardService;
    private final PurchaseBoardService purchaseBoardService;
    private final MemberService memberService;

    /*문의 게시판*/
    @GetMapping("inquiry/list") //문의 게시판 목록
    public String adminInquiryList(@RequestParam(value = "page", defaultValue = "1") Integer page) {
        return "admin/inquiry-list";
    }

    @ResponseBody
    @PostMapping("inquiry/list")  //문의 게시판 목록
    public Page<InquiryDTO> adminInquiryGetListJson(@RequestParam(value = "page", defaultValue = "1") Integer page) {
        Pageable pageable = PageRequest.of(1, 10, Sort.Direction.DESC, "inquiryId");
        return inquiryService.showList(pageable);
    }
//
//    @ResponseBody
//    @PostMapping("inquiry/search")
//    public Page<AdminInquirySearchDTO> {
//        return null;
//    }

    /*포인트 게시판 */
    @GetMapping("point/list") //포인트 게시판 목록
    public String adminPointList(){
        return "admin/point-list";
    }

    @ResponseBody
    @PostMapping("point/list")
    public Page<PointDTO> getPointList(@RequestParam(value = "page", defaultValue = "1") Integer page) {
        Pageable pageable = PageRequest.of(1, 10, Sort.Direction.DESC, "pointId");
        return pointService.showList(pageable);
    }


    /*자유 게시판 */
    @GetMapping("free-board/list") //자유 게시판 목록
    public String adminfreeBoardList(){
        return "admin/free-board-list";
    }

    @ResponseBody
    @PostMapping("free-board/list") //자유 게시판 목록
    public Page<FreeBoardFileDTO> getFreeBoardList(@RequestParam(value = "page", defaultValue = "1") Integer page){
        Pageable pageable = PageRequest.of(1, 10, Sort.Direction.DESC, "freeBoardId");
        return freeBoardService.getAdminFreeBoardList(pageable);
    }


    /*판매 게시판 */
    @GetMapping("purchase-board/list") //판매 게시판 목록
    public String adminPurchaseBoardList(){
        return "admin/purchase-board-list";
    }

    @ResponseBody
    @PostMapping("purchase-board/list") //판매 게시판 목록
    public Page<PurchaseBoardDTO> getPurchaseBoardList(@RequestParam(value = "page", defaultValue = "1") Integer page){
        Pageable pageable = PageRequest.of(1, 10, Sort.Direction.DESC, "purchaseBoardId");
        return purchaseBoardService.showList(pageable);
    }

    /*멤버 게시판*/
    @GetMapping("member/list") //판매 게시판 목록
    public String adminMemberList(){
        return "admin/member-list";
    }

    @ResponseBody
    @PostMapping("member/list") //판매 게시판 목록
    public Page<MemberDTO> getMemberList(@RequestParam(value = "page", defaultValue = "1") Integer page){
        Pageable pageable = PageRequest.of(1, 10, Sort.Direction.DESC, "memberId");
        return memberService.showList(pageable);
    }

    /*이벤트 게시글*/
    

    /*모집 게시글*/

}