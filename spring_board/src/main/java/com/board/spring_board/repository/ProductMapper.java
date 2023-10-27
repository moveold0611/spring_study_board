package com.board.spring_board.repository;

import com.board.spring_board.entity.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {
    public List<Product> selectProducts();
}
