package com.homeis.map.controller;

import com.homeis.map.dto.*;
import com.homeis.map.model.service.MapService;
import com.homeis.util.JWTUtil;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/map")
@RequiredArgsConstructor
public class MapController {

    private final MapService mapService;
    private final JWTUtil jwtUtil;

    @GetMapping("/apartDealInfo/{aptCode}")
    public ResponseEntity<?> getApartDealInfo(@PathVariable("aptCode") String aptCode, 
    		@RequestHeader(value = "Authorization", required = false) String tokenHeader,
    		@RequestParam(value = "size", defaultValue = "10") int size,
			@RequestParam(value = "page", defaultValue = "1") int page) {
    	String tokenId = null;
    	System.out.println("page= " +page);
    	
		if(tokenHeader != null && tokenHeader.startsWith("Bearer ")) {
			tokenId = jwtUtil.getIdFromToken(tokenHeader.substring(7));
		}
		
        DetailInfo detailInfo = mapService.getApartDealInfo(aptCode, tokenId, size, page);

        if (detailInfo == null) return ResponseEntity.status(404).body("NOT FOUND");

        return ResponseEntity.ok().body(detailInfo);
    }

    @GetMapping("/like")
    public ResponseEntity<?> getLike() {
        List<ApartDealInfo> aptInfo = mapService.selectLikeRank();

        if (aptInfo == null) return ResponseEntity.status(404).body("NOT FOUND");

        return ResponseEntity.ok().body(aptInfo);
    }

    @GetMapping("/view")
    public ResponseEntity<?> getView() {
        List<ApartDealInfo> aptInfo = mapService.selectViewRank();

        if (aptInfo == null) return ResponseEntity.status(404).body("NOT FOUND");

        return ResponseEntity.ok().body(aptInfo);
    }

    @PostMapping("/like")
    public ResponseEntity<?> inreaseLike(@RequestHeader("Authorization") String tokenHeader, @RequestBody HouseLike like) {
        String userId = jwtUtil.getIdFromToken(tokenHeader.substring(7));
        like.setUserId(userId);

        int result = mapService.insertLike(like);

        if (result == 0) return ResponseEntity.status(404).body("NOT FOUND");

        return ResponseEntity.ok(result);

    }

    @DeleteMapping("/like/{aptCode}")
    public ResponseEntity<?> decreaseLike(@RequestHeader("Authorization") String tokenHeader, @PathVariable("aptCode") String aptCode) {
        String userId = jwtUtil.getIdFromToken(tokenHeader.substring(7));
        int result = mapService.decreaseLike(aptCode, userId);

        if (result == 0) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("삭제 불가");

        return ResponseEntity.ok(result);
    }

    @PostMapping("/review")
    public ResponseEntity<?> insertReview(@RequestHeader("Authorization") String tokenHeader, @RequestBody Review review) {
        String userId = jwtUtil.getIdFromToken(tokenHeader.substring(7));
        review.setUserId(userId);

        int result = mapService.insertReview(review);

        if (result == 0) return ResponseEntity.status(404).body("NOT FOUND");

        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/review/{id}")
    public ResponseEntity<?> deleteReview(@RequestHeader("Authorization") String tokenHeader, @PathVariable("id") int id) {
        String userId = jwtUtil.getIdFromToken(tokenHeader.substring(7));

        int result = mapService.deleteReview(id, userId);

        if (result == 0) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("삭제 불가");

        return ResponseEntity.ok(result);
    }
    
    @GetMapping("/dongCodes/{lng1}/{lat1}/{lng2}/{lat2}")
    public ResponseEntity<?> getRangeDongCodes(
    		@PathVariable("lng1") String lng1,
    		@PathVariable("lat1") String lat1,
    		@PathVariable("lng2") String lng2,
    		@PathVariable("lat2") String lat2) {
        List<HouseInfo> dongCodeList = mapService.selectRangeDongCode(lng1, lat1, lng2, lat2);
        return ResponseEntity.ok().body(dongCodeList);
    }

    @GetMapping("/dongCodes/{inputDongName}")
    public ResponseEntity<?> getDongCodes(@PathVariable("inputDongName") String dongName) {
        List<DongCodeDTO> dongCodeList = mapService.selectDongCode(dongName);
        return ResponseEntity.ok().body(dongCodeList);
    }
    
    @GetMapping("/houseInfo/{dongCodeStr}")
    public ResponseEntity<?> getHouseInfo(@PathVariable("dongCodeStr") String dongCodeStr) {
        List<HouseInfo> houseInfoList = mapService.selectHouseInfo(dongCodeStr);
        return ResponseEntity.ok().body(houseInfoList);
    }
}