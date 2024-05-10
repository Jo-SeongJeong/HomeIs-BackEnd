package com.homeis.user.model.service;

import com.homeis.user.dto.User;

public interface UserService {
	User login(User loginInfo);
	int register(User userInfo);
}
