package com.uniz.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.uniz.domain.UnizVO;

public interface UnizMapper {

	public int insertNoSeq(UnizVO uniz);

	public int insertSelectKey(UnizVO uniz);

	public UnizVO read(int unizSN);

	public UnizVO readByKeyword(@Param("keyword") String keyword, @Param("unizTypeList") int unizType);

	public UnizVO readByKeywordForUserUnizInsert(@Param("keyword") String keyword);

	public List<UnizVO> getListByKeyword(String keyword);

	public List<UnizVO> getFavoriteList(@Param("userSN") Long userSN, @Param("fvPoint") int point, @Param("limit") int limit);

	public UnizVO read(Long unizSN);

	public int delete(Long unizSN);

	public int update(UnizVO uniz);

	public List<UnizVO> getPresetList(int menuSN);
}
