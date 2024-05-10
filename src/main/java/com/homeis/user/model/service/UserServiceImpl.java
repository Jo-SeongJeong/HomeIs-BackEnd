package com.homeis.user.model.service;

import org.springframework.stereotype.Service;

import com.homeis.user.dto.User;
import com.homeis.user.model.mapper.UserMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserMapper userMapper;
	
	@Override
	public User login(User loginInfo) {
		return userMapper.login(loginInfo);
	}

	@Override
	public int register(User userInfo) {
		return userMapper.register(userInfo);
	}

	@Override
	public User idExist(String userId) {
		return userMapper.idExist(userId);
	}
}
