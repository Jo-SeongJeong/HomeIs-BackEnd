package com.homeis.board.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.homeis.board.dto.Board;
import com.homeis.board.dto.BoardPaginationResponse;
import com.homeis.board.dto.Comment;
import com.homeis.board.dto.Likes;
import com.homeis.board.model.mapper.BoardMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{
	private final BoardMapper boardMapper;
	
	
	@Override
	public BoardPaginationResponse selectAll(int size, int page, String category) {
		Map<String, Object> param = new HashMap<>();
		param.put("size", size);
		param.put("offset", (page-1)*size);
		param.put("category", category);
		
		/*게시판 목록 조회 응답 데이터 생성*/
		BoardPaginationResponse resp = new BoardPaginationResponse();
		//게시판 목록 정보 세팅
		List<Board> boardList = boardMapper.selectAll(param);
		
		// 생성 날짜 파싱
		for(Board board : boardList) {
			board.setCreateTime(board.getCreateTime().substring(0, 10));
		}
		
		resp.setBoardList(boardList);
		
		//페이지네이션 정보 세팅
		int totalRow = boardMapper.totalRow(param);
		int totalPages = ((totalRow-1)/size)+1; 
		resp.setTotalPages(totalPages);
		resp.setSize(size);
		resp.setPage(page);
		/*======================*/
		
		return resp;
	}

	@Override
	@Transactional
	public Board findById(int id) {
		int isSucceed = boardMapper.increaseView(id);
		
		if(isSucceed == 0) return null;
		
		Board board  = boardMapper.getBoard(id);
		
		if(board == null) return null;
		
		board.setCreateTime(board.getCreateTime().substring(0, 16));
		
		board.setCommentList(boardMapper.getComment(id));
		
		for(Comment comment : board.getCommentList()) {
			comment.setCreateTime(comment.getCreateTime().substring(0, 16));
		}
		
		return board;
	}

	@Override
	public int insertBoard(Board board) {
		return boardMapper.insertBoard(board);
	}

	@Override
	public int updateBoard(Board board) {
		return boardMapper.updateBoard(board);
	}

	@Override
	public int deleteBoard(Board board) {
		return boardMapper.deleteBoard(board);
	}
	
	@Override
	@Transactional
	public int insertLike(Likes like) {
		
		int isSucceed = boardMapper.insertLike(like);
		
		if(isSucceed == 0) return 0;
		
		return boardMapper.plusBoardLike(like.getBoardId());
	}
	

	@Override
	@Transactional
	public int deleteLike(Likes like) {
		int isSucceed = boardMapper.deleteLike(like);
		
		if(isSucceed == 0) return 0;
		
		return boardMapper.minusBoardLike(like.getBoardId());
	}

	@Override
	public int insertComment(Comment comment) {
		return boardMapper.insertComment(comment);
	}

	@Override
	public int updateComment(Comment comment) {
		return boardMapper.updateComment(comment);
	}

	@Override
	public int deleteComment(Comment comment) {
		return boardMapper.deleteComment(comment);
	}

}
