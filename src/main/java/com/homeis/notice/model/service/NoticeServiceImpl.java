package com.homeis.notice.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.homeis.notice.dto.Notice;
import com.homeis.notice.model.mapper.NoticeMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {
	private final NoticeMapper noticeMapper;

	@Override
	public List<Notice> selectAll() {
		return noticeMapper.selectAll();
	}
	
	@Override
	public Notice selectById(int id) {
		return noticeMapper.selectById(id);
	}

	@Override
	public int insertNotice(Notice notice) {
		return noticeMapper.insertNotice(notice);
	}

	@Override
	public int updateNotice(Notice notice) {
		return noticeMapper.updateNotice(notice);
	}

	@Override
	public int deleteNotice(Notice notice) {
		return noticeMapper.deleteNotice(notice);
	}


}
