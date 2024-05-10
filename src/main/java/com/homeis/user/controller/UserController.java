package com.homeis.user.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.homeis.user.dto.DongCode;
import com.homeis.user.dto.User;
import com.homeis.user.model.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;
	
	@PostMapping("/login")
	public ResponseEntity<User> login(@ModelAttribute User loginInfo) {
		User userInfo = userService.login(loginInfo);
		if (userInfo == null) {
			return ResponseEntity.status(404).body(null);
		}
		return ResponseEntity.status(200).body(userInfo);
	}
	
	@PostMapping("/register")
	public ResponseEntity<Integer> register(@ModelAttribute User userInfo) {
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
