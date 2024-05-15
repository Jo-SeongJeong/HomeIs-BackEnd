package com.homeis.qna.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.homeis.qna.dto.Qna;
import com.homeis.qna.dto.QnaComment;

@Mapper
public interface QnaMapper {
	public List<Qna> selectAll(Map<String, Object> param);
	/**
	 * 조회한 목록에 대한 전체 
	 * @param param
	 * @return
	 */
	int totalRow(Map<String, Object> param);
	
	public Qna getQuestion(int id);
	
	public List<QnaComment> getAnswer(int id);
	
	public int insertQuestion(Qna qna);
	
	public int insertAnswer(QnaComment qnaComment);
	
	public int updateQna(Qna qna);
	
	public int deleteQna(Qna qna);
}
