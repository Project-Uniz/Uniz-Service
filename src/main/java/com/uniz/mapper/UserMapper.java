package com.uniz.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.uniz.domain.MyUnizPoint;
import com.uniz.domain.UserDTO;

public interface UserMapper {
	
	public UserDTO getUserData(Long userSN); //서치쪽이니 건드리지 않기!
	
	public int insertUserData(UserDTO dto);
	
	public int findExistingUserId(String userId);
	public int findExistingNick(String nick);
	
	public Integer selectState(@Param("userId") String userId, @Param("password")String password);
	
	public int isIdPwdValid(@Param("userId")String userId, @Param("password")String password);
	
	public int updateUserInfo(UserDTO dto);
	
	public UserDTO getUserDTO(@Param("userId") String userId); //read.jsp  와 modify.jsp에서 UserDTO를 읽기 위함.
	
	public int updateState(UserDTO dto);
	
	public List<UserDTO> getUserDTOList(); //아이디 중복체크에 쓸 DB에서 유저정보 전부 가져오기. 

	
	//대윤
	//USERDATA INSERT
	public int userDataInsert(UserDTO dto);
	
	//USERDATA 테이블에서 중복되는 데이터 있는지 확인
	public int userDuplicationCheck(UserDTO dto);
	
	//닉네임 중복 체크 
	public int userNickDuplicationCheck(@Param("nick") String nick);

	public int userIdkDuplicationCheck(@Param("userId") String userId);

	public void userSelectUnizInsert(@Param("unizSN")List<Long> unizSN);
}
