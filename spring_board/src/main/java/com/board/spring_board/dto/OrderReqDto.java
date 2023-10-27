package com.board.spring_board.dto;

import com.board.spring_board.entity.Order;
import lombok.Builder;
import lombok.Data;
import org.aspectj.weaver.ast.Or;

@Data
@Builder
public class OrderReqDto {
    private int productId;
    private String email;

    public Order toEntity() {
        return Order.builder()
                .productId(productId)
                .email(email)
                .build();
    }
}
