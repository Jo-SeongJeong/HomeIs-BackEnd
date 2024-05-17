package com.homeis.qna.dto;

import java.util.List;

import lombok.Data;

@Data
public class Qna {
	private int id;
	private String userId;
	private String title;
	private String content;
	private String createTime;
	
	private List<QnaComment> qnaComment;
	
}
