package com.uniz.mapper;

import java.util.List;

import com.uniz.domain.ApplyVO;

public interface ApplyMapper {
	
	public int apply(ApplyVO vo); // 크리에이터 신청
	
	public ApplyVO getApply(Long userSN); // 신청한 유저가 신청한 정보 보기
	
	public int modifyApply(ApplyVO vo); // 신청한 내용 수정하기
	
	public int cancleApply(Long applySN); // 신청 한 내용 취소하기
	
	public int checkApply(Long userSN); // 여러번 신청 못하게 체크
	
	public List<ApplyVO> getAllApply();
	
}
