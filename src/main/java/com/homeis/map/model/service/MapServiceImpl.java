package com.homeis.map.model.service;

import com.homeis.map.dto.*;
import com.homeis.map.model.mapper.MapMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MapServiceImpl implements MapService {

    private final MapMapper mapMapper;

    @Override
    @Transactional
    public DetailInfo getApartDealInfo(String aptCode, String userId, int size, int page) {
    	
    	
    	HouseView view = new HouseView();
		view.setAptCode(aptCode);
		view.setUserId(userId);
		
		int isView = mapMapper.getView(view);
		System.out.println("조회한지? " + isView);
		
		if(isView == 0) {
			int isSucceed = mapMapper.insertView(view);
			if(isSucceed == 0) return null;
			
			isSucceed =  mapMapper.increaseView(aptCode);
			if(isSucceed == 0) return null;
		}
    
		Map<String, Object> param = new HashMap<>();
		param.put("size", size);
		param.put("offset", (page-1)*size);
		param.put("aptCode",aptCode);
		param.put("userId", userId);

		List<ApartDealInfo> aptDealInfoList = mapMapper.getApartDealInfo(param);
    	List<Review> reviewList = mapMapper.selectReviewAll(aptCode);
    	
    	HouseLike like = new HouseLike();
    	like.setAptCode(aptCode);
    	like.setUserId(userId);
    	
    	DetailInfo detailInfo = new DetailInfo();
    	detailInfo.setAptDealInfoList(aptDealInfoList);
    	detailInfo.setReviewList(reviewList);
    	detailInfo.setIsLike(mapMapper.getLike(like));
    	

    	//페이지네이션 정보 세팅
		int totalRow = mapMapper.totalRow(param);
		int totalPages = ((totalRow-1)/size)+1; 
		detailInfo.setTotalPages(totalPages);
		detailInfo.setSize(size);
		detailInfo.setPage(page);
		/*======================*/
    	
        return detailInfo;
    }

    @Override
    @Transactional
    public int insertLike(HouseLike like) {
    	int isSucceed = mapMapper.insertLike(like);
    	
    	if(isSucceed == 0) return 0;
    	
    	return mapMapper.increaseLike(like.getAptCode());
    }

    @Override
    @Transactional
	public int decreaseLike(String aptCode, String userId) {
		Map<String, String> param = new HashMap<>();
    	param.put("aptCode", aptCode);
    	param.put("userId", userId);
    	
    	int isSucceed = mapMapper.deleteLike(param);
    	
    	if(isSucceed == 0) return 0;
    	
    	
		return mapMapper.decreaseLike(aptCode);
	}

	@Override
	public int insertReview(Review review) {
		
		return mapMapper.insertReview(review);
	}

	@Override
	public int deleteReview(int id, String userId) {
		Map<String, Object> param = new HashMap<>();
		param.put("id", id);
		param.put("userId", userId);
		
		return mapMapper.deleteReview(param);
	}

	@Override
	public List<ApartDealInfo> selectViewRank() {
		return mapMapper.selectViewRank();
	}

	@Override
	public List<ApartDealInfo> selectLikeRank() {
		return mapMapper.selectLikeRank();
	}

	@Override
	public List<DongCodeDTO> selectDongCode(String inputDongName) {
		return mapMapper.selectDongCode(inputDongName);
	}

	@Override
	public List<HouseInfo> selectHouseInfo(String dongCodeStr) {
		return mapMapper.selectHouseInfo(dongCodeStr);
	}

	@Override
	public List<HouseInfo> selectRangeDongCode(String lng1, String lat1, String lng2, String lat2) {
		Map<String, String> param = new HashMap<>();
		param.put("lng1", lng1);
		param.put("lat1", lat1);
		param.put("lng2", lng2);
		param.put("lat2", lat2);
		return mapMapper.selectRangeDongCode(param);
	}

}
