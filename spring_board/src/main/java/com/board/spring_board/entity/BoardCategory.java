package com.board.spring_board.entity;

import com.board.spring_board.dto.BoardCategoryRespDto;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class BoardCategory {
    private int boardCategoryId;
    private String boardCategoryName;
    private int boardCount;

    public BoardCategoryRespDto toCategoryDto() {
        return BoardCategoryRespDto.builder()
                .boardCategoryId(boardCategoryId)
                .boardCategoryName(boardCategoryName)
                .boardCount(boardCount)
                .build();
    }

}
