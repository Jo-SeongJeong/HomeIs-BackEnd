package com.homeis.map.controller;

import com.homeis.map.dto.ApartDealInfo;
import com.homeis.map.dto.Review;
import com.homeis.map.model.service.MapService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/map")
@RequiredArgsConstructor
public class MapController {

    private final MapService mapService;

    @GetMapping("/apartDealInfo/{aptCode}")
    public ResponseEntity<?> getApartDealInfo(@PathVariable("aptCode") String aptCode) {
        List<ApartDealInfo> apartDealInfo = mapService.getApartDealInfo(aptCode);
        if (apartDealInfo == null) {
            return ResponseEntity.status(404).body("NOT FOUND");
        }
        return ResponseEntity.ok().body(apartDealInfo);
    }

    @GetMapping("/like/{aptCode}")
    public ResponseEntity<?> selectLike(@PathVariable("aptCode") String aptCode) {
        int likeCount = mapService.selectLike(aptCode);

        return ResponseEntity.ok().body(likeCount);
    }

    @GetMapping("/review/{aptCode}")
    public ResponseEntity<?> selectReviewAll(@PathVariable("aptCode") String aptCode) {
        List<Review> reviewList = mapService.selectReviewAll(aptCode);

        if (reviewList == null) {
            return ResponseEntity.status(404).body("NOT FOUND");
        }
        return ResponseEntity.ok().body(reviewList);
    }
}