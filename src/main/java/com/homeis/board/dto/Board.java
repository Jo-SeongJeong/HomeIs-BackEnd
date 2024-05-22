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
	private int totalView;
	private int totalLike;
	
	private int isLike;
	
	private List<Comment> commentList;
	
}

