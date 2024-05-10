package com.homeis.board.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.homeis.board.dto.Board;
import com.homeis.board.dto.Comment;
import com.homeis.board.model.service.BoardService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
	private final BoardService boardService;
	
	@GetMapping("/list")
	public List<Board> list() {
		List<Board> boards = boardService.selectAll();
		
		return boards;
	}
	
	@PutMapping("/detail")
	public int view(@RequestParam("id") int id) {
		int isSuceed = boardService.increaseView(id);
		
		return isSuceed;
	}
	
	@GetMapping("/detail")
	public List<Comment> detail(@RequestParam("id") int boardId) {
		List<Comment> comments = boardService.findById(boardId);
		
		return comments;
	}
	
	@PostMapping("/regist")
	public int regist(@ModelAttribute Board board) {
		int isSucceed = boardService.insertBoard(board);

		return isSucceed;
	}
	
	@PutMapping("/update")
	public int update(@ModelAttribute Board board) {
		int isSucceed = boardService.updateBoard(board);
		
		return isSucceed;
	}
	
	@DeleteMapping("/delete")
	public int delete(@ModelAttribute Board board) {
		int isSucceed = boardService.deleteBoard(board);
		
		return isSucceed;
	}
}
