package com.homeis.user.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.homeis.user.dto.DongCode;
import com.homeis.user.dto.User;

@Mapper
public interface UserMapper {
	
	User login(String id);
	int idExist(String userId);
	int register(User userInfo);
	DongCode findByName(Map<String, String> location);
	int insertDongCode(DongCode dongCode);
	User getUserInfo(String id);
	int updateUserInfo(User user);
	int deleteUser(String userId);
	List<DongCode> getInterestArea(String userId);
	
//	List<DongCode> list = sqlSession.selectList("com.homeis.user.model.mapper.UserMapper.getInterestedArea",userId)
}
