package com.app.dodamdodam.search.board;

import com.app.dodamdodam.type.CategoryType;
import lombok.Data;

@Data
public class AdminFreeBoardSearch {
    private CategoryType freeCategory;
    private String boardTitle;
    private String memberName;

}