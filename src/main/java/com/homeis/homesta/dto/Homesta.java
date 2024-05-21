package com.homeis.homesta.dto;

import java.util.List;

import lombok.Data;

@Data
public class Homesta {
	private int id;
	private String title;
	private String content;
	private String createTime;
	private String userId;
	private int view;
	private int type;
	private int totalLike;
	
	private int isLike;
	
	private List<HomestaImage> image;
}
