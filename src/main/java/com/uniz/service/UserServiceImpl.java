package com.uniz.service;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.uniz.domain.MyUnizPoint;
import com.uniz.domain.UserDTO;
import com.uniz.mapper.UserMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{

	private UserMapper mapper;
	
	@Transactional
	@Override
	public int userRegister(UserDTO dto,List<Long> unizSN) {
		final int SUCCESS = 1;
		final int NO_DUPLICATION = 0;
		final int FAIL = -1;
		
		if(dto.getImgUrl()==null) {
			dto.setImgUrl("default.img");
		}
		//회원가입 전 NotNull 데이터 널 체크
		if(isNotUserDTO(dto)==true) {
			//데이터 중복체크
			if((mapper.userDuplicationCheck(dto) == NO_DUPLICATION)) {
				try {
					mapper.userDataInsert(dto);	
					
					mapper.userSelectUnizInsert(unizSN);
					return SUCCESS;
				}catch(Exception e){
					e.printStackTrace();
					return FAIL;
				}
			}
		}
		return FAIL;
	}
	
	@Transactional
	public boolean isNotUserDTO(UserDTO dto) {
		
		return dto.getUserId() != null &&  dto.getPassword() != null && dto.getNick() != null ? true : false;
		
	}
	public boolean isVaildUser(UserDTO dto) {
		return dto.getUserId() != null &&  dto.getPassword() != null ? true : false;
	}
	//Ajax용 닉네임 중복검사
	public String userNickDuplicationCheck(String nick) {
		final String NO_DUPLICATION = "SUCCESS";
		final String YES_DUPLICATION = "DUPLICATION";
		final String DB_ERROR = "DB ERROR";
		//1. null체크
		if(nick != null) {
			//해당 닉네임이 중복인지 확인
			try {
				// DB에서 COUNT한 값이 0보다 작다 = 중복되는값이 없다
				if(mapper.userNickDuplicationCheck(nick) <=0) {
					return NO_DUPLICATION;
				}
			}catch(Exception e){
				e.printStackTrace();
				return DB_ERROR;
			}
		}
		return YES_DUPLICATION;
	}
	
	//Ajax용 닉네임 아이디검사
	public String userIdDuplicationCheck(String userId) {
		final String NO_DUPLICATION = "SUCCESS";
		final String YES_DUPLICATION = "DUPLICATION";
		final String DB_ERROR = "DB ERROR";
		//1. null체크
		if(userId != null) {
			//해당 닉네임이 중복인지 확인
			try {
				// DB에서 COUNT한 값이 0보다 작다 = 중복되는값이 없다
				if(mapper.userIdkDuplicationCheck(userId) <=0) {
					return NO_DUPLICATION;
				}
			}catch(Exception e){
				e.printStackTrace();
				return DB_ERROR;
			}
		}
		return YES_DUPLICATION;
	}
	
	@Override
	public int userLogin(UserDTO user,HttpSession session) {
		final int SUCCESS = 1;
		final int NO_DUPLICATION = 0;
		final int FAIL = -1;
		
		//아이디, 비밀번호 유효성 체크
		if(isVaildUser(user) == true) {
			//아이디랑 비밀번호가 DB의 값과 일치하는지 확인한다.
			int loginResult = mapper.userLogin(user);
			
			//로그인 성공
			if(loginResult == SUCCESS) {
		
				//세션 생성
				user = mapper.getUser(user);
				List<MyUnizPoint> myUnizPoint = mapper.userUniz(user.getUserSN());
				user.setMyUnizPoint(myUnizPoint);
				session.setAttribute("user", user);
				return SUCCESS;
			}
		}
		return FAIL;
	}
	
}
