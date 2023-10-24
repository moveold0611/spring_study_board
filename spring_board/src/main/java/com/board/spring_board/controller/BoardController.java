package com.board.spring_board.controller;

import com.board.spring_board.aop.annotation.ValidAop;
import com.board.spring_board.dto.RegisterBoardReqDto;
import com.board.spring_board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/board/category")
    public ResponseEntity<?> getCategory() throws Exception{
        return ResponseEntity.ok().body(boardService.getBoardCategoriesAll());
    }
}
