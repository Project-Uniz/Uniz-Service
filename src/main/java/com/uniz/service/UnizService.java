package com.uniz.service;

import java.util.List;

import com.uniz.domain.UnizVO;

public interface UnizService {
	
	public boolean registerNoseq(UnizVO uniz);
	
	public boolean registerSelectKey(UnizVO uniz);
	
	public UnizVO findByKeyword(String keyword);
	
	public boolean modify(UnizVO uniz);
	
	public boolean remove(Long unizSN);
	
	public List<UnizVO> getPresetList(int menuSN);	
		
}
