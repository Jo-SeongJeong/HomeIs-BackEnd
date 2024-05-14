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
	
	@GetMapping("/mypage/{userId}")
	public ResponseEntity<?> getUserInfo(@PathVariable("userId") String userId, @RequestHeader("Authorization") String tokenHeader) {
//		String tokenHeader = request.getHeader("Authorization");	//Header에서 토큰 정보 추출
		//토큰 헤더가 없거나 Bearer로 시작하지 않는 경우
//		if(tokenHeader == null || !tokenHeader.startsWith("Bearer ")) {
//			response.setStatus(401);
//			response.getWriter().write("Unauthorized");
//			return false;
//		}
		//토큰이 유효하지 않은 경우
		boolean isValid = JWTUtil.isValid(tokenHeader.substring(7));
		if(!isValid) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("잘못된 접근입니다.");
		
		String memberId = JWTUtil.getIdFromToken(tokenHeader.substring(7));
		if(!memberId.equals(userId)) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("잘못된 접근입니다.");
		
		User user = userService.getUserInfo(userId);
		
		return ResponseEntity.ok(user);
	}
	
	@PutMapping
	public ResponseEntity<Integer> updateUserInfo(@ModelAttribute User userInfo) {
		int flag = userService.updateUserInfo(userInfo);
		if (flag == 0) {
			return ResponseEntity.status(404).body(0);
		}
		return ResponseEntity.status(200).body(1);
	}
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<Integer> deleteUser(@PathVariable("userId") String userId) {
		int flag = userService.deleteUser(userId);
		if (flag == 0) {
			return ResponseEntity.status(404).body(0);
		}
		return ResponseEntity.status(200).body(1);
	}
	
	@GetMapping("/interest-area/{userId}")
	public ResponseEntity<List<DongCode>> getInterestArea(@PathVariable("userId") String userId) {
		List<DongCode> interestAreaList = userService.getInterestArea(userId);
		if (interestAreaList.isEmpty()) {
			return ResponseEntity.status(404).body(null);
		}
		return ResponseEntity.status(200).body(interestAreaList);
	}
}
