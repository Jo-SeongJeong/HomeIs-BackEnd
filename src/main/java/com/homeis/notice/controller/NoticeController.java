package com.homeis.notice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.homeis.notice.dto.Notice;
import com.homeis.notice.dto.NoticePaginationResponse;
import com.homeis.notice.model.service.NoticeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notice")
public class NoticeController {
	private final NoticeService noticeService;

	@GetMapping("/list")
	public ResponseEntity<?> list(@RequestParam(value = "size", defaultValue = "10") int size,
			@RequestParam(value = "page", defaultValue = "1") int page) {
		
		NoticePaginationResponse response = noticeService.selectAll(size, page);
		return ResponseEntity.status(200).body(response);
	}
	
	@GetMapping("/detail/{id}")
	public ResponseEntity<?> selectBy(@PathVariable("id") int id) {
		Notice notice = noticeService.selectById(id);
		return ResponseEntity.status(200).body(notice);
	}

	@PostMapping("/regist")
	public ResponseEntity<?> regist(@RequestBody Notice notice) {
		int isSucceed = noticeService.insertNotice(notice);
		return ResponseEntity.status(200).body(isSucceed);
	}

	@PutMapping("/update")
	public ResponseEntity<?> update(@RequestBody Notice notice) {
		int isSucceed = noticeService.updateNotice(notice);
		return ResponseEntity.status(200).body(isSucceed);
	}

	@DeleteMapping("/delete")
	public ResponseEntity<?> delete(@RequestBody Notice notice) {
		int isSucceed = noticeService.deleteNotice(notice);
		return ResponseEntity.status(200).body(isSucceed);
	}
}
