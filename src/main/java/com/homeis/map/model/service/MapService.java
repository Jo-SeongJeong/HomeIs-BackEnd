package com.homeis.map.model.service;

import java.util.List;

import com.homeis.map.dto.ApartDealInfo;
import com.homeis.map.dto.DetailInfo;
import com.homeis.map.dto.HouseLike;
import com.homeis.map.dto.Review;

public interface MapService {
    public DetailInfo getApartDealInfo(String aptCode);
    public int insertLike(HouseLike like);
    public int decreaseLike(String aptCode, String userId);
    public int insertReview(Review review);
    public int deleteReview(int id, String userId);    
    public List<ApartDealInfo> selectViewRank();
    public List<ApartDealInfo> selectLikeRank();
}
