package com.uniz.mapper;

import com.uniz.domain.UserData;
import com.uniz.domain.UserTemp;

public interface UserMapper {
	

	
	public int insert(UserData data);
	
	public int insertTemp(UserTemp temp);
	//회원가입메서드. 
	
	public int chkId(String userId);
	//아이디 중복체크 메서드. (아이디만 체크한다.)
	
	public int isIdPwdValid(UserTemp vo);
//	로그인 할때 아이디와 패스워드가 일치하는지.
	
	
	
	
	public UserTemp selectUserTemp(String userId);
	
	public Long getDataSN(Long userSN);
	
	public UserData getUserData(Long userSN);

}
