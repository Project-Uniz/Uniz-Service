package com.uniz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public UnizVO findByKeyword(String keyword) {
		log.info("getByKeyword............" + keyword);
		
		return mapper.readByKeyword(keyword);
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
