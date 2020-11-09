package com.uniz.mapper;

import org.apache.ibatis.annotations.Param;

public interface SearchMapper {

	public Integer getOptions(Long userSN);

	public int makeOptions(@Param("userSN") Long userSN, @Param("options") int options);

	public int setOptions(@Param("userSN") Long userSN, @Param("options") int options);	
}
