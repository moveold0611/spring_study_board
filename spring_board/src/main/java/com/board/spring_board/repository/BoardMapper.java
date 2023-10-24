package com.board.spring_board.repository;

import com.board.spring_board.controller.BoardCategoryRespDto;
import com.board.spring_board.dto.RegisterBoardReqDto;
import com.board.spring_board.entity.BoardCategory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    public List<BoardCategory> getBoardCategories();
    public Integer addBoard(RegisterBoardReqDto registerBoardReqDto);
}
