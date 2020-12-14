package com.uniz.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.uniz.domain.MyUnizPoint;

public interface UnizPointMapper {

	public int insert(MyUnizPoint unizPoint);

	public MyUnizPoint read(MyUnizPoint unizPoint);

	public MyUnizPoint read(@Param("userSN") Long userSN, @Param("unizSN") Long unizSN);

	public int update(@Param("userSN") Long userSN, @Param("unizSNList") List<Long> unizSN, @Param("point") int Point);	

	public int incMyUnizPoint(@Param("userSN") Long userSN, @Param("unizSNList") List<Long> unizSN, @Param("incPoint") int incPoint);

	public int decMyUnizPoint(@Param("userSN") Long userSN, @Param("unizSNList") List<Long> unizSN, @Param("decPoint") int decPoint);

	public int addHistory(@Param("userSN") Long userSN, @Param("unizSN") Long unizSN, @Param("point") int Point, @Param("type") int type);

	public List<MyUnizPoint> getMyUnizPointList(Long userSN);

	public int addMyUnizPoint(@Param("userSN")Long userSN, @Param("unizSN")Long parentUniz, @Param("addPoint")int point);
}
