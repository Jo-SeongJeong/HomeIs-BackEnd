package com.homeis.qna.model.service;

import com.homeis.qna.dto.Qna;
import com.homeis.qna.dto.QnaComment;
import com.homeis.qna.dto.QnaPaginationResponse;

public interface QnaService {
	public QnaPaginationResponse selectAll(String userId, int size, int page);
	
	public Qna findById(int id);
	
	public int insertQuestion(Qna qna);
	
	public int insertAnswer(QnaComment qnaComment);
	
	public int updateQna(Qna qna);
	
	public int deleteQna(Qna qna);

}
