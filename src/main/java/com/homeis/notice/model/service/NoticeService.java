package com.homeis.notice.model.service;

import com.homeis.notice.dto.Notice;
import com.homeis.notice.dto.NoticePaginationResponse;

public interface NoticeService {
	// 공지사항 글 전체 조회
	public NoticePaginationResponse selectAll(int size, int page);
	// 공지사항 상세 조회
	public Notice selectById(int id);
	// 공지사항 등록
	public int insertNotice(Notice notice);
	// 공지사항 수정
	public int updateNotice(Notice notice);
	// 공지사항 삭제
	public int deleteNotice(Notice notice);
}
