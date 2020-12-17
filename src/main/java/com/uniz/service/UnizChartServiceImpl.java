package com.uniz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniz.domain.UnizChartVO;
import com.uniz.mapper.UnizChartMapper;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;


@Log4j
@Service
@AllArgsConstructor

public class UnizChartServiceImpl implements UnizChartService {
	
	@Setter(onMethod_ = @Autowired)
	private UnizChartMapper mapper;
	
	@Override
	public List<UnizChartVO> chartData() {
		
		log.info("get chartData........");
		
		return mapper.chartData();
	}

}
