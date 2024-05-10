package com.homeis.board.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.homeis.board.dto.Board;
import com.homeis.board.dto.Comment;

@Mapper
public interface BoardMapper {
	// 자유게시판 글 전체 조회
	public List<Board> selectAll();
	// 자유게시판 글 상세 조회(댓글 조회)
	public List<Comment> findById(int boardId);
	// 글 조회수 증가
	public int increaseView(int id);
	// 글 등록
	public int insertBoard(Board board);
	// 글 수정
	public int updateBoard(Board board);
	// 글 삭제
	public int deleteBoard(Board board);
}
