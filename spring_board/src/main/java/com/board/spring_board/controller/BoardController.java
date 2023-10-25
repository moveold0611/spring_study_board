package com.board.spring_board.controller;

import com.board.spring_board.aop.annotation.ArgsAop;
import com.board.spring_board.aop.annotation.ValidAop;
import com.board.spring_board.dto.BoardListRespDto;
import com.board.spring_board.dto.RegisterBoardReqDto;
import com.board.spring_board.dto.SearchBoardListReqDto;
import com.board.spring_board.dto.WriteBoardReqDto;
import com.board.spring_board.service.BoardService;
import lombok.Getter;
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

    @ValidAop
    @PostMapping("/board/content")
    public ResponseEntity<?> writeBoard(@Valid @RequestBody WriteBoardReqDto writeBoardReqDto, BindingResult bindingResult) {
        return ResponseEntity.ok().body(boardService.writeBoardContent(writeBoardReqDto));
    }

    @GetMapping("/boards/{categoryName}/{page}")
    public ResponseEntity<?> getBoardList(@PathVariable String categoryName, @PathVariable int page, SearchBoardListReqDto searchBoardListReqDto) {
        return ResponseEntity.ok().body(boardService.getBoardList(categoryName, page, searchBoardListReqDto));
    }

}
