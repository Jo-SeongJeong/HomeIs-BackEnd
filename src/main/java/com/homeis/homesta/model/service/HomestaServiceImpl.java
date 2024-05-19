package com.homeis.homesta.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
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
	public HomestaPaginationResponse selectAll(int size, int page, String category, String sort) {
		Map<String, Object> param = new HashMap<>();
		param.put("size", size);
		param.put("offset", (page-1)*size);
		param.put("category", category);
		param.put("sort", sort);
		
		HomestaPaginationResponse resp = new HomestaPaginationResponse();
		List<Homesta> homestaList = homestaMapper.selectAll(param);
		
		if(homestaList.isEmpty()) return null;
		
		for(Homesta homesta : homestaList) {
			homesta.setCreateTime(homesta.getCreateTime().substring(0, 10));
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
	public Homesta findById(int id, String userId) {
		int isSucceed = homestaMapper.increaseView(id);
		
		if(isSucceed == 0) return null;
		
		Homesta homesta = homestaMapper.findById(id);
		
		HomestaLike like = new HomestaLike();
		like.setHomestaId(id);
		like.setUserId(userId);

		homesta.setIsLike(homestaMapper.getLike(like));
		
		homesta.setImage(homestaMapper.selectImage(id));
		
		homesta.setCreateTime(homesta.getCreateTime().substring(0, 16));
		
		return homesta;
	}

	@Override
	@Transactional(isolation = Isolation.SERIALIZABLE) // 동시성 오류 해결 위해 serializable 적용
	public int insertHomesta(Homesta homesta) {
		if(homesta.getImage() == null) return 0;
		
		int isSucceed = homestaMapper.insertHomesta(homesta);
		
		if(isSucceed == 0) return 0;
		
		int id = homestaMapper.selectLast();
		
		for(HomestaImage image : homesta.getImage()) {
			image.setHomestaId(id);
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
	public int increaseLike(HomestaLike like) {
		
		int isSucceed = homestaMapper.insertLike(like);
		
		if(isSucceed == 0) return 0;
		
		
		return homestaMapper.increaseLike(like.getHomestaId());
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
