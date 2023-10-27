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
public class Order {
    private int orderId;
    private int productId;
    private String email;
    private LocalDateTime orderDate;
}
