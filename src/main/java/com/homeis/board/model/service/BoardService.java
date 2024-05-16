package com.homeis.board.model.service;

import java.util.List;

import com.homeis.board.dto.Board;
import com.homeis.board.dto.BoardPaginationResponse;
import com.homeis.board.dto.Comment;
import com.homeis.board.dto.Likes;

public interface BoardService {
	// 자유게시판 글 전체 조회
		public BoardPaginationResponse selectAll(int size, int page, String category);
		// 글 상세 조회
		public Board findById(int id);
		// 글 등록
		public int insertBoard(Board board);
		// 글 수정
		public int updateBoard(Board board);
		// 글 삭제
		public int deleteBoard(Board board);
		// 좋아요 등록
		public int insertLike(Likes like);
		// 좋아요 삭제
		public int deleteLike(Likes like);
		// 댓글 등록
		public int insertComment(Comment comment);
		// 댓글 수정
		public int updateComment(Comment comment);
		// 댓글 삭제
		public int deleteComment(Comment comment);
}
