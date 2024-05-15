package com.homeis.board.dto;

import java.util.List;

import lombok.Data;

@Data
public class Board {
	private int id;
	private String userId;
	private String title;
	private String content;
	private String createTime;
	private int view;
	private int totalLike;
	
	private List<Comment> commentList;
	
}

