package com.homeis.map.model.service;

import com.homeis.map.dto.ApartDealInfo;
import com.homeis.map.dto.Review;

import java.util.List;

public interface MapService {
    public List<ApartDealInfo> getApartDealInfo(String aptCode);
    public int selectLike(String aptCode);
    public List<Review> selectReviewAll(String aptCode);

}
