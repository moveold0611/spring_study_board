package com.board.spring_board.dto;

import com.board.spring_board.entity.Point;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PointUseReqDto {
    private String email;
    private int pointHistoryPrice;

    public Point toEntity() {
        return Point.builder()
                .email(email)
                .pointHistoryPrice(pointHistoryPrice * -1)
                .build();
    }
}
