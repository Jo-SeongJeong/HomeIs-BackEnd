package com.homeis.homesta.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.homeis.homesta.dto.Homesta;
import com.homeis.homesta.dto.HomestaImage;
import com.homeis.homesta.dto.HomestaLike;
import com.homeis.homesta.dto.HomestaPaginationResponse;
import com.homeis.homesta.model.mapper.HomestaMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HomestaServiceImpl implements HomestaService {
	private final HomestaMapper homestaMapper;

	@Override
	public HomestaPaginationResponse slelectAll(int size, int page, String category, String sort) {
		Map<String, Object> param = new HashMap<>();
		param.put("size", size);
		param.put("page", page);
		param.put("category", category);
		param.put("sort", sort);
		
		HomestaPaginationResponse resp = new HomestaPaginationResponse();
		List<Homesta> homestaList = homestaMapper.selectAll(param);
		
		if(homestaList.isEmpty()) return null;
		
		for(Homesta homesta : homestaList) {
			homesta.setCreaeteTime(homesta.getCreaeteTime().substring(0, 10));
			homesta.setImage(homestaMapper.selectImage(homesta.getId()));
		}
		
		resp.setHomestaList(homestaList);
		
		//페이지네이션 정보 세팅
		int totalRow = homestaMapper.totalRow(param);
		int totalPages = ((totalRow-1)/size)+1; 
		resp.setTotalPages(totalPages);
		resp.setSize(size);
		resp.setPage(page);
		/*======================*/
		
		return resp;
	}

	@Override
	@Transactional
	public Homesta findById(int id) {
		int isSucceed = homestaMapper.increaseView(id);
		
		if(isSucceed == 0) return null;
		
		Homesta homesta = homestaMapper.findById(id);
		
		homesta.setImage(homestaMapper.selectImage(id));
		
		homesta.setCreaeteTime(homesta.getCreaeteTime().substring(0, 16));
		
		return homesta;
	}

	@Override
	@Transactional
	public int insertHomesta(Homesta homesta) {
		int isSucceed = homestaMapper.insertHomesta(homesta);
		
		if(isSucceed == 0) return 0;
		
		for(HomestaImage image : homesta.getImage()) {
			isSucceed =  homestaMapper.insertImage(image);
			
			if(isSucceed == 0) return 0;
		}
		
		return isSucceed;
	}

	@Override
	@Transactional
	public int updateHomesta(Homesta homesta) {
		int isSucceed = homestaMapper.updateHomesta(homesta);
		
		if(isSucceed == 0) return 0;
		
		isSucceed = homestaMapper.deleteImage(homesta.getId());
		
		if(isSucceed == 0) return 0;
		
		for(HomestaImage image : homesta.getImage()) {
			isSucceed = homestaMapper.insertImage(image);
			
			if(isSucceed == 0) return 0;
		}
		
		return isSucceed;
	}

	@Override
	@Transactional
	public int deleteHomesta(int id, String userId) {
		Map<String, Object> param = new HashMap<>();
		param.put("id", id);
		param.put("userId", userId);
		
		return homestaMapper.deleteHomesta(param);
	}

	@Override
	@Transactional
	public int increaseLike(int id, String userId) {
		HomestaLike like = new HomestaLike();
		like.setHomestaId(id);
		like.setUserId(userId);
		
		int isSucceed = homestaMapper.insertLike(like);
		
		if(isSucceed == 0) return 0;
		
		
		return homestaMapper.increaseLike(id);
	}

	@Override
	@Transactional
	public int decreaseLike(int id, String userId) {
		HomestaLike like = new HomestaLike();
		like.setHomestaId(id);
		like.setUserId(userId);
		
		int isSucceed = homestaMapper.deleteLike(like);
		
		if(isSucceed == 0) return 0;
		
		return homestaMapper.decreaseLike(id);
	}

}
