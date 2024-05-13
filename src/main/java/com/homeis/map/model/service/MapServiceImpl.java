package com.homeis.map.model.service;

import com.homeis.map.dto.ApartDealInfo;
import com.homeis.map.dto.Review;
import com.homeis.map.model.mapper.MapMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MapServiceImpl implements MapService {

    private final MapMapper mapMapper;

    @Override
    public List<ApartDealInfo> getApartDealInfo(String aptCode) {
        return mapMapper.getApartDealInfo(aptCode);
    }

    @Override
    public int selectLike(String aptCode) {
        return mapMapper.selectLike(aptCode);
    }

    @Override
    public List<Review> selectReviewAll(String aptCode) {
        return mapMapper.selectReviewAll(aptCode);
    }
}
