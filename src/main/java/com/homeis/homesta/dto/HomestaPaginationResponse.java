package com.homeis.homesta.dto;

import java.util.List;

import lombok.Data;

@Data
public class HomestaPaginationResponse {
    private List<Homesta> homestaList;	// 게시글 목록 정보
    private int totalPages;		// 전체 페이지 수
    private int page;		// 현재 페이지 번호
    private int size;		// 페이지 크기

}