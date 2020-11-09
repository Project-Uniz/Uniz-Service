package com.uniz.service;

public interface SearchService {

	public Integer getOptions(Long userSN);

	public boolean makeOptions(Long userSN, int options);

	public boolean setOptions(Long userSN, int options);

}
