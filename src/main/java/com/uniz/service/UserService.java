package com.uniz.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import com.uniz.domain.MyUnizPoint;
import com.uniz.domain.UserDTO;

public interface UserService {

	//대윤
	public int userRegister(UserDTO dto,List<Long> unizSN);
	
	public boolean isNotUserDTO(UserDTO dto); //test용
	
	public String userNickDuplicationCheck(String nick); //test용
	
	public String userIdDuplicationCheck(String userId); // test용
	
	public int userLogin(UserDTO user, HttpSession session);

	public List<MyUnizPoint> getUserUniz(Long userSN);

	public String modifyUser(UserDTO userDto, UserDTO modifyUserDto, String c_password, HttpSession session);

	public void changeUserState(Long userSN, int STATECODE);

	public String addMyPlayLog(Long userSN, Long videoSN, int currentTime);
	
	

}
