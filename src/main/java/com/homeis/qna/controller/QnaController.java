package com.homeis.qna.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.homeis.qna.dto.Qna;
import com.homeis.qna.dto.QnaComment;
import com.homeis.qna.dto.QnaPaginationResponse;
import com.homeis.qna.model.service.QnaService;
import com.homeis.util.JWTUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/qna")
@RequiredArgsConstructor
public class QnaController {
	private final QnaService qnaService;
	private final JWTUtil jwtUtil;
	
	@GetMapping("/list/{userId}")
	public ResponseEntity<?> list(@PathVariable("userId") String userId, 
			@RequestHeader("Authorization") String tokenHeader,
			@RequestParam(value = "size", defaultValue = "10") int size,
			@RequestParam(value = "page", defaultValue = "1") int page) {
		//토큰이 유효하지 않은 경우
		boolean isValid = jwtUtil.isValid(tokenHeader.substring(7));
		
		if(!isValid) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("토큰 만료, 잘못된 접근.");
		
		String tokenJob = jwtUtil.getJobFromToken(tokenHeader.substring(7));
		
		QnaPaginationResponse response = new QnaPaginationResponse();
		
		if(tokenJob.equals("관리자")) response = qnaService.selectAdmin(size, page);
		else {
			String tokenId = jwtUtil.getIdFromToken(tokenHeader.substring(7));
			if(!userId.equals(tokenId)) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("잘못된 접근입니다.");
			
			response = qnaService.selectAll(userId, size, page);
		}
		
		
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/detail/{userId}/{id}")
	public ResponseEntity<?> question(@PathVariable("userId") String userId, @PathVariable("id") int id, @RequestHeader("Authorization") String tokenHeader) {
		//토큰이 유효하지 않은 경우
		boolean isValid = jwtUtil.isValid(tokenHeader.substring(7));
		if(!isValid) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("토큰 만료, 잘못된 접근.");
		
		String tokenJob = jwtUtil.getJobFromToken(tokenHeader.substring(7));
		
		Qna qna = new Qna();
		
		if(tokenJob.equals("관리자")) qna = qnaService.findById(id);
		else {
			String tokenId = jwtUtil.getIdFromToken(tokenHeader.substring(7));
			if(!userId.equals(tokenId)) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("잘못된 접근입니다.");
			
			qna = qnaService.findById(id);
		}
		
		if(qna == null) return ResponseEntity.status(404).body("없는 글입니다!");
		
		return ResponseEntity.ok(qna);
	}
	
	@PostMapping("/insert-question")
	public ResponseEntity<?> registQuestion(@RequestBody Qna qna) {
		int result = qnaService.insertQuestion(qna);
		
		if(result == 0) return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/insert-answer")
	public ResponseEntity<?> registAnswer(@RequestBody QnaComment qnaComment,
			@RequestHeader("Authorization") String tokenHeader) {
		
		String tokenJob = jwtUtil.getJobFromToken(tokenHeader.substring(7));
		if(!tokenJob.equals("관리자")) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("잘못된 접근입니다.");
		
		int result = qnaService.insertAnswer(qnaComment);
		
		if(result == 0) return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> update(@RequestBody Qna qna) {
		int result = qnaService.updateQna(qna);
		
		if(result == 0) return ResponseEntity.status(404).body("수정 불가!");
		
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<?> delete(@RequestBody Qna qna) {
		int result = qnaService.deleteQna(qna);
		
		if(result == 0) return ResponseEntity.status(404).body("삭제 불가!");
		
		return ResponseEntity.ok().build();
	}
}
