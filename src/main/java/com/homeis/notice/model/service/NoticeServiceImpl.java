package com.homeis.notice.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.homeis.notice.dto.Notice;
import com.homeis.notice.dto.NoticePaginationResponse;
import com.homeis.notice.model.mapper.NoticeMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {
	private final NoticeMapper noticeMapper;

	@Override
	public NoticePaginationResponse selectAll(int size, int page) {
		Map<String, Object> param = new HashMap<>();
		param.put("size", size);
		param.put("offset", (page-1)*size);
		
		NoticePaginationResponse resp = new NoticePaginationResponse();
		List<Notice> noticeList = noticeMapper.selectAll(param);
		resp.setQnaList(noticeList);
		
		//페이지네이션 정보 세팅
		int totalRow = noticeMapper.totalRow(param);
		int totalPages = ((totalRow-1)/size)+1; 
		resp.setTotalPages(totalPages);
		resp.setSize(size);
		resp.setPage(page);
		/*======================*/
				
		return resp;
	}
	
	@Override
	public Notice selectById(int id) {
		System.out.println("Ss");
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