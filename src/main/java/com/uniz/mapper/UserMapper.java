package com.uniz.mapper;

import org.apache.ibatis.annotations.Param;

import com.uniz.domain.UserDTO;

public interface UserMapper {
	
	public UserDTO getUserData(Long userSN); //서치쪽이니 건드리지 않기!
	
	public int insertUserData(UserDTO dto);
	
	public int findExistingUserId(String userId);
	public int findExistingNick(String nick);
	
	public Integer selectState(String userId);
	
	public int isIdPwdValid(@Param("userId")String userId, @Param("password")String password);
	
	public int updateUserInfo(UserDTO dto);
}
