package com.board.spring_board.dto;

import com.board.spring_board.entity.Board;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
public class WriteBoardReqDto {
    @NotBlank
    private String title;
    @NotBlank
    private String content;

    private int categoryId;
    @NotBlank
    private String categoryName;

    public Board toBoardEntity(String email) {
        return Board.builder()
                .boardTitle(title)
                .boardCategoryId(categoryId)
                .boardContent(content)
                .email(email)
                .build();
    }
}
