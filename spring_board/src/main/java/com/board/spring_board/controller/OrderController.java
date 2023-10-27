package com.board.spring_board.controller;

import com.board.spring_board.dto.OrderReqDto;
import com.board.spring_board.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/order")
    public ResponseEntity<?> order(@RequestBody OrderReqDto orderReqDto) {
        return ResponseEntity.ok().body(orderService.order(orderReqDto));
    }
}
