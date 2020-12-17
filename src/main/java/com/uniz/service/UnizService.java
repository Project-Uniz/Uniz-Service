package com.uniz.service;

import java.util.List;

import com.uniz.domain.MyUnizPoint;
import com.uniz.domain.UnizVO;

public interface UnizService {

	public boolean registerNoseq(UnizVO uniz);

	public boolean registerSelectKey(UnizVO uniz);

	public UnizVO get(Long unizSN);

	public UnizVO findByKeywordForUserUnizInsert(String keyword);

	public List<UnizVO> getFavoriteList(Long unizSN, int fvPoint, int maxCnt);

	public boolean modify(UnizVO uniz);

	public boolean remove(Long unizSN);

	public List<UnizVO> getPresetList(int menuSN);

	public List<MyUnizPoint> getMyPointHistory(Long userSN);

}
