package com.homeis.user.model.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.homeis.user.dto.User;

@Mapper
public interface UserMapper {
	
	User login(User loginInfo);
	int register(User userInfo);
	User idExist(String userId);
}
