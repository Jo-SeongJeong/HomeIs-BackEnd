package com.homeis.map.model.mapper;

import com.homeis.map.dto.ApartDealInfo;
import com.homeis.map.dto.DongCodeDTO;
import com.homeis.map.dto.HouseLike;
import com.homeis.map.dto.Review;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface MapMapper {

	public int increaseView(String aptCode);
    public List<ApartDealInfo> getApartDealInfo(String aptCode);
    public List<Review> selectReviewAll(String aptCode);
    
    public int insertLike(HouseLike like);
    public int increaseLike(String aptCode);
    public int deleteLike(Map<String, String> param);
    public int decreaseLike(String aptCode);
    
    public int insertReview(Review review);
    public int deleteReview(Map<String, Object> param);
    
    public List<ApartDealInfo> selectViewRank();
    public List<ApartDealInfo> selectLikeRank();

    public List<DongCodeDTO> selectDongCode(String inputDongName);
}
