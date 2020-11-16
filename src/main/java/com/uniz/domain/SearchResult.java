package com.uniz.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SearchResult {
	List<String> keyword;
	List<VideoDataListResult> result;
}
