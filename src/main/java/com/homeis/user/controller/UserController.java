package com.homeis.user.controller;

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
	public User login(@ModelAttribute User loginInfo, HttpSession session) {
		User userInfo = userService.login(loginInfo);
		if (userInfo == null) {
			return null;
		}
		session.setAttribute("userInfo", userInfo);
		return userInfo;
	}
}
