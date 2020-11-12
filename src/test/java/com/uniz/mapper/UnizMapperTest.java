package com.uniz.mapper;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.uniz.domain.UnizVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class UnizMapperTest {

	@Setter(onMethod_ = @Autowired)
	private UnizMapper mapper;

	@Test
	public void testGetPresetList() {
		mapper.getPresetList(1).forEach(uniz -> log.info(uniz));
	}

	@Test
	public void testInsert() {
		UnizVO uniz = UnizVO.builder()
				.unizSN(5000L)
				.unizKeyword("테스트유니즈mp")
				.unizTypeSN(8)
				.imgUrl("")
				.build();
		log.info(uniz);
		mapper.insertNoSeq(uniz);
		log.info(uniz);

		mapper.delete(uniz.getUnizSN());
	}

	@Test
	public void testInsertSelectKey() {
		UnizVO uniz = UnizVO.builder()
				.unizKeyword("테스트유니즈mp")
				.unizTypeSN(8)
				.imgUrl("")
				.build();

		mapper.insertSelectKey(uniz);
		log.info(uniz);

		if(uniz.getUnizSN() != null) {
			mapper.delete(uniz.getUnizSN());
		}
	}

	@Test
	public void testRead() {
		UnizVO uniz = mapper.read(1L);
		log.info(uniz);
	}

	@Test
	public void testDelete() {
		UnizVO uniz = UnizVO.builder()
				.unizSN(5000L)
				.unizKeyword("테스트유니즈mp")
				.unizTypeSN(8)
				.imgUrl("")
				.build();

		mapper.insertNoSeq(uniz);
		log.info("DELETE COUNT : " + mapper.delete(uniz.getUnizSN()));
	}

	@Test
	public void testUpdate() {

		UnizVO uniz = UnizVO.builder()
				.unizSN(5000L)
				.unizKeyword("테스트유니즈mp")
				.unizTypeSN(8)
				.imgUrl("")
				.build();

		mapper.insertNoSeq(uniz);
		uniz.setUnizKeyword("테스트유니즈수정mp");
		log.info("UPDATE COUNT : " + mapper.update(uniz));
		mapper.delete(uniz.getUnizSN());
	}
	
	@Test
	public void testGetListByKeywordOptList() {

		List<Integer> list = Arrays.asList(new Integer[] {1,2,4});

		log.info("searchList : " + mapper.getUnizListByKeywordOptList("야구", list) );
	}

}

