package com.board.spring_board.repository;

import com.board.spring_board.entity.Order;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper {
    public Integer order(Order order);
}
