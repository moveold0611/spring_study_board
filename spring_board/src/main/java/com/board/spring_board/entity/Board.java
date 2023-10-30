package com.board.spring_board.entity;

import com.board.spring_board.dto.BoardDetailsRespDto;
import com.board.spring_board.dto.BoardListRespDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Board {
    private int boardId;
    private String boardTitle;
    private int boardCategoryId;
    private String boardCategoryName;
    private String boardContent;
    private String email;
    private String nickname;
    private LocalDateTime createDate;
    private int boardHitsCount;
    private int boardLikeCount;

    public BoardListRespDto toBoardListDto() {
        return BoardListRespDto.builder()
                .boardId(boardId)
                .title(boardTitle)
                .nickname(nickname)
                .createDate(createDate.format(DateTimeFormatter.ISO_DATE))
                .hitsCount(boardHitsCount)
                .likeCount(boardLikeCount)
                .build();
    }

    public BoardDetailsRespDto toBoardDetailsDto() {
        return BoardDetailsRespDto.builder()
                .boardId(boardId)
                .boardTitle(boardTitle)
                .nickname(nickname)
                .email(email)
                .boardContent(boardContent)
                .boardCategoryId(boardCategoryId)
                .boardCategoryName(boardCategoryName)
                .createDate(createDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)))
                .boardHitsCount(boardHitsCount)
                .boardLikeCount(boardLikeCount)
                .build();
    }
}
