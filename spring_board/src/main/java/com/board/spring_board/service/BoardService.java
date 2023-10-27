package com.board.spring_board.service;

import com.board.spring_board.dto.*;
import com.board.spring_board.entity.Board;
import com.board.spring_board.entity.BoardCategory;
import com.board.spring_board.repository.BoardMapper;
import com.board.spring_board.security.PrincipalUser;
import lombok.RequiredArgsConstructor;
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
    @Transactional(rollbackFor = Exception.class)
    public boolean updateBoardContent(UpdateBoardReqDto updateBoardReqDto) {
        Board board = updateBoardReqDto.toEntity();
        return boardMapper.updateBoardContent(board) > 0;
    }
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteBoardContent(int boardId) {
//        String contentEmail = boardMapper.getBoardDetails(deleteBoardReqDto.getBoardId()).getEmail();
//        if(deleteBoardReqDto.getEmail() != contentEmail) {
//            return false;
//        }
        return boardMapper.deleteBoardContent(boardId) > 0;
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

    public int getBoardCount(String CategoryName, SearchBoardListReqDto searchBoardListReqDto) {
        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("categoryName", CategoryName);
        paramsMap.put("optionName", searchBoardListReqDto.getOptionName());
        paramsMap.put("searchValue", searchBoardListReqDto.getSearchValue());

        return boardMapper.getBoardCount(paramsMap);
    }
    public BoardDetailsRespDto getBoardDetails(int boardId) {
        return boardMapper.getBoardDetails(boardId).toBoardDetailsDto();
    }
    public boolean getBoardLikeState(int boardId) {
        Map<String, Object> paramsMap = new HashMap<>();
        PrincipalUser principalUser =
                (PrincipalUser) SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getPrincipal();
        String email = principalUser.getUsername();
        System.out.println(boardId);
        System.out.println(email);
        paramsMap.put("boardId", boardId);
        paramsMap.put("email", email);

        System.out.println("test " + boardMapper.getBoardLikeState(paramsMap));
        return boardMapper.getBoardLikeState(paramsMap) > 0;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean setBoardLike(int boardId) {
        Map<String, Object> paramsMap = new HashMap<>();
        PrincipalUser principalUser =
                (PrincipalUser) SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getPrincipal();
        String email = principalUser.getUsername();
        paramsMap.put("boardId", boardId);
        paramsMap.put("email", email);
        System.out.println("set");

        return boardMapper.insertBoardLike(paramsMap) > 0;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean cancelBoardLike(int boardId) {
        Map<String, Object> paramsMap = new HashMap<>();
        PrincipalUser principalUser =
                (PrincipalUser) SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getPrincipal();
        String email = principalUser.getUsername();
        paramsMap.put("boardId", boardId);
        paramsMap.put("email", email);
        System.out.println("del");

        return boardMapper.deleteBoardLike(paramsMap) > 0;
    }
}
