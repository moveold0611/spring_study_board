package com.board.spring_board.controller;

import com.board.spring_board.aop.annotation.ValidAop;
import com.board.spring_board.dto.*;
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
    @ValidAop
    @PostMapping("/board/content")
    public ResponseEntity<?> writeBoard(@Valid @RequestBody WriteBoardReqDto writeBoardReqDto, BindingResult bindingResult) {
        return ResponseEntity.ok().body(boardService.writeBoardContent(writeBoardReqDto));
    }
    @GetMapping("/boards/{categoryName}/{page}")
    public ResponseEntity<?> getBoardList(@PathVariable String categoryName, @PathVariable int page, SearchBoardListReqDto searchBoardListReqDto) {
        return ResponseEntity.ok().body(boardService.getBoardList(categoryName, page, searchBoardListReqDto));
    }
    @GetMapping("/board/{categoryName}/all")
    public ResponseEntity<?> getBoardCount(@PathVariable String categoryName, SearchBoardListReqDto searchBoardListReqDto) {
        return ResponseEntity.ok().body(boardService.getBoardCount(categoryName, searchBoardListReqDto));
    }
    @GetMapping("/board/details/{boardId}")
    public ResponseEntity<?> getBoardDetails(@PathVariable int boardId) {
        System.out.println(boardService.getBoardDetails(boardId));
        return ResponseEntity.ok().body(boardService.getBoardDetails(boardId));
    }
    @GetMapping("/board/like/{boardId}")
    public ResponseEntity<?> getBoardLikeState(@PathVariable int boardId) {
        System.out.println(boardId);
        return ResponseEntity.ok().body(boardService.getBoardLikeState(boardId));
    }

    @PostMapping("/board/like/set/{boardId}")
    public ResponseEntity<?> addBoardLike(@PathVariable int boardId ) {
        return ResponseEntity.ok().body(boardService.setBoardLike(boardId));
    }
    @DeleteMapping("/board/like/del/{boardId}")
    public ResponseEntity<?> cancelBoardLike(@PathVariable int boardId) {
        return ResponseEntity.ok().body(boardService.cancelBoardLike(boardId));
    }


}
