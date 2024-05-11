package com.homeis.board.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.homeis.board.dto.Board;
import com.homeis.board.dto.Comment;
import com.homeis.board.dto.Likes;
import com.homeis.board.model.service.BoardService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
	private final BoardService boardService;
	
	@GetMapping("/list")
	public ResponseEntity<List<Board>> list() {
		List<Board> boards = boardService.selectAll();
		
		return ResponseEntity.ok(boards);
	}
	
	@GetMapping("/like")
	public ResponseEntity<List<Likes>> getLike() {
		List<Likes> likes = boardService.getBoardLike();
		
		return ResponseEntity.ok(likes);
	}
	
	@PatchMapping("/detail")
	public ResponseEntity<?> view(@RequestParam("id") int id) {
		int isSucceed = boardService.increaseView(id);
		
		if(isSucceed == 0) return ResponseEntity.notFound().build();
		
		
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/detail/{id}")
	public ResponseEntity<List<Comment>> detail(@PathVariable("id") int boardId) {
		List<Comment> comments = boardService.findById(boardId);
		
		return ResponseEntity.ok(comments);
	}
	
	@PostMapping("/regist")
	public ResponseEntity<?> regist(@ModelAttribute Board board) {
		int isSucceed = boardService.insertBoard(board);
		
		if(isSucceed == 0) return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> update(@ModelAttribute Board board) {
		int isSucceed = boardService.updateBoard(board);
		
		if(isSucceed == 0) return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<?> delete(@ModelAttribute Board board) {
		int isSucceed = boardService.deleteBoard(board);
		
		if(isSucceed == 0) return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/like")
	public ResponseEntity<?> likeRegist(@ModelAttribute Likes like) {
		int isSucceed = boardService.insertLike(like);
		
		if(isSucceed == 0) return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/like")
	public ResponseEntity<?> likeDelete(@ModelAttribute Likes like) {
		int isSucceed = boardService.deleteLike(like);
		
		if(isSucceed == 0) return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/comment/regist")
	public ResponseEntity<?> commentRegist(@ModelAttribute Comment comment) {
		int isSucceed = boardService.insertComment(comment);
		
		if(isSucceed == 0) return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok().build();
	}
	 
	@PutMapping("/comment/update")
	public ResponseEntity<?> commentupdate(@ModelAttribute Comment comment) {
		int isSucceed = boardService.updateComment(comment);
		
		if(isSucceed == 0) return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/comment/delete")
	public ResponseEntity<?> commentDelete(@ModelAttribute Comment comment) {
		int isSucceed = boardService.deleteComment(comment);
		
		if(isSucceed == 0) return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok().build();
	}
}
