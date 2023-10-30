package com.board.spring_board.dto;

import com.board.spring_board.entity.Board;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@Builder
public class UpdateBoardReqDto {

    private String boardContent;
    private String boardTitle;
    private int categoryId;
    private String categoryName;
    private int boardId;

    public Board toEntity() {
        return Board.builder()
                .boardContent(boardContent)
                .boardTitle(boardTitle)
                .boardCategoryId(categoryId)
                .boardId(boardId)
                .build();
    }
}
