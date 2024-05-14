package com.homeis.board.model.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.homeis.board.dto.Board;
import com.homeis.board.dto.Comment;
import com.homeis.board.dto.Likes;
import com.homeis.board.model.mapper.BoardMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{
	private final BoardMapper boardMapper;
	
	
	@Override
	public List<Board> selectAll() {
		return boardMapper.selectAll();
	}
	
	@Override
	public List<Likes> getBoardLike() {
		return boardMapper.getBoardLike();
	}
	
	@Override
	public Board getBoard(int id) {
		return boardMapper.getBoard(id);
	}
	
	@Override
	public int increaseView(Board board) {
		return boardMapper.increaseView(board);
	}
	
	@Override
	public List<Comment> getComment(int boardId) {
		return boardMapper.getComment(boardId);
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
		return boardMapper.insertLike(like);
	}

	@Override
	public int deleteLike(Likes like) {
		return boardMapper.deleteLike(like);
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
