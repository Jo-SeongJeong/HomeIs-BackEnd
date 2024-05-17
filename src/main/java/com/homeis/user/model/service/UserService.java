package com.homeis.user.model.service;

import java.util.List;

import com.homeis.user.dto.DongCode;
import com.homeis.user.dto.User;

public interface UserService {
	int idExist(String userId);
	int register(User userInfo);
	String login(User loginInfo);
	User getUserInfo(String id);
	int updateUserInfo(User userInfo);
	int deleteUser(String userId);
	List<DongCode> getInterestArea(String userId);
	int insertInterestArea(DongCode dongCode);
	int deleteInterestArea(int id, String userId);
}
