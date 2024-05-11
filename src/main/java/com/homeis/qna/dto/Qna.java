package com.homeis.qna.dto;

import lombok.Data;

@Data
public class Qna {
	private int id;
	private String userId;
	private String title;
	private String content;
	private String createTime;
	
}
