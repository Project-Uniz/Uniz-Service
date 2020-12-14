package com.uniz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uniz.domain.MyUnizPoint;
import com.uniz.mapper.UnizPointMapper;
import com.uniz.mapper.UserMapper;

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
	public boolean addUnizPoint(Long userSN, Long parentUniz, int point) {

		boolean result = false;
		if (userSN != null) {
			
			mapper.addMyUnizPoint(userSN, parentUniz, point);
			result = true;
			result = mapper.addHistory(userSN, parentUniz, point, 3) == 1;
					
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
	
	//대
	public String showVideoAddPoint(MyUnizPoint myUnizPoint) {
		final String SUCCESS = "SUCCESS";
		final String FAIL = "FAIL";
		final int ADD_POINT = 5;
		
		if(myUnizPoint.getUserSN() != null && myUnizPoint.getUnizSN() != null) {
			
			int result;
			//1. 회원이 비디오 시청시 해당 비디오의 키워드를 MyunizpointList에 추가
			log.info("videoCateSN :" + myUnizPoint.getUnizSN());
			//해당 유튜브 카테고리로 매핑된 uniz를 가져온다.
			//1-1 . MyUnizPoint에 해당 uniz가 등록되어있을경우 update point +
			//1-2 . MyUnizPoint에 해당 uniz가 등록되지 않았을 경우 insert// 
			result = mapper.addMyUnizPoint(myUnizPoint.getUserSN(),myUnizPoint.getUnizSN(),ADD_POINT);				

			
			//-> 현재까지 본 영상을 어떻게 저장?
			return result ==1 ? SUCCESS : FAIL;
					
		}
		
		return FAIL;
		
	}
}