package com.homeis.qna.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.homeis.qna.dto.Qna;
import com.homeis.qna.dto.QnaComment;

@Mapper
public interface QnaMapper {
	public List<Qna> selectAll(Qna qna);
	
	public Qna getQuestion(Qna qna);
	
	public List<QnaComment> getAnswer(Qna qna);
	
	public int insertQuestion(Qna qna);
	
	public int insertAnswer(QnaComment qnaComment);
	
	public int updateQna(Qna qna);
	
	public int deleteQna(Qna qna);
}
