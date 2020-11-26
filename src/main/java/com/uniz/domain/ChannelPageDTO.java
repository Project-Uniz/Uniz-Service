package com.uniz.domain;


import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
@Getter
public class ChannelPageDTO {
	
	private int postCnt;
	private List<ChannelBoardVO> list;
	
}
