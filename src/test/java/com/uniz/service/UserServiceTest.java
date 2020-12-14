package com.uniz.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.uniz.domain.MyUnizPoint;
import com.uniz.domain.UserDTO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class UserServiceTest {

	@Setter(onMethod_ = {@Autowired})
	private UserService service;
	
	@Test
	public void testUserRegister() {
//		List<MyUnizPoint> myUnizPoint = new ArrayList<>();
//		MyUnizPoint MUP = MyUnizPoint.builder().unizSN((long) 1001).point(50).build();
//		MyUnizPoint MUP2 = MyUnizPoint.builder().unizSN((long) 1002).point(50).build();
//		MyUnizPoint MUP3 = MyUnizPoint.builder().unizSN((long) 1003).point(50).build();
		
		List<Long> unizSN = new ArrayList<>();
		
		unizSN.add((long) 1001);
		unizSN.add((long) 1002);
		unizSN.add((long) 1003);
				
		UserDTO user = UserDTO.builder()
				 .userId("test0002")
				 .password("1234")
				 .nick("서교동의밤2")
				 .imgUrl("")
				 .build();	
		
		int insertResult = service.userRegister(user,unizSN);
		
		log.info("USER INSERT : "+ insertResult);
		
		assertTrue(insertResult == 1);
	}
	
	@Test
	public void isNotUserDTO() {
		UserDTO user = UserDTO.builder()
				 .userId("test0001")
				 .password("1234")
				 .nick("서교동의밤")
				 .build();	
		
		log.info("Null값없이 데이터를 보냈을 경우 : " + service.isNotUserDTO(user));
		assertTrue(service.isNotUserDTO(user));
		
		UserDTO user2 = UserDTO.builder()
				 
				 .password("1234")
				 .nick("서교동의밤")
				 .build();	
		
		log.info("아이디가 null값인 경우 : " + service.isNotUserDTO(user2));
		assertFalse(service.isNotUserDTO(user2));
		
		UserDTO user3 = UserDTO.builder()
				 .userId("test0001")
				 .password("1234")
				 .build();	
		
		log.info("닉네임이 null값인 경우 : " + service.isNotUserDTO(user2));
		assertFalse(service.isNotUserDTO(user3));
		
	}
	
	@Test
	public void userNickDuplicationCheck() {
		String testNick1 = "newNick";
		String testNick2 = "테스터윤"; 
		
		log.info("중복데이터가 없는 경우 " + service.userNickDuplicationCheck(testNick1));
		log.info("중복데이터가 있는 경우 " + service.userNickDuplicationCheck(testNick2));
	}
	
	@Test
	public void userIdDuplicationCheck() {
		
	}
	
}
