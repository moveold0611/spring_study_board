package com.board.spring_board.dto;

import lombok.Data;

@Data
public class BoardLikeReqDto {
    private int boardId;
    private String email;
}
