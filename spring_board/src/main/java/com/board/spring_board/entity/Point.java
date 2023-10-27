package com.board.spring_board.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Point {
    private int pointHistoryId;
    private int pointHistoryType;
    private int pointHistoryPrice;
    private String email;
    private LocalDateTime historyDate;
}
