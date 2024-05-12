package com.homeis.qna.dto;

import lombok.Data;

@Data
public class QnaComment {
	private int id;
	private String userId;
	private int qnaId;
	private String comment;
	private String createTime;

}
