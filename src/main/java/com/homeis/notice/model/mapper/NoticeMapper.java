package com.homeis.notice.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.homeis.notice.dto.Notice;

@Mapper
public interface NoticeMapper {
	// 공지사항 글 전체 조회
	public List<Notice> selectAll(Map<String, Object> param);
	/**
	 * 조회한 목록에 대한 전체 
	 * @param param
	 * @return
	 */
	int totalRow(Map<String, Object> param);
	// 공지사항 상세 조회
	public Notice selectById(int id);
	// 공지사항 등록
	public int insertNotice(Notice notice);
	// 공지사항 수정
	public int updateNotice(Notice notice);
	// 공지사항 삭제
	public int deleteNotice(Notice notice);
}
