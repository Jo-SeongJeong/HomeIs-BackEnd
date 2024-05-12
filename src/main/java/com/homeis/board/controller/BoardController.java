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
		List<Board> boardList = boardService.selectAll();
		
		return ResponseEntity.ok(boardList);
	}
	
	@GetMapping("/like")
	public ResponseEntity<List<Likes>> getLike() {
		List<Likes> likeList = boardService.getBoardLike();
		
		return ResponseEntity.ok(likeList);
	}
	
	@PatchMapping("/detail")
	public ResponseEntity<?> view(@RequestParam("id") int id) {
		int result = boardService.increaseView(id);
		
		if(result == 0) return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/detail/{id}")
	public ResponseEntity<List<Comment>> detail(@PathVariable("id") int boardId) {
		List<Comment> commentList = boardService.findById(boardId);
		
		return ResponseEntity.ok(commentList);
	}
	
	@PostMapping("/insert")
	public ResponseEntity<?> regist(@ModelAttribute Board board) {
		int result = boardService.insertBoard(board);
		
		if(result == 0) return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> update(@ModelAttribute Board board) {
		int result = boardService.updateBoard(board);
		
		if(result == 0) return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<?> delete(@ModelAttribute Board board) {
		int result = boardService.deleteBoard(board);
		
		if(result == 0) return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/like")
	public ResponseEntity<?> likeRegist(@ModelAttribute Likes like) {
		int result = boardService.insertLike(like);
		
		if(result == 0) return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/like")
	public ResponseEntity<?> likeDelete(@ModelAttribute Likes like) {
		int result = boardService.deleteLike(like);
		
		if(result == 0) return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/insert-comment")
	public ResponseEntity<?> commentRegist(@ModelAttribute Comment comment) {
		int result = boardService.insertComment(comment);
		
		if(result == 0) return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok().build();
	}
	 
	@PutMapping("/update-comment")
	public ResponseEntity<?> commentupdate(@ModelAttribute Comment comment) {
		int result = boardService.updateComment(comment);
		
		if(result == 0) return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/delete-comment")
	public ResponseEntity<?> commentDelete(@ModelAttribute Comment comment) {
		int result = boardService.deleteComment(comment);
		
		if(result == 0) return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok().build();
	}
}
