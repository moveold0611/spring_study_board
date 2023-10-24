package com.board.spring_board.entity;

import com.board.spring_board.controller.BoardCategoryRespDto;
import lombok.Data;

@Data
public class BoardCategory {
    private int boardCategoryId;
    private String boardCategoryName;

    public BoardCategoryRespDto toCategoryDto() {
        return BoardCategoryRespDto.builder()
                .boardCategoryId(boardCategoryId)
                .boardCategoryName(boardCategoryName)
                .build();
    }

}
