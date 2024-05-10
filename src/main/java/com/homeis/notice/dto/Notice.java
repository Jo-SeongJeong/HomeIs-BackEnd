package com.homeis.notice.dto;

import lombok.Data;

@Data
public class Notice {
	private int id;
	private String userId;
	private String title;
	private String content;
	private String createTime;
	
}

