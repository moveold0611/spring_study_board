package com.board.spring_board.entity;

import lombok.Data;

@Data
public class Board {
    private int boardId;
    private String boardTitle;
    private int boardCategoryId;
    private String boardContent;
    private String email;
    private String createDate;
}
