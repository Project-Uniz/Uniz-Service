package com.uniz.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.uniz.domain.UnizVO;

public interface SearchMapper {

	public Integer getOptions(Long userSN);

	public int makeOptions(@Param("userSN") Long userSN, @Param("options") int options);

	public int setOptions(@Param("userSN") Long userSN, @Param("options") int options);	

	public List<UnizVO> getSearchUnizList(@Param("unizSN") Long unizSN);

	public List<UnizVO> getSearchUnizListBySNList(@Param("unizSNList") List<Long> unizSNList);

	public List<Integer> getUnizTypeFromUnizSN(@Param("unizSN") Long unizSN);
}
