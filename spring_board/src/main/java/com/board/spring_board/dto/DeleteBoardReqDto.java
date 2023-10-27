package com.board.spring_board.dto;

import com.board.spring_board.entity.Board;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeleteBoardReqDto {
    private String email;
    private int boardId;

    public Board toEntity() {
        return Board.builder()
                .email(email)
                .boardId(boardId)
                .build();
    }
}
