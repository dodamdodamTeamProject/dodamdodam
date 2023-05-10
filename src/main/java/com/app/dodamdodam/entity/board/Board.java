package com.app.dodamdodam.entity.board;

import com.app.dodamdodam.audit.Period;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter @ToString
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board extends Period {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    private String boardTitle;
    private String boardContent;

    public void setBoardTitle(String boardTitle) {
        this.boardTitle = boardTitle;
    }

    public void setBoardContent(String boardContent) {
        this.boardContent = boardContent;
    }

    public Board(String boardTitle, String boardContent) {
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
    }
}
