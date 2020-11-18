package com.uniz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniz.domain.MenuType;
import com.uniz.domain.UnizVO;
import com.uniz.mapper.UnizMapper;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@AllArgsConstructor
public class UnizServiceImpl implements UnizService {

	@Setter(onMethod_ = @Autowired)
	private UnizMapper mapper;


	@Override
	public boolean registerNoseq(UnizVO uniz) {
		log.info("registerNoseq......." + uniz);

		return mapper.insertNoSeq(uniz) == 1;
	}

	@Override
	public boolean registerSelectKey(UnizVO uniz) {
		log.info("registerSelectKey......." + uniz);

		return mapper.insertSelectKey(uniz) == 1;
	}

	@Override
	public UnizVO get(Long unizSN) {
		log.info("get............ : " + unizSN);

		return mapper.read(unizSN);
	}
	
	@Override
	public UnizVO findByKeywordForUserUnizInsert(String keyword) {
		log.info("findByKeywordForUserUnizInsert............ : " + keyword);
		
		return mapper.readByKeywordForUserUnizInsert(keyword);

	}

	@Override
	public List<UnizVO> getFavoriteList(Long userSN, int fvPoint, int limit) {
		log.info("getFavoriteList............ : " + userSN + "/" + limit);

		// userSN 이 null 이면 추천메뉴 프리셋을 가져옴
		return userSN != null ?
				mapper.getFavoriteList(userSN, fvPoint, limit)
				: getPresetList(MenuType.RECOMMEND.getMenu())
					;
	}

	@Override
	public boolean modify(UnizVO uniz) {

		log.info("modify........." + uniz);

		return mapper.update(uniz) == 1;
	}

	@Override
	public boolean remove(Long unizSN) {

		log.info("remove........" + unizSN);

		return mapper.delete(unizSN) == 1;
	}

	@Override
	public List<UnizVO> getPresetList(int menuSN) {

		log.info("getPresetList......." + menuSN);

		return mapper.getPresetList(menuSN);
	}
}
