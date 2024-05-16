package com.homeis.user.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.homeis.user.dto.DongCode;
import com.homeis.user.dto.User;
import com.homeis.user.model.service.UserService;
import com.homeis.util.JWTUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;
	private final JWTUtil jwtUtil;
	
	//로그인
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody User loginInfo){
		String token = userService.login(loginInfo);
		System.out.println(token);
		
		if(token == null ) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("아이디 또는 비밀번호가 잘못되었습니다.");
		
		return ResponseEntity.ok(token);
	}
	
	@PostMapping("/register")
	public ResponseEntity<Integer> register(@RequestBody User userInfo) {
		
		int flag = userService.register(userInfo);
		if (flag != 1) {
			return ResponseEntity.status(404).body(0);
		}
		return ResponseEntity.status(200).body(1);
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<Integer> idExist(@PathVariable("userId") String userId) {
		User user = userService.idExist(userId);
		if (user == null) {
			return ResponseEntity.status(404).body(0);
		}
		return ResponseEntity.status(200).body(1);
	}
	
	@GetMapping("/mypage/{id}")
	public ResponseEntity<?> getUserInfo(@PathVariable("id") String id, @RequestHeader("Authorization") String tokenHeader) {
		//토큰이 유효하지 않은 경우
		boolean isValid = jwtUtil.isValid(tokenHeader.substring(7));
		if(!isValid) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("토큰 만료, 잘못된 접근.");
		
		String userId = jwtUtil.getIdFromToken(tokenHeader.substring(7));
		if(!userId.equals(id)) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("잘못된 접근입니다.");
		
		User user = userService.getUserInfo(id);
		
		return ResponseEntity.ok(user);
	}
	
	@PutMapping
	public ResponseEntity<?> updateUserInfo(@RequestBody User userInfo, @RequestHeader("Authorization") String tokenHeader) {
		//토큰이 유효하지 않은 경우
		boolean isValid = jwtUtil.isValid(tokenHeader.substring(7));
		if(!isValid) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("토큰 만료, 잘못된 접근.");
		
		String userId = jwtUtil.getIdFromToken(tokenHeader.substring(7));
		if(!userId.equals(userInfo.getId())) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("잘못된 접근입니다.");
		
		int flag = userService.updateUserInfo(userInfo);
		if (flag == 0) {
			return ResponseEntity.status(404).body(0);
		}
		return ResponseEntity.status(200).body(1);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable("id") String id, @RequestHeader("Authorization") String tokenHeader) {
		
		//토큰이 유효하지 않은 경우
		boolean isValid = jwtUtil.isValid(tokenHeader.substring(7));
		if(!isValid) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("토큰 만료, 잘못된 접근.");
		
		String userId = jwtUtil.getIdFromToken(tokenHeader.substring(7));
		if(!userId.equals(id)) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("잘못된 접근입니다.");
		
		int flag = userService.deleteUser(userId);
		if (flag == 0) {
			return ResponseEntity.status(404).body(0);
		}
		return ResponseEntity.status(200).body(1);
	}
	
	@GetMapping("/interest-area/{id}")
	public ResponseEntity<?> getInterestArea(@PathVariable("id") String id,  @RequestHeader("Authorization") String tokenHeader) {
		//토큰이 유효하지 않은 경우
		boolean isValid = jwtUtil.isValid(tokenHeader.substring(7));
		if(!isValid) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("토큰 만료, 잘못된 접근.");
		
		String userId = jwtUtil.getIdFromToken(tokenHeader.substring(7));
		if(!userId.equals(id)) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("잘못된 접근입니다.");
		
		
		List<DongCode> interestAreaList = userService.getInterestArea(userId);
		if (interestAreaList.isEmpty()) {
			return ResponseEntity.status(404).body(null);
		}
		return ResponseEntity.status(200).body(interestAreaList);
	}
}
