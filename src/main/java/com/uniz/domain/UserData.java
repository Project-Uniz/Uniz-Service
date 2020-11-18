package com.uniz.domain;


import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class UserData {

	
	private Long userSN;
	
	private String provider;
	private int userType;
	private String nick;
	private String imgUrl;
	
	private int state;
	
	private Date lastLoginDateTime;
	private Date createdDateTime;
	private Date updatedDateTime;
	private Date lastStateDateTime;
}
