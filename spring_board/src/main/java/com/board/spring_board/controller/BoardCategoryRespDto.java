package com.board.spring_board.controller;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class BoardCategoryRespDto {
    private int boardCategoryId;
    private String boardCategoryName;
}
