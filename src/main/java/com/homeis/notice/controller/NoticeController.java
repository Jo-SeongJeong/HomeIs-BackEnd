package com.homeis.notice.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.homeis.notice.dto.Notice;
import com.homeis.notice.model.service.NoticeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notice")
public class NoticeController {
	private final NoticeService noticeService;

	@GetMapping("/")
	public ResponseEntity<?> list() {
		List<Notice> boards = noticeService.selectAll();
		return ResponseEntity.status(200).body(boards);
	}
	
	@GetMapping("/detail")
	public ResponseEntity<?> selectBy(@ModelAttribute Notice noticeInfo) {
		Notice notice = noticeService.selectById(noticeInfo.getId());
		return ResponseEntity.status(200).body(notice);
	}

	@PostMapping("/regist")
	public ResponseEntity<?> regist(@ModelAttribute Notice notice) {
		int isSucceed = noticeService.insertNotice(notice);
		return ResponseEntity.status(200).body(isSucceed);
	}

	@PutMapping("/update")
	public ResponseEntity<?> update(@ModelAttribute Notice notice) {
		int isSucceed = noticeService.updateNotice(notice);
		return ResponseEntity.status(200).body(isSucceed);
	}

	@DeleteMapping("/delete")
	public ResponseEntity<?> delete(@ModelAttribute Notice notice) {
		int isSucceed = noticeService.deleteNotice(notice);
		return ResponseEntity.status(200).body(isSucceed);
	}
}
