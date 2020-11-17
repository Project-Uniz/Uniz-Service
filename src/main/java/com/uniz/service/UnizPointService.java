package com.uniz.service;

import java.util.List;

public interface UnizPointService {

	public boolean registUnizPoint(Long userSN, List<Long> unizSNList, int point);

	public boolean setMyUnizPoint(Long userSN, List<Long> unizSNList, int point);

	public boolean incMyUnizPoint(Long userSN, List<Long> unizSNList, int point);

	public boolean decMyUnizPoint(Long userSN, List<Long> unizSNList, int point);
}
