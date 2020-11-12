package com.uniz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniz.mapper.TimeMapper;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@AllArgsConstructor
public class SampleServiceImpl implements SampleService {

	@Setter(onMethod_ = @Autowired)
	private TimeMapper mapper;
	
	@Override
	public String getTime() {
		log.info("getTime............");
		
		return mapper.getTime2();
	}
}
