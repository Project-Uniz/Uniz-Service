package com.uniz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uniz.domain.ApplyAttachVO;
import com.uniz.domain.ApplyVO;
import com.uniz.mapper.ApplyAttachMapper;
import com.uniz.mapper.ApplyMapper;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@AllArgsConstructor
public class ApplyCreatorServiceImpl implements ApplyCreatorService {
	
	@Setter(onMethod_ = @Autowired)
	private ApplyMapper mapper;
	
	@Setter(onMethod_ = @Autowired)
	private ApplyAttachMapper attachMapper;
	
	@Transactional
	@Override
	public void apply(ApplyVO vo) {
		
		mapper.apply(vo);
		
		log.info("vo======= " + vo);
		log.info("vo.getApplySN : " +  vo.getApplySN());
		
		if(vo.getAttachList() == null || vo.getAttachList().size() <= 0) {
			
			log.info("test 1 ");
			return;
		}
		
		log.info("test Vo : " + vo);
		
		vo.getAttachList().forEach(attach -> {
			
			attach.setUserSN(vo.getUserSN());
			attach.setApplySN(vo.getApplySN());
			attachMapper.insert(attach);
			
		});
		
		
	}

	@Override
	public ApplyVO getApply(Long userSN) {
		return mapper.getApply(userSN);
	}
	
	@Transactional
	@Override
	public boolean modifyApply(ApplyVO vo) {
		
		attachMapper.deleteAll(vo.getUserSN());
		
		boolean modifyResult = mapper.modifyApply(vo) == 1;
		
		log.info("===========" + vo.getApplySN());
		
		if(modifyResult && vo.getAttachList() != null && vo.getAttachList().size() > 0) {
			
			vo.getAttachList().forEach(attach -> {
				
				attach.setUserSN(vo.getUserSN());
				attach.setApplySN(vo.getApplySN()); // 이게 null로 들어가서 에러
				
				attachMapper.insert(attach);
				
			});
			
		}
		
		return modifyResult;
		
	}
	
	@Transactional
	@Override
	public boolean cancleApply(Long applySN) {
		
		attachMapper.deleteAll(applySN);
		
		final int CANCLE = mapper.cancleApply(applySN);
		
		if(CANCLE == 1) {
			
			return true;
					
		}
		
			return false;
		
	}

	@Override
	public int checkApply(Long userSN) {
		log.info("checkApply========");
		int checkApply = mapper.checkApply(userSN);
		return checkApply;
	}					
	
	@Override
	public List<ApplyAttachVO> getAttachList(Long applySN){
		
		return attachMapper.findByUserSN(applySN);
		
	}
	
	@Override
	public List<ApplyVO> getAllApply(){
		
		return mapper.getAllApply();
		
	}
	

}
