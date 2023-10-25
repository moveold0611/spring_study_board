package com.board.spring_board.entity;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class Board {
    private int boardId;
    private String boardTitle;
    private int boardCategoryId;
    private String boardContent;
    private String email;
    private LocalDateTime createDate;
}
