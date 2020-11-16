package com.uniz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uniz.domain.MyUnizPoint;
import com.uniz.mapper.UnizPointMapper;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@AllArgsConstructor
public class UnizPointServiceImpl implements UnizPointService {

	@Setter(onMethod_ = @Autowired)
	private UnizPointMapper mapper;

	@Transactional
	@Override
	public boolean registUnizPoint(Long userSN, List<Long> unizSNList, int point) {

		for (Long unizSN : unizSNList) {
			if (0 == mapper.insert(	MyUnizPoint.builder()
										.userSN(userSN)
										.unizSN(unizSN)
										.point(point)
										.build()) ) {
				return false;
			}
		}

		return true;
	}

	@Override
	public boolean setMyUnizPoint(Long userSN, List<Long> unizSNList, int point) {

		return mapper.update(userSN, unizSNList, point) == unizSNList.size();
	}

	@Override
	public boolean incMyUnizPoint(Long userSN, List<Long> unizSNList, int point) {

		return mapper.incMyUnizPoint(userSN, unizSNList, point) == unizSNList.size();
	}

	@Override
	public boolean decMyUnizPoint(Long userSN, List<Long> unizSNList, int point) {

		return mapper.decMyUnizPoint(userSN, unizSNList, point) == unizSNList.size();
	}
}