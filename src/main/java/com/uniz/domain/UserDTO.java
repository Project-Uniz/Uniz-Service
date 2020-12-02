package com.uniz.domain;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class UserDTO {
	
	public UserDTO() {
	}
	private Long userSN; //유저번호
	private String userId; //유저아이디
	private String password; //유저비밀번호
	private int userType; // 유저타입(1- 일반, 99 - 관리자)
	private String nick; //유저닉네임
	private String imgUrl; //유저 프로필경로
	private int state; // 유저상태 ( 1-정상, 2-탈퇴 등)
	
	@DateTimeFormat(pattern ="yyyy-MM-dd")
	private Date lastLoginDateTime; //최종로그인시간
	@DateTimeFormat(pattern ="yyyy-MM-dd")
	private Date createDateTime; // 가입일시
	@DateTimeFormat(pattern ="yyyy-MM-dd")
	private Date updateDateTime; // 변경일시
	@DateTimeFormat(pattern ="yyyy-MM-dd")
	private Date lastStateDateTime; //회원최종상태
	
	List<MyUnizPoint> myUnizPoint;
	
	
	
	

}
