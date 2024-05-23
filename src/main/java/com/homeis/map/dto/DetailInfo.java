package com.homeis.map.dto;

import java.util.List;

import lombok.Data;

@Data
public class DetailInfo {
	private List<ApartDealInfo> aptDealInfoList;
	private List<Review> reviewList;
	private int isLike;
    private int totalPages;		// 전체 페이지 수
    private int page;		// 현재 페이지 번호
    private int size;		// 페이지 크기
}
