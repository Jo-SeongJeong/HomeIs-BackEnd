package com.homeis.board.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.homeis.board.dto.Board;
import com.homeis.board.dto.BoardPaginationResponse;
import com.homeis.board.dto.Comment;
import com.homeis.board.dto.Likes;
import com.homeis.board.model.service.BoardService;
import com.homeis.util.JWTUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
	private final BoardService boardService;
	private final JWTUtil jwtUtil;
	
	@GetMapping("/list")
	public ResponseEntity<BoardPaginationResponse> list(
			@RequestParam(value = "size", defaultValue = "10") int size,
			@RequestParam(value = "page", defaultValue = "1") int page, 
			@RequestParam(value = "category", defaultValue = "id") String category) {

		BoardPaginationResponse response= boardService.selectAll(size, page, category);
		
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/detail/{id}")
	public ResponseEntity<?> detail(@PathVariable("id") int id, @RequestHeader(value = "Authorization", required = false) String tokenHeader) {
		String tokenId = null;
		
		if(tokenHeader != null && tokenHeader.startsWith("Bearer ")) {
			tokenId = jwtUtil.getIdFromToken(tokenHeader.substring(7));
		}
		
		Board board = boardService.findById(id, tokenId);
		
		if(board == null) return ResponseEntity.status(404).body("요청하신 글을 찾을 수 없습니다.");
		
		return ResponseEntity.ok(board);
	}
	
	@PostMapping("/insert")
	public ResponseEntity<?> regist(@RequestBody Board board) {
		int result = boardService.insertBoard(board);
		
		if(result == 0) return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> update(@RequestBody Board board) {
		int result = boardService.updateBoard(board);
		
		if(result == 0) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("권한 없음. 잘못된 접근.");
		
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("/delete")
	public ResponseEntity<?> delete(@RequestBody Board board) {
		int result = boardService.deleteBoard(board);
		
		if(result == 0) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("권한 없음. 잘못된 접근.");
		
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/like")
	public ResponseEntity<?> likeRegist(@RequestBody Likes like) {
		int result = boardService.insertLike(like);
		
		if(result == 0) return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("/like")
	public ResponseEntity<?> likeDelete(@RequestBody Likes like) {
		int result = boardService.deleteLike(like);
		
		if(result == 0) return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/insert-comment")
	public ResponseEntity<?> commentRegist(@RequestBody Comment comment) {
		int result = boardService.insertComment(comment);
		
		if(result == 0) return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok().build();
	}
	 
	@PutMapping("/update-comment")
	public ResponseEntity<?> commentupdate(@RequestBody Comment comment) {
		int result = boardService.updateComment(comment);
		
		if(result == 0) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("권한 없음. 잘못된 접근.");
		
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("/delete-comment")
	public ResponseEntity<?> commentDelete(@RequestBody Comment comment) {
		int result = boardService.deleteComment(comment);
		
		if(result == 0) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("권한 없음. 잘못된 접근.");
		
		return ResponseEntity.ok().build();
	}
}
