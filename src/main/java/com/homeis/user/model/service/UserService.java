package com.homeis.user.model.service;

import com.homeis.user.dto.User;

public interface UserService {
	User login(User loginInfo);
	int register(User userInfo);
	User idExist(String userId);
	int updateUserInfo(User user);
	int deleteUser(String userId);
}
