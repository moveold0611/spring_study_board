package com.board.spring_board.repository;

import com.board.spring_board.dto.RegisterBoardReqDto;
import com.board.spring_board.dto.WriteBoardReqDto;
import com.board.spring_board.entity.Board;
import com.board.spring_board.entity.BoardCategory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface BoardMapper {
    public List<BoardCategory> getBoardCategories();
    public Integer saveBoardContent(Board board);
    public Integer saveCategory(BoardCategory boardCategory);
    public List<Board> getBoardList(Map<String, Object> paramsMap);
    public Integer getBoardCount(Map<String, Object> paramsMap);
    public Board getBoardDetails(int boardId);
    public Integer getBoardLikeState(Map<String, Object> paramsMap);
    public Integer insertBoardLike(Map<String, Object> paramsMap);
    public Integer deleteBoardLike(Map<String, Object> paramsMap);
}
