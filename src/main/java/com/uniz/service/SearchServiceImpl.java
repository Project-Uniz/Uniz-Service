package com.uniz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniz.mapper.SearchMapper;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@AllArgsConstructor
public class SearchServiceImpl implements SearchService {

	@Setter(onMethod_ = @Autowired)
	private SearchMapper mapper;

	@Override
	public Integer getOptions(Long userSN) {
		log.info("getOptions............ : " + userSN);

		return mapper.getOptions(userSN);
	}

	@Override
	public boolean makeOptions(Long userSN, int options) {
		log.info("setOptions............ : " + userSN + "/" + options);

		return mapper.makeOptions(userSN, options) == 1;
	}

	@Override
	public boolean setOptions(Long userSN, int options) {
		log.info("setOptions............ : " + userSN + "/" + options);

		return mapper.setOptions(userSN, options) == 1;
	}
}
