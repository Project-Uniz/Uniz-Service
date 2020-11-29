package com.uniz.mapper;

import com.uniz.domain.UserDTO;

public interface UserMapper {
	
	public UserDTO getUserData(Long userSN); //서치쪽이니 건드리지 않기!
	
	public int insertUserData(UserDTO dto);
	
	public int findExistingUserId(String userId);
	public int findExistingNick(String nick);
	
	public int selectState(String userId);
	
	public int isIdPwdValid(String userId, String password);
	
	
	
	public int updateUserInfo(UserDTO dto);
}
