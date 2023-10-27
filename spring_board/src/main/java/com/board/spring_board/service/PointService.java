package com.board.spring_board.service;

import com.board.spring_board.dto.PointUseReqDto;
import com.board.spring_board.repository.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PointService {
    private final OrderMapper orderMapper;
    @Transactional(rollbackFor = Exception.class)
    public boolean pointUse(PointUseReqDto pointUseReqDto) {

        return orderMapper.pointUse(pointUseReqDto.toEntity()) > 0;

    }
}
