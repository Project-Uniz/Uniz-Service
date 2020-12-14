package com.uniz.service;

import java.util.List;

import com.uniz.domain.ApplyAttachVO;
import com.uniz.domain.ApplyVO;

public interface ApplyCreatorService {
	
	public void apply(ApplyVO vo);
	
	public ApplyVO getApply(Long userSN);
	
	public boolean modifyApply(ApplyVO vo);
	
	public boolean cancleApply(Long userSN);
	
	public int checkApply(Long userSN);
	
	public List<ApplyAttachVO> getAttachList(Long userSN);
	
	public List<ApplyVO> getAllApply();

}
