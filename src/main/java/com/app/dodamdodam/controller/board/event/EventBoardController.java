package com.app.dodamdodam.controller.board.event;

import com.app.dodamdodam.domain.EventBoardDTO;
import com.app.dodamdodam.domain.EventFileDTO;
import com.app.dodamdodam.domain.EventReplyDTO;
import com.app.dodamdodam.entity.event.EventBoard;
import com.app.dodamdodam.entity.event.EventReply;
import com.app.dodamdodam.search.EventBoardSearch;
import com.app.dodamdodam.service.board.eventBoard.EventBoardService;
import com.app.dodamdodam.service.board.eventBoard.eventReply.EventReplyService;
import com.app.dodamdodam.type.EventType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/event/*")
@RequiredArgsConstructor
@Slf4j
public class EventBoardController {
    @Qualifier
    private final EventBoardService eventBoardService;
    private final EventReplyService eventReplyService;

    /* 이벤트 게시판 메인 */
    @GetMapping("list")
    public String goList(Model model){
        log.info("@LLllllllllllllll");
        return "event-board/event-board-list";
    }

    /* 문화공간 게시판 */
    @GetMapping("culture-space")
    public String cultureSpace(Model model){
        log.info("문화공간 게시판 들어옴");
        return "culture-space/banner-test";
    }

    /* 이벤트 게시판 리스트(최신순) */
    @ResponseBody
    @GetMapping("list-search")
    public List<EventBoardDTO> getEventBoardList(@RequestParam int page, @RequestParam String search, @RequestParam String eventType){
        log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        EventBoardSearch eventBoardSearch = new EventBoardSearch();
        eventBoardSearch.setBoardContent(search);
        eventBoardSearch.setBoardTitle(search);
        eventBoardSearch.setWriterName(search);
        EventType eventStatus = null;
        if (eventStatus.equals("전체")){
            eventStatus = EventType.ALL;
        } else if(eventStatus.equals("진행중")){
            eventStatus = EventType.PROGRESS;
        } else if (eventStatus.equals("진행전")){
            eventStatus = EventType.HOLD;
        } else if (eventStatus.equals("이벤트")){
            eventStatus = EventType.END;
        } else {
            eventStatus = null;
        }

        log.info(eventBoardSearch.toString());

        Pageable pageable = PageRequest.of(page,8);

        List<EventBoardDTO> eventboards = eventBoardService.getEventBoardsBySearch(pageable, eventBoardSearch, eventStatus);
        log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        log.info(eventboards.toString());

        return eventboards;
    }

    /* 이벤트 게시판 상세보기 */
//    @GetMapping("detail/{boardId}")
//    public String eventBoardDetail(@PathVariable(value = "boardId") Long boardId, Model model, HttpSession session) {
//        session.setAttribute("boardId", 31L);
//        Long id = (Long) session.getAttribute("boardId");
//        model.addAttribute("eventBoardDetail", eventBoardService.getDetail(id));
//        /* PageRequest는 뭐 넣어도 상관없이 개수 가져와서 아무렇게나 넣음 */
//        model.addAttribute("replyCount", eventReplyService.getEventRepliesCountByBoardId(PageRequest.of(0, 5), id));
//        return "event-board/event-board-detail";
//    }

    /* 이벤트 상세페이지 이동 */
    @GetMapping("detail")
    public String goEventDetail(HttpSession session, Model model) {
        session.setAttribute("boardId", 31L);
        Long boardId = (Long)session.getAttribute("boardId");
        log.info(boardId+"boardId");
        log.info("=========================================" + eventBoardService.getDetail(boardId).toString());
        model.addAttribute("eventBoardDetail", eventBoardService.getDetail(boardId));
        model.addAttribute("replyCount", eventReplyService.getEventRepliesCountByBoardId(PageRequest.of(0, 5), boardId));
        return "event-board/event-board-detail";
    }

    /* 이벤트 작성하기 */
    @GetMapping("write")
    public String goToWriteForm(HttpSession session) {
        /* 임시로 세션에 memberId 담아둠 */
        session.setAttribute("memberId", 1L);
        return "event-board/event-board-write";
    }

    @PostMapping("write")
    public RedirectView write(EventBoardDTO eventBoardDTO, HttpSession session) {
        Long memberId = (Long) session.getAttribute("memberId");
        log.info(eventBoardDTO.toString());

        // 파일 저장 로직 추가
        List<MultipartFile> eventFiles = eventBoardDTO.getEventFiles();
        List<EventFileDTO> fileDTOS = new ArrayList<>();

        if (eventFiles != null && !eventFiles.isEmpty()) {
            for (MultipartFile file : eventFiles) {
                // 파일을 저장하고 저장된 파일의 경로를 fileUrl에 설정
                String fileUrl = saveFile(file);

                // 파일 DTO 생성 및 정보 설정
                EventFileDTO fileDTO = EventFileDTO.builder()
                        .fileOriginalName(file.getOriginalFilename())
                        .fileUuid(UUID.randomUUID().toString())
                        .filePath(fileUrl)
                        .fileSize(file.getSize())
                        .build();

                fileDTOS.add(fileDTO);
            }
        }

        // 파일 DTO 리스트를 eventBoardDTO에 추가
        eventBoardDTO.setFileDTOS(fileDTOS);

        // eventBoardDTO를 사용하여 게시글 작성 등 필요한 처리 수행
        eventBoardService.write(eventBoardDTO, memberId);
        return new RedirectView("/event/list");
    }

    private String saveFile(MultipartFile file) {
        try {
            String fileName = file.getOriginalFilename();
            String basePath = "c:/upload/"; // 파일을 저장할 기본 경로
            String filePath = basePath + fileName; // 파일을 저장할 경로 설정
            File destFile = new File(filePath);
            file.transferTo(destFile); // 파일을 저장

            return filePath; // 저장된 파일의 경로 반환
        } catch (IOException e) {
            // 파일 저장 중에 예외가 발생한 경우 처리
            e.printStackTrace();
            return null; // 예외 발생 시 null 반환 또는 예외 처리 방식에 맞게 수정
        }
    }

    /* 이벤트 게시판 댓글 작성 */
    @PostMapping("write-reply")
    @ResponseBody
    public Long writeReply(String replyContent, HttpSession session){
        session.setAttribute("boardId", 31L);
        Long boardId = (Long)session.getAttribute("boardId");
        EventReply eventReply = new EventReply(replyContent);
        log.info("=============================댓글작성들어옴=====================================");
        log.info("이벤트 게시판 댓글 : " + replyContent);
        log.info("게시판 ID : " + boardId);
        Long memberId = (Long)session.getAttribute("memberId");
        eventReplyService.saveEventBoardReply(eventReply, boardId, memberId);
        Long replyCount = eventReplyService.getEventRepliesCountByBoardId(PageRequest.of(0, 5), boardId);
        return replyCount;
    }

    /* 이벤트 게시판 댓글 수정 */
    @PostMapping("update-reply/{replyId}")
    @ResponseBody
    public String updateReply(String updatedEventReply, @PathVariable(value = "replyId") Long replyId, HttpSession session) {
        log.info("댓글 수정 들어옴");
        log.info("댓글 : " + updatedEventReply);
        log.info("댓글 : " + replyId);
//        session.setAttribute("boardId", 31L);
//        Long boardId = (Long)session.getAttribute("boardId");

        EventReply updatedReply = new EventReply(updatedEventReply);
        eventReplyService.setEventReplyContent(updatedReply, replyId);
        return "success##";
    }

    /* 이벤트 게시판 댓글 삭제 */
    @PostMapping("delete-reply/{replyId}")
    @ResponseBody
    public Integer deleteReply(@PathVariable(value = "replyId") Long replyId, HttpSession session){
        log.info("댓글 삭제 들어옴");
        session.setAttribute("boardId", 31L);
        Long boardId = (Long)session.getAttribute("boardId");
        Integer replyCount = eventReplyService.getEventRepliesCountByReplyId(replyId);
        eventReplyService.removeEventReply(replyId);
        return replyCount - 1;
    }

    /* 이벤트 게시판 댓글 리스트 */
    @GetMapping("replies/{page}")
    @ResponseBody
    public List<EventReplyDTO> getReplies(@PathVariable(value = "page") int page, HttpSession session){
        session.setAttribute("boardId", 31L);
        Long boardId = (Long)session.getAttribute("boardId");
        log.info("==========================댓글 리스트 들어옴================================");
        log.info(boardId + " || " + page);
        Pageable pageable = PageRequest.of(page, 5);
        return eventReplyService.getEventRepliesByBoardId(pageable, boardId);
    }
}
