package com.homeis.user.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.homeis.user.dto.DongCode;
import com.homeis.user.dto.User;

@Mapper
public interface UserMapper {
	
	User login(User loginInfo);
	int register(User userInfo);
	User idExist(String userId);
	int updateUserInfo(User user);
	int deleteUser(String userId);
	List<DongCode> getInterestArea(String userId);
	
//	List<DongCode> list = sqlSession.selectList("com.homeis.user.model.mapper.UserMapper.getInterestedArea",userId)
}
