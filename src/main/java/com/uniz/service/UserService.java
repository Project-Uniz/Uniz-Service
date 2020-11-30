package com.uniz.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import com.uniz.domain.UserDTO;

public interface UserService {

	public boolean validateForm(UserDTO dto);
	
	public boolean getExistingUserId( String userId);
	public boolean getExistingNick( String nick);
	public boolean insertUserData(UserDTO dto);
	public int register(UserDTO dto, Model model);
	

	public boolean stateCheck(String userId);
	
	public boolean isIdPwdValid(UserDTO dto);
	
	public int login(UserDTO dto, Model model, HttpServletRequest request, HttpServletResponse response);
	
	public void validateSession(HttpServletRequest request, String userId);
	public void deleteCookie(HttpServletRequest request, HttpServletResponse response);
	public void makeCookie(HttpServletResponse response, String userId);

	public boolean isIdRememberChecked(HttpServletRequest request); 
	
	public boolean modifyUserInfo(UserDTO dto);
}
