package com.homeis.board.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.homeis.board.dto.Board;
import com.homeis.board.dto.Comment;
import com.homeis.board.dto.Likes;
import com.homeis.board.dto.Views;

@Mapper
public interface BoardMapper {
	// 자유게시판 글 전체 조회
	public List<Board> selectAll(Map<String, Object> param);
	/**
	 * 조회한 목록에 대한 전체 
	 * @param param
	 * @return
	 */
	int totalRow(Map<String, Object> param);
	// 글 상세 조회
	public int increaseView(int id);
	public Board getBoard(int id);
	public List<Comment> getComment(int boardId);
	public int getView(Views view);
	public int insertView(Views view);
	public int getLike(Likes like);
	// 글 등록
	public int insertBoard(Board board);
	// 글 수정
	public int updateBoard(Board board);
	// 글 삭제
	public int deleteBoard(Board board);
	// 좋아요 등록
	public int insertLike(Likes like);
	public int plusBoardLike(int id);
	// 좋아요 삭제
	public int deleteLike(Likes like);
	public int minusBoardLike(int id);
	// 댓글 등록
	public int insertComment(Comment comment);
	// 댓글 수정
	public int updateComment(Comment comment);
	// 댓글 삭제
	public int deleteComment(Comment comment);
}
