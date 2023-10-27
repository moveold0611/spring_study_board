package com.board.spring_board.controller;

import com.board.spring_board.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService storeService;

    @GetMapping("/products")
    public ResponseEntity<?> getProducts() {
        return ResponseEntity.ok().body(storeService.getProducts());
    }
}
