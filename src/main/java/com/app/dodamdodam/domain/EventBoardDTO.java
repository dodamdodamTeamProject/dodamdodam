package com.app.dodamdodam.domain;

import com.app.dodamdodam.type.EventType;
import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Component
public class EventBoardDTO {

    /* 이벤트 보드 정보 다 받아오기*/
    private Long id;
    private String eventAddress;
    private String eventAddressDetail;
    private String eventStartDate;
    private String eventEndDate;
    private String eventIntroduction;

    /*게시글 정보*/
    private String boardTitle;
    private String boardContent;

    /* 좋아요 */
    private int eventLikeNumber;

    /*수락상태*/
    private EventType eventStatus;
    /*기업작성*/
    private String eventBusinessNumber;
    private String eventBusinessName;
    private String eventBusinessTel;
    private String eventBusinessEmail;
    /*멤버 정보 */
    private MemberDTO memberDTO;
    /*댓글 정보*/
    List<EventReviewDTO> reviews;
    /*파일*/
    private List<EventFileDTO> fileDTOS;
    private List<MultipartFile> eventFiles;
    private List<String> fileUrls;


    /*만든날짜, 업뎃일*/
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    public EventBoardDTO() {
        this.fileDTOS = new ArrayList<>();
    }

    @Builder
    public EventBoardDTO(Long id, String eventAddress, String eventAddressDetail, String eventStartDate, String eventEndDate, String eventIntroduction, String boardTitle, String boardContent, int eventLikeNumber, EventType eventStatus, String eventBusinessNumber, String eventBusinessName, String eventBusinessTel, String eventBusinessEmail, MemberDTO memberDTO, List<EventReviewDTO> reviews, List<EventFileDTO> fileDTOS, List<MultipartFile> eventFiles, LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.id = id;
        this.eventAddress = eventAddress;
        this.eventAddressDetail = eventAddressDetail;
        this.eventStartDate = eventStartDate;
        this.eventEndDate = eventEndDate;
        this.eventIntroduction = eventIntroduction;
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.eventLikeNumber = eventLikeNumber;
        this.eventStatus = eventStatus;
        this.eventBusinessNumber = eventBusinessNumber;
        this.eventBusinessName = eventBusinessName;
        this.eventBusinessTel = eventBusinessTel;
        this.eventBusinessEmail = eventBusinessEmail;
        this.memberDTO = memberDTO;
        this.reviews = reviews;
        this.fileDTOS = fileDTOS;
        this.eventFiles = eventFiles;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    public List<EventFileDTO> getFileDTOS() {
        return fileDTOS;
    }

    public void setFileDTOS(List<EventFileDTO> fileDTOS) {
        this.fileDTOS = fileDTOS;
    }

    public List<MultipartFile> getEventFiles() {
        return eventFiles;
    }

    public void setEventFiles(List<MultipartFile> eventFiles) {
        this.eventFiles = eventFiles;
    }

    public void setMemberDTO(MemberDTO memberDTO) {
        this.memberDTO = memberDTO;
    }

    public List<String> getFileUrls() {
        return fileUrls;
    }

    public void setFileUrls(List<String> fileUrls) {
        this.fileUrls = fileUrls;
    }

}
