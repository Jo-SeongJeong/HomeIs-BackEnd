package com.homeis.qna.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.homeis.qna.dto.Qna;
import com.homeis.qna.dto.QnaComment;
import com.homeis.qna.model.mapper.QnaMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QnaServiceImpl implements QnaService {
	private final QnaMapper qnaMapper;
	
	@Override
	public List<Qna> selectAll(Qna qna) {
		return qnaMapper.selectAll(qna);
	}

	@Override
	public Qna getQuestion(Qna qna) {
		return qnaMapper.getQuestion(qna);
	}

	@Override
	public List<QnaComment> getAnswer(Qna qna) {
		return qnaMapper.getAnswer(qna);
	}

	@Override
	public int insertQuestion(Qna qna) {
		return qnaMapper.insertQuestion(qna);
	}

	@Override
	public int insertAnswer(QnaComment qnaComment) {
		return qnaMapper.insertAnswer(qnaComment);
	}

	@Override
	public int updateQna(Qna qna) {
		return qnaMapper.updateQna(qna);
	}

	@Override
	public int deleteQna(Qna qna) {
		return qnaMapper.deleteQna(qna);
	}

}
