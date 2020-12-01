package com.uniz.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDTO {
	
	public UserDTO() {
	}
	private Long userSN;
	private String nick;
	private String userId;
	private String password;
	private String imgUrl;
	private int userType;
	
	private int state;
	
	private Date lastLoginDateTime;
	private Date createDateTime;
	private Date updateDateTime;
	private Date lastStateDateTime;
	
	
	
	

}
