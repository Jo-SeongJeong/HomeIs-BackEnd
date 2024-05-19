package com.homeis.map.controller;

import com.homeis.map.dto.DetailInfo;
import com.homeis.map.dto.HouseLike;
import com.homeis.map.dto.Review;
import com.homeis.map.model.service.MapService;
import com.homeis.util.JWTUtil;

import lombok.RequiredArgsConstructor;

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

@Controller
@RequestMapping("/map")
@RequiredArgsConstructor
public class MapController {

    private final MapService mapService;
    private final JWTUtil jwtUtil;

    @GetMapping("/apartDealInfo/{aptCode}")
    public ResponseEntity<?> getApartDealInfo(@PathVariable("aptCode") String aptCode) {
        DetailInfo detailInfo = mapService.getApartDealInfo(aptCode);
        
        if (detailInfo == null) return ResponseEntity.status(404).body("NOT FOUND");
        
        return ResponseEntity.ok().body(detailInfo);
    }
    
    @PostMapping("/like")
    public ResponseEntity<?> inreaseLike(@RequestHeader("Authorization") String tokenHeader , @RequestBody HouseLike like) {
    	String userId = jwtUtil.getIdFromToken(tokenHeader.substring(7));
    	like.setUserId(userId);
    	
    	int result = mapService.insertLike(like);
    	
    	if(result == 0) return ResponseEntity.status(404).body("NOT FOUND");
    	
    	return ResponseEntity.ok(result);
    	
    }
    
    @DeleteMapping("/like/{aptCode}")
    public ResponseEntity<?> decreaseLike(@RequestHeader("Authorization") String tokenHeader, @PathVariable("aptCode") String aptCode) {
    	String userId = jwtUtil.getIdFromToken(tokenHeader.substring(7));
    	int result = mapService.decreaseLike(aptCode, userId);
    	
    	if(result == 0) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("삭제 불가");
    	
    	return ResponseEntity.ok(result);
    }
    
    @PostMapping("/review")
    public ResponseEntity<?> insertReview(@RequestHeader("Authorization") String tokenHeader, @RequestBody Review review) {
    	String userId = jwtUtil.getIdFromToken(tokenHeader.substring(7));
    	review.setUserId(userId);
    	
    	int result = mapService.insertReview(review);
    	
    	if(result == 0) return ResponseEntity.status(404).body("NOT FOUND");
    	
    	return ResponseEntity.ok(result);
    }
    
    @DeleteMapping("/review/{id}")
    public ResponseEntity<?> deleteReview(@RequestHeader("Authorization") String tokenHeader, @PathVariable("id") int id) {
    	String userId = jwtUtil.getIdFromToken(tokenHeader.substring(7));
    	
    	int result = mapService.deleteReview(id, userId);
    	
    	if(result == 0) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("삭제 불가");
    	
    	return ResponseEntity.ok(result);
    }
}