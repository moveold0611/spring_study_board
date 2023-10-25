package com.board.spring_board.service;

import com.board.spring_board.dto.BoardCategoryRespDto;
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
import java.util.List;

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

        System.out.println("test " + writeBoardReqDto);
        return boardMapper.saveBoardContent(board) > 0;
    }

}
