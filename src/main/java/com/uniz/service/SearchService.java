package com.uniz.service;

import java.util.List;
import java.util.Map;

import com.uniz.domain.UnizVO;
import com.uniz.domain.VideoDataListResult;

public interface SearchService {

	public Integer getOptions(Long userSN);

	public Map<Integer, String> getOptionNameMap(Long userSN);

	public List<Integer> getOptionList(Long userSN);

	public List<UnizVO> getSearchUnizList(Long userSN);

	public Map<Integer, String> setOptions(Long userSN, List<Integer> options);

	public List<VideoDataListResult> getSearchResult(List<String> keywordList, List<UnizVO> searchUnizList);
}
