package com.uniz.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniz.domain.UnizTypeEnum;
import com.uniz.domain.UnizVO;
import com.uniz.domain.VideoDataListResult;
import com.uniz.domain.VideoDataVO;
import com.uniz.mapper.SearchMapper;
import com.uniz.mapper.UnizMapper;
import com.uniz.mapper.VideoMapper;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@AllArgsConstructor
public class SearchServiceImpl implements SearchService {

	@Setter(onMethod_ = @Autowired)
	private UnizMapper unizMapper;

	@Setter(onMethod_ = @Autowired)
	private SearchMapper mapper;

	@Setter(onMethod_ = @Autowired)
	private VideoMapper videoMapper;

	@Override
	public Integer getOptions(Long userSN) {
		log.info("getOptions............ : " + userSN);

		return mapper.getOptions(userSN);
	}
	
	@Override
	public List<UnizVO> getSearchUnizList(Long userSN) {
		log.info("getSearchUnizList............ : " + userSN);

		// 1. 내 옵션 리스트를 가져옴 
		List<Integer> optList = getOptionList(userSN);

		// 2. 옵션 리스트로 서치유니즈 SN 리스트 생성
		// TODO 현재 하드코딩 상태 : 동적으로 혹은 DB에서 가져올 수 있도록 변환 필요
		List<Long> searchUnizSNList = makeSearchUnizSNList(optList);

		log.info("searchUnizSNList........... : " + searchUnizSNList);

		List<UnizVO> searchUnizList = mapper.getSearchUnizListBySNList(searchUnizSNList);

		return searchUnizList;
	};


	/**
	 * 검색용 유니즈SN 리스트 생성기
	 * @param optList
	 * @return
	 * 
	 * 하드코드 상태 : 나중에 고칠 것
	 * 
	 * 1. 만들값, 백단위 이하 3자리 숫자
	 * 2. 입력값수, 1개 이상 n개 이하, 중복없음, 오름차순 정렬된 상태  [1,2,3,6,7]  [1,2,3,4,5,6,7] [1,3,7]
	 *     - optList.size() > 0,  inputOptions.size() <= n
	 * 3. 결과값 중복없음, 오름차순  : 123 134 245 267
	 * 4. 입력값이 3개 미만이면 뒤에 0 붙임 : 120 100
	 * 
	 *  nP1, nP2, nP3 을 각각 가져오자
	 *  
	 */

	public List<Long> makeSearchUnizSNList(List<Integer> optList) {
		List<Long> result = new ArrayList<>();

		int optSize = optList.size();
		
		// 1개만 픽
		optList.forEach( x
				-> result.add(2000L + 100 * x)	);

		if (optSize > 1) {
			// 2개 픽
			for(int i = 0; i<optSize-1; i++) {
				int temp_result = 2000 + optList.get(i) * 100;
				for(int j = i+1; j<optList.size(); j++) {
					result.add(temp_result + optList.get(j) * 10 + 0L);
				}
			}
		}

		if (optSize > 2) {
			// 3개픽
			for(int i = 0; i<optSize-2; i++) {
				int temp_result = 2000 + optList.get(i) * 100;
				for(int j = i+1; j<optSize-1; j++) {
					int temp_result_2 = temp_result + optList.get(j) * 10;
					for(int k = j+1; k<optSize; k++) {
						result.add(temp_result_2 + optList.get(k) + 0L);
					}
				}
			}
		}

		return result;
	}

	@Override
	public Map<Integer, String> getOptionNameMap(Long userSN) {
		log.info("getOptionNameMap............ : " + userSN);

		Map<Integer, String> map = new HashMap<>();

		Integer optionValue = mapper.getOptions(userSN);

		// TODO 스트림변환?
		if (optionValue != null) {
			UnizTypeEnum[] opts = UnizTypeEnum.values();
			for( int i=1 ; i<opts.length ; i++) {
				if (optionValue == null || optionValue == 0) {
					break;
				} else if ((optionValue & 1) == 1 ) {
					map.put(opts[i].getTypeSN(), opts[i].getTypeNameKR());
				}
				optionValue >>>= 1;
			}
		}

		return map;
	}

	@Override
	public List<Integer> getOptionList(Long userSN) {
		log.info("getOptionList............ : " + userSN);

		List<Integer> list = new ArrayList<>();

		Integer optionValue = userSN != null ? mapper.getOptions(userSN) : null;

		if (optionValue == null) {
			optionValue = (1<<UnizTypeEnum.SEARCHEND.getTypeSN()) - 1;
		}

		// TODO 스트림변환?
		// UnizTypeEnum 을 클래스로 변경하도록 고려
		// 해당 클래스 리스트를 관리하는 매니저를 싱글턴으로 만들고
		// 첫 호출 시점에 DB 접근해서 클래스 오브젝트를 박는 형태
		//

		UnizTypeEnum[] opts = UnizTypeEnum.values();
		for( int i=1 ; i<opts.length ; i++) {
			if (optionValue == 0) {
				break;
			} else if ((optionValue & 1) == 1 ) {
				list.add(opts[i].getTypeSN());
			}
			optionValue >>>= 1;
		}

		return list;
	}

	@Override
	public Map<Integer, String> setOptions(Long userSN, List<Integer> options) {
		log.info("setOptions............ : " + userSN + "/" + options);

		Map<Integer, String> map = new HashMap<>();

		int optionValue = 0;

		for (Integer optNum : options) {
			if (optNum == null) {
				continue;
			}
			UnizTypeEnum opt = UnizTypeEnum.valueOf(optNum);
			if (opt != null) {
				map.put(opt.getTypeSN(), opt.getTypeNameKR());
				optionValue += ( 1 << (opt.getTypeSN()-1) );
			}
		}

		boolean result = false;
		
		if(map.size() > 0) {
			if ( mapper.getOptions(userSN) == null ) {
				result = mapper.makeOptions(userSN, optionValue) > 0;
			} else {
				result = mapper.setOptions(userSN, optionValue) > 0;
			}
			if(result == false) {
				map.clear();
			}
		}

		return map;
	}


	/**
	 * 
	 * 옵션 별 키워드 비디오 검색
	 * @param keyword
	 * @param options
	 * @return
	 * 
	 */
	private List<ArrayList<VideoDataVO>> getVideoListByOptions(List<String> keywordList, List<Integer> options) {

		// 1. 키워드, 옵션으로 유니즈 리스트 획득
		List<UnizVO> uList = unizMapper.getUnizListByKeywordOptList(keywordList, options);

		// 2. 유니즈 별 비디오리스트 획득
		List<ArrayList<VideoDataVO>> videoList = new ArrayList<>();

		for (UnizVO unizVO : uList) {
			List<VideoDataVO> vList = videoMapper.getVideoList(unizVO.getUnizSN());
			if (vList == null || vList.size() == 0) {
				videoList.clear();
				break;
			}
			videoList.add(new ArrayList<>(vList));
		}

		return videoList;
	}


	/**
	 * 
	 * 총 n개의 검색결과 리스트
	 * @param rawVideolist
	 * @return n개의 항목에 모두 존재하는 비디오 리스트만 정상적인 검색 결과
	 * 
	 */
	private ArrayList<VideoDataVO> getFilterIntersectionVideoList( List<ArrayList<VideoDataVO>> rawVideolist ) {

		ArrayList<VideoDataVO> validList = new ArrayList<>();

		for (ArrayList<VideoDataVO> videoList : rawVideolist) {
			if ( validList.size() == 0) {
				validList = videoList;
				continue;
			}
			validList = new ArrayList<>(
					validList.stream()
									.filter(x -> videoList.contains(x))
									.collect(Collectors.toList())
								);
		}

		return validList;
	}

	@Override
	public List<VideoDataListResult> getSearchResult(List<String> keywordList, List<UnizVO> searchUnizList) {

		List<VideoDataListResult> result = new ArrayList<>();

		// 1. 서치유니즈 별로 결과를 누적하자
		for (UnizVO searchUniz : searchUnizList) {

			// 2. 서치유니즈 값으로 그 검색옵션 타입 리스트를 획득 
			List<Integer> options = mapper.getUnizTypeFromUnizSN(searchUniz.getUnizSN());

			// 2. 서치유니즈 키워드와 옵션으로 검색
			// 2-a안 : DB에서 옵션 교집합 유니즈만 가져옴
			// 2-b안 : 다 가져온 후 로직에서 교집합 유니즈만 처리

			// 일단 2-b안 으로
			// 멀티 키워드도 일단은 IN 조건으로만
			List<ArrayList<VideoDataVO>> rawVideolist = getVideoListByOptions(keywordList, options);

			// 옵션 결과값 수와 옵션의 수가 안맞으면 검색실패 : 교집합 대상 옵션 중 하나의 검색 결과가 없는것, 검사하기도 전에 탈락
			if (rawVideolist.size() != options.size()) {
				continue;
			}

			// 3. 검색 결과 중 교집합 비디오 리스트 획득
			ArrayList<VideoDataVO> resultVideiList = getFilterIntersectionVideoList(rawVideolist);

			if (resultVideiList.size() > 0) {
				result.add(
						new VideoDataListResult(
								searchUniz.getUnizKeyword(),	// 검색 테마명
								searchUniz.getUnizSN(),			// 검색 유니즈
								resultVideiList.size(),			// 결과 개수
								resultVideiList					// 결과 비디오리스트
						)
					);
			}
		}

		return result;
	}

}
