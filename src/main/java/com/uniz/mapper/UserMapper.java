package com.uniz.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.uniz.domain.MyUnizPoint;
import com.uniz.domain.UserDTO;

public interface UserMapper {
	
	//대윤
	//USERDATA INSERT
	public int userDataInsert(UserDTO dto);
	
	//USERDATA 테이블에서 중복되는 데이터 있는지 확인
	public int userDuplicationCheck(UserDTO dto);
	
	//닉네임 중복 체크 
	public int userNickDuplicationCheck(@Param("nick") String nick);

	public int userIdkDuplicationCheck(@Param("userId") String userId);

	public void userSelectUnizInsert(@Param("unizSN")List<Long> unizSN);

	public int userLogin(UserDTO user);

	public UserDTO getUserData(@Param("userSN")Long userSN);

	public List<MyUnizPoint> userUniz(@Param("userSN") Long userSN);

	public UserDTO getUser(UserDTO user);
}
