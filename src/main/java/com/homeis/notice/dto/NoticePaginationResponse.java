package com.homeis.notice.dto;

import java.util.List;

import lombok.Data;

@Data
public class NoticePaginationResponse {
    private List<Notice> qnaList;	// 게시글 목록 정보
    private int totalPages;		// 전체 페이지 수
    private int page;		// 현재 페이지 번호
    private int size;		// 페이지 크기

}