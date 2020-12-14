package com.uniz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uniz.mapper.AdminMapper;
import com.uniz.mapper.ApplyAttachMapper;
import com.uniz.mapper.ApplyMapper;

import lombok.AllArgsConstructor;
import lombok.Setter;

@Service
@AllArgsConstructor
public class AdminServiceImpl implements AdminService {
	
	@Setter(onMethod_ = @Autowired)
	private AdminMapper mapper;
	
	@Transactional
	@Override
	public boolean acceptApply(Long userSN) {

		if(mapper.acceptApply(userSN) == 1 && mapper.changeUser(userSN) == 1) {
			
			return true;
			
		}
		
			return false;
		
	}

}
