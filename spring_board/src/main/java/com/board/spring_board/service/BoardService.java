package com.board.spring_board.service;

import com.board.spring_board.dto.BoardCategoryRespDto;
import com.board.spring_board.dto.BoardListRespDto;
import com.board.spring_board.dto.SearchBoardListReqDto;
import com.board.spring_board.dto.WriteBoardReqDto;
import com.board.spring_board.entity.Board;
import com.board.spring_board.entity.BoardCategory;
import com.board.spring_board.repository.BoardMapper;
import com.board.spring_board.security.PrincipalUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardMapper boardMapper;

    public List<BoardCategoryRespDto> getBoardCategoriesAll() {
        List<BoardCategoryRespDto> boardCategoryRespDtos = new ArrayList<>();
        boardMapper.getBoardCategories().forEach(category -> {
            boardCategoryRespDtos.add(category.toCategoryDto());
        });
        return boardCategoryRespDtos;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean writeBoardContent(WriteBoardReqDto writeBoardReqDto) {
        BoardCategory boardCategory = null;
        if(writeBoardReqDto.getCategoryId() == 0) {
            boardCategory = BoardCategory.builder()
                    .boardCategoryName(writeBoardReqDto.getCategoryName())
                    .build();
            boardMapper.saveCategory(boardCategory) ;
            writeBoardReqDto.setCategoryId(boardCategory.getBoardCategoryId());
        }

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Board board = writeBoardReqDto.toBoardEntity(email);

        return boardMapper.saveBoardContent(board) > 0;
    }

    public List<BoardListRespDto> getBoardList(String categoryName, int page, SearchBoardListReqDto searchBoardListReqDto) {
        int index = (page - 1) * 10;
        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("index", index);
        paramsMap.put("categoryName", categoryName);
        paramsMap.put("optionName", searchBoardListReqDto.getOptionName());
        paramsMap.put("searchValue", searchBoardListReqDto.getSearchValue());

        List<BoardListRespDto> boardListRespDto = new ArrayList<>();
        boardMapper.getBoardList(paramsMap).forEach(board -> {
            boardListRespDto.add(board.toBoardListDto());
        });
        return boardListRespDto;
    }
}
