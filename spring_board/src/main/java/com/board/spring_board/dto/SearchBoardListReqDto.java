package com.board.spring_board.dto;

import lombok.Data;

@Data
public class SearchBoardListReqDto {
    private String categoryName;
    private String searchValue;
}
