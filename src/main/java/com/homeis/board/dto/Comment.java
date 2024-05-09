package com.homeis.board.dto;

import lombok.Data;

@Data
public class Comment {
	private int id;
	private String userId;
	private String comment;
	private String createTime;
	private int boardId;
	private int good;
	
}

