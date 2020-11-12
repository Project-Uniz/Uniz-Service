package com.uniz.mapper;

import java.util.List;

import com.uniz.domain.UnizVO;

public interface UnizMapper {

	public int insertNoSeq(UnizVO uniz);
	
	public int insertSelectKey(UnizVO uniz);
	
	public UnizVO readByKeyword(String keyword);

	public UnizVO read(Long unizSN);
	
	public int delete(Long unizSN);
	
	public int update(UnizVO uniz);
	
	public List<UnizVO> getPresetList(int menuSN);
}
