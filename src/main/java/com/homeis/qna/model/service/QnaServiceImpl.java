package com.homeis.qna.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.homeis.qna.dto.Qna;
import com.homeis.qna.dto.QnaComment;
import com.homeis.qna.dto.QnaPaginationResponse;
import com.homeis.qna.model.mapper.QnaMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QnaServiceImpl implements QnaService {
	private final QnaMapper qnaMapper;
	
	@Override
	public QnaPaginationResponse selectAll(String userId, int size, int page) {
		Map<String, Object> param = new HashMap<>();
		param.put("size", size);
		param.put("offset", (page-1)*size);
		param.put("userId", userId);
		
		QnaPaginationResponse resp = new QnaPaginationResponse();
		
		List<Qna> qnaList = qnaMapper.selectAll(param);
		
		// 생성 시간 파싱
		for(Qna qna : qnaList) {
			qna.setCreateTime(qna.getCreateTime().substring(0, 10));
		}
		
		resp.setQnaList(qnaList);
		
		//페이지네이션 정보 세팅
		int totalRow = qnaMapper.totalRow(param);
		int totalPages = ((totalRow-1)/size)+1; 
		resp.setTotalPages(totalPages);
		resp.setSize(size);
		resp.setPage(page);
		/*======================*/
				
		return resp;
	}
	
	@Override
	public QnaPaginationResponse selectAdmin(int size, int page) {
		Map<String, Object> param = new HashMap<>();
		param.put("size", size);
		param.put("offset", (page-1)*size);
		
		QnaPaginationResponse resp = new QnaPaginationResponse();
		
		List<Qna> qnaList = qnaMapper.selectAdmin(param);
		
		// 생성 시간 파싱
		for(Qna qna : qnaList) {
			qna.setCreateTime(qna.getCreateTime().substring(0, 10));
		}
		
		resp.setQnaList(qnaList);
		
		//페이지네이션 정보 세팅
		int totalRow = qnaMapper.totalRow(param);
		int totalPages = ((totalRow-1)/size)+1; 
		resp.setTotalPages(totalPages);
		resp.setSize(size);
		resp.setPage(page);
		/*======================*/
				
		return resp;
	}
	

	@Override
	public Qna findById(int id) {
		
		Qna qna = qnaMapper.getQuestion(id);		
		if(qna == null) return null;
		
		qna.setCreateTime(qna.getCreateTime().substring(0, 10));
		
		qna.setQnaComment(qnaMapper.getAnswer(id));
		
		for(QnaComment comment : qna.getQnaComment()) {
			comment.setCreateTime(comment.getCreateTime().substring(0, 10));
		}
		
		return qna;
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
		List<QnaComment> qnaComment = qnaMapper.getAnswer(qna.getId());
		
		if(!qnaComment.isEmpty()) return 0;
		
		return qnaMapper.updateQna(qna);
	}

	@Override
	public int deleteQna(Qna qna) {
		return qnaMapper.deleteQna(qna);
	}

}
