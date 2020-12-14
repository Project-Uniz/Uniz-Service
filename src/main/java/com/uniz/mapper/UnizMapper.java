package com.uniz.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.uniz.domain.UnizVO;

public interface UnizMapper {

	public int insertNoSeq(UnizVO uniz);

	public int insertSelectKey(UnizVO uniz);

	public UnizVO read(Long unizSN);

	public int update(UnizVO uniz);

	public int delete(Long unizSN);
	
	public List<UnizVO> getPresetList(int menuSN);

	public UnizVO readByKeywordForUserUnizInsert(@Param("keyword") String keyword);

	public List<UnizVO> getUnizListByKeywordOptList(@Param("keywordList") List<String> keywordList, @Param("unizTypeList") List<Integer> unizTypeList);

	public List<UnizVO> getFavoriteList(@Param("userSN") Long userSN, @Param("fvPoint") int point, @Param("limit") int limit);

	public Long findParentUniz(@Param("utbCateSN")Long utbCateSN);

}
