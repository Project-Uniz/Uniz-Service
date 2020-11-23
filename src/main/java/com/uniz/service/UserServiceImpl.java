package com.uniz.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.uniz.domain.UserData;
import com.uniz.domain.UserTemp;
import com.uniz.mapper.UserMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;


@Log4j
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
	
	private UserMapper mapper;

	@Override
	public String register(UserTemp temp) {
		//회원정보를 넣는 메서드. 
		
		
		String msg = "failed";

		int result = mapper.chkId(temp.getUserId());
		// 아이디 중복체크. 

		if (result ==0) {
			// 중복아님. 

			UserData data = UserData.builder().provider("T").nick(temp.getUserId()).state(1)
					.imgUrl("").userType(1)
					.build();

			if (mapper.insert(data) == 1) {
				// userData에  insert
				msg = "userData inserted.......";

				temp.setUserSN(data.getUserSN());

			if (mapper.insertTemp(temp) == 1) {
				//userTemp에 insert

				msg = "success";
				}
			}
			return msg;
		}

		System.out.println("회원가입에 실패했습니다.");
		return msg;
		//failed  떠야함. 
	}

	@Override
	public boolean isNotNull(UserTemp vo) {

		if(vo.getUserId()=="" || vo.getPassword() =="") {
			return false;
		}
		return true;
	}
	
	
	@Override 
	public boolean isChecked(String chk) {
		
		if(chk==null) {
			return false;
			
		}else
			return true;
	}
	@Override
	public String login(UserTemp vo, HttpServletRequest request, HttpServletResponse response
					) {

		String msg = "Failed";
		String chk = request.getParameter("chk");
		Cookie cookie = new Cookie("userId", vo.getUserId());

		// 1.아이디와 패스워드 일치확인.
		int count = mapper.isIdPwdValid(vo);

		if (count == 1) {

				// 2. userTemp로부터 해당 정보를 전부 가져와서 저장.
				UserTemp temp = mapper.selectUserTemp(vo.getUserId());
				
				System.out.println(temp);
				// 3. userTemp의 SN을 userData의 SN과 조인.
				Long userDataSN = mapper.getDataSN(temp.getUserSN());
				
				msg = "join success!";
				
				if(chk!=null) {
					response.addCookie(cookie);
				}else {
					
					cookie.setMaxAge(0); // 3. 쿠키의 유효시간을 0으로 변경(쿠키삭제)
					response.addCookie(cookie);} // 4. 쿠키를 응답에 포함시킨다.
				
				if (userDataSN != null && userDataSN != 0) {
					
					// 해당 userDataSN이 있으면 해당 userData정보를 전부 가져와 저장.
					UserData data = mapper.getUserData(userDataSN);
					
					HttpSession session = request.getSession();
					
					//userData 정보를 세션에 저장.  
					session.setAttribute("userId", data);
					
					System.out.println("이것은 세션의 주소입니다 : "+session);
					
					System.out.println("session success!!!!");
				}
				
			
				msg = "maincontent";
				System.out.println("로그인에 성공하였으므로 메인으로 가겠습니다.");
				return msg;
				
			}else {//아이디와 패스워드 불일치. 
				//쿠키를 삭제한다. 
				
				cookie.setMaxAge(0); // 3. 쿠키의 유효시간을 0으로 변경(쿠키삭제)

				response.addCookie(cookie);} // 4. 쿠키를 응답에 포함시킨다.
				 
				
				
				msg = "/user/loginForm";
				System.out.println("로그인에 실패했습니다.");
				return msg;
			}
			
			
		
	
@Override
public void deleteCookie(UserTemp temp, HttpServletRequest request, HttpServletResponse response) {

	
	Cookie[] cookies = request.getCookies(); // cookies가 null 수 있음에 주의

	if(cookies!=null) {//쿠키가 널이 아니라면

	for(int i=0;i<cookies.length;i++) {

	if(cookies[i].getName().equals("userId")) {

	Cookie cookie = new Cookie("userId", ""); // 2. 쿠키를 생성


	cookie.setMaxAge(0); // 3. 쿠키의 유효시간을 0으로 변경(쿠키삭제)

	response.addCookie(cookie); // 4. 쿠키를 응답에 포함시킨다. 

	} // end if

	} // end for.

	} // end null if

	}// end method
}
	
	

