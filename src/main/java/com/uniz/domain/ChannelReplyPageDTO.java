package com.uniz.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
@Getter
public class ChannelReplyPageDTO {
	
	private int replyCnt;
	private List<ChannelReplyVO> list;
	
}
