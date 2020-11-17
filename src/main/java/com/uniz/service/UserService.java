package com.uniz.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.uniz.domain.UserTemp;

public interface UserService {

	
	public String register(UserTemp temp);
	
	
	public boolean isNotNull(UserTemp vo);
	
	
	public String login(UserTemp vo, HttpServletRequest request, HttpServletResponse response);

	public boolean isChecked(String chk);
	

	public void deleteCookie(UserTemp temp, HttpServletRequest request, HttpServletResponse response);
}