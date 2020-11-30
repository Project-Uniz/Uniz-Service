package com.uniz.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.uniz.domain.MenuType;
import com.uniz.domain.UnizTypeEnum;
import com.uniz.domain.UnizVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class UnizServiceTest {

	@Setter(onMethod_ = {@Autowired})
	private UnizService service;
	
	@Test
	public void testExist() {
		log.info(service);		
		assertNotNull(service);
	}
	
	@Test
	public void testRegister() {
		UnizVO uniz = UnizVO.builder()
				.unizSN(5000L)
				.unizKeyword("테스트유니즈svc")
				.unizTypeSN(UnizTypeEnum.USERMADE.getTypeSN())
				.imgUrl("")
				.build();
		
		service.registerNoseq(uniz);
		log.info(uniz);
		
		service.remove(uniz.getUnizSN());
		
		log.info("생성된 유니즈의 SN: " + uniz.getUnizSN());
	}
	
	@Test
	public void testRegisterSelectKey() {
		UnizVO uniz = UnizVO.builder()
				.unizKeyword("테스트유니즈svc")
				.unizTypeSN(UnizTypeEnum.USERMADE.getTypeSN())
				.imgUrl("")
				.build();
		
		service.registerSelectKey(uniz);
		log.info(uniz);
		
		Long sn = uniz.getUnizSN();
		
		if (sn != null)
		service.remove(sn);
		
		log.info("생성된 유니즈의 SN: " + uniz.getUnizSN());
	}
	
	@Test
	public void testGetPresetList() {
		
		service.getPresetList(MenuType.SEARCH.getMenu()).forEach(uniz->log.info(uniz));
	}

	@Test
	public void testModify() {
		UnizVO uniz = UnizVO.builder()
				.unizSN(5000L)
				.unizKeyword("테스트유니즈svc")
				.unizTypeSN(UnizTypeEnum.USERMADE.getTypeSN())
				.imgUrl("")
				.build();
		
		service.registerNoseq(uniz);
		uniz.setUnizKeyword("테스트유니즈수정svc");
		
		log.info("MODIFY RESULT: " + service.modify(uniz));
		service.remove(uniz.getUnizSN());
	}
	
	@Test
	public void testRemove() {
		UnizVO uniz = UnizVO.builder()
				.unizSN(5000L)
				.unizKeyword("테스트유니즈svc")
				.unizTypeSN(UnizTypeEnum.USERMADE.getTypeSN())
				.imgUrl("")
				.build();
		
		service.registerNoseq(uniz);

		log.info("REMOVE RESULT: " + service.remove(uniz.getUnizSN()));
	}

}
