package com.board.spring_board.entity;

import com.board.spring_board.dto.ProductRespDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    private int productId;
    private String productName;
    private int productPrice;

    public ProductRespDto toProductDto() {
        return ProductRespDto.builder()
                .productId(productId)
                .productName(productName)
                .productPrice(productPrice)
                .build();
    }
}
