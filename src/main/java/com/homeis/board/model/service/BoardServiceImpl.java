package com.homeis.board.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.homeis.board.dto.Board;
import com.homeis.board.dto.Comment;
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
	public List<Comment> findById(int boardId) {
		return boardMapper.findById(boardId);
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

}
