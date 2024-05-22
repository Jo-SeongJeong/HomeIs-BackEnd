package com.homeis.map.dto;

import java.util.List;

import lombok.Data;

@Data
public class DetailInfo {
	private List<ApartDealInfo> aptDealInfoList;
	private List<Review> reviewList;
	private int isLike;
}
