package com.app.dodamdodam.domain;

import com.app.dodamdodam.type.CategoryType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class FreeBoardDTO {
    private Long id;
    private String boardTitle;
    private String boardContent;
    private MemberDTO memberDTO;
    private CategoryType freeCategory;
    private int likeCount;

    private List<FreeFileDTO> freeFileDTOS;
    private List<FreeBoardReplyDTO> freeBoardReplyDTOS;


    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    @Builder
    public FreeBoardDTO(Long id, String boardTitle, String boardContent, MemberDTO memberDTO, CategoryType freeCategory, int likeCount, List<FreeFileDTO> freeFileDTOS, List<FreeBoardReplyDTO> freeBoardReplyDTOS, LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.id = id;
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.memberDTO = memberDTO;
        this.freeCategory = freeCategory;
        this.likeCount = likeCount;
        this.freeFileDTOS = freeFileDTOS;
        this.freeBoardReplyDTOS = freeBoardReplyDTOS;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }
}
