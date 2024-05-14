package com.homeis.qna.model.service;

import java.util.List;

import com.homeis.qna.dto.Qna;
import com.homeis.qna.dto.QnaComment;

public interface QnaService {
	public List<Qna> selectAll(Qna qna);
	
	public Qna getQuestion(Qna qna);
	
	public List<QnaComment> getAnswer(Qna qna);
	
	public int insertQuestion(Qna qna);
	
	public int insertAnswer(QnaComment qnaComment);
	
	public int updateQna(Qna qna);
	
	public int deleteQna(Qna qna);

}
