package com.uniz.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideoDataListResult {
	private String group;
	private Long unizSN;
	private int count;
	private List<VideoDataVO> videoList;
}
