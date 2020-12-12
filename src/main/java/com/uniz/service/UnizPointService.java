package com.uniz.service;

import java.util.List;

import com.uniz.domain.MyUnizPoint;

public interface UnizPointService {

	public boolean registUnizPoint(Long userSN, List<Long> unizSNList, int point);

	public boolean setMyUnizPoint(Long userSN, List<Long> unizSNList, int point);

	public boolean incMyUnizPoint(Long userSN, List<Long> unizSNList, int point);

	public boolean decMyUnizPoint(Long userSN, List<Long> unizSNList, int point);

	public boolean recMyUnizPoint(Long userSN, List<Long> unizSNList, int point);

	public boolean addUnizPoint(Long userSN, Long parentUniz, int point);
	
	public String showVideoAddPoint(MyUnizPoint myUnizPoint);
}
