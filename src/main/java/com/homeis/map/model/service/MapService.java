package com.homeis.map.model.service;

import java.util.List;

import com.homeis.map.dto.*;

public interface MapService {
    public DetailInfo getApartDealInfo(String aptCode, String userId, int size, int page);
    public int insertLike(HouseLike like);
    public int decreaseLike(String aptCode, String userId);
    public int insertReview(Review review);
    public int deleteReview(int id, String userId);    
    public List<ApartDealInfo> selectViewRank();
    public List<ApartDealInfo> selectLikeRank();
    public List<DongCodeDTO> selectDongCode(String inputDongName);
    public List<HouseInfo> selectRangeDongCode(String lng1, String lat1, String lng2, String lat2);
    public List<HouseInfo> selectHouseInfo(String dongCodeStr);
}
