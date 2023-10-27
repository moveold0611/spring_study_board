package com.board.spring_board.service;

import com.board.spring_board.dto.OrderReqDto;
import com.board.spring_board.repository.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class OrderService {
    private final OrderMapper orderMapper;

    @Transactional(rollbackFor = Exception.class)
    public boolean order(OrderReqDto orderReqDto) {
        return orderMapper.order(orderReqDto.toEntity()) > 0;
    }
}
