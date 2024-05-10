package com.homeis.user.model.service;

import java.util.List;

import com.homeis.user.dto.DongCode;
import com.homeis.user.dto.User;

public interface UserService {
	User login(User loginInfo);
	int register(User userInfo);
	User idExist(String userId);
	int updateUserInfo(User user);
	int deleteUser(String userId);
	List<DongCode> getInterestArea(String userId);
}
