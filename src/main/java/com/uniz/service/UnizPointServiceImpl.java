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

			if (0 == mapper.addHistory(userSN, unizSN, point, 1)) {
				return false;
			}
		}

		return true;
	}

	@Transactional
	@Override
	public boolean setMyUnizPoint(Long userSN, List<Long> unizSNList, int point) {

		boolean result = false;
		if ( mapper.update(userSN, unizSNList, point) == unizSNList.size() ) {
			result = true;
			for (Long unizSN : unizSNList) {
				result = mapper.addHistory(userSN, unizSN, point, 2) == 1;
				if (result == false) {
					break;
				}
			}
		}
		return result;
	}

	@Transactional
	@Override
	public boolean incMyUnizPoint(Long userSN, List<Long> unizSNList, int point) {

		boolean result = false;
		if ( mapper.incMyUnizPoint(userSN, unizSNList, point) == unizSNList.size() ) {
			result = true;
			for (Long unizSN : unizSNList) {
				result = mapper.addHistory(userSN, unizSN, point, 3) == 1;
				if (result == false) {
					break;
				}
			}
		}
		return result;
	}

	@Transactional
	@Override
	public boolean decMyUnizPoint(Long userSN, List<Long> unizSNList, int point) {

		boolean result = false;
		if ( mapper.decMyUnizPoint(userSN, unizSNList, point) == unizSNList.size() ) {
			result = true;
			for (Long unizSN : unizSNList) {
				result = mapper.addHistory(userSN, unizSN, point, 4) == 1;
				if (result == false) {
					break;
				}
			}
		}
		return result;
	}

	@Transactional
	@Override
	public boolean recMyUnizPoint(Long userSN, List<Long> unizSNList, int point) {

		boolean result = false;
		if ( mapper.incMyUnizPoint(userSN, unizSNList, point) == unizSNList.size() ) {
			result = true;
			for (Long unizSN : unizSNList) {
				result = mapper.addHistory(userSN, unizSN, point, 5) == 1;
				if (result == false) {
					break;
				}
			}
		}
		return result;
	}
}