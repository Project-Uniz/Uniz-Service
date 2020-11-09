package com.uniz.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideoDataListResult {
	private List<VideoDataVO> videoList;
	private int count;
	private UnizVO uniz;
}
