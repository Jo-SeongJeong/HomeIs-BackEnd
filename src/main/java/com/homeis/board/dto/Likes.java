package com.homeis.board.dto;

import lombok.Data;

@Data
public class Likes {
	private int boardId;
	private String userId;
	private int number;
}
