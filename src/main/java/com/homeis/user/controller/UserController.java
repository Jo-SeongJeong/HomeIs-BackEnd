package com.homeis.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.homeis.user.dto.User;
import com.homeis.user.model.service.UserService;

import jakarta.servlet.http.HttpSession;
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
			return ResponseEntity.status(404).build();
		}
		return ResponseEntity.status(200).body(userInfo);
	}
	
	@PostMapping("/register")
	public ResponseEntity<Integer> register(@ModelAttribute User userInfo) {
		int flag = userService.register(userInfo);
		if (flag != 1) {
			return ResponseEntity.status(404).build();
		}
		return ResponseEntity.status(200).body(0);
	}
}
