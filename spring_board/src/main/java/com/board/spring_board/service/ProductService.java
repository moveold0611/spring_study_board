package com.board.spring_board.service;

import com.board.spring_board.dto.ProductRespDto;
import com.board.spring_board.entity.Product;
import com.board.spring_board.repository.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductMapper storeMapper;

    public List<ProductRespDto> getProducts() {
        List<ProductRespDto> resultList = new ArrayList<>();
        storeMapper.selectProducts().forEach(product -> {
            resultList.add(product.toProductDto());
        });
        return resultList;
    }
}
