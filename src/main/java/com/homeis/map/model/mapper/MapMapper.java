package com.homeis.map.model.mapper;

import com.homeis.map.dto.ApartDealInfo;
import com.homeis.map.dto.Review;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MapMapper {

    public List<ApartDealInfo> getApartDealInfo(String aptCode);
    public int selectLike(String aptCode);
    public List<Review> selectReviewAll(String aptCode);
}
