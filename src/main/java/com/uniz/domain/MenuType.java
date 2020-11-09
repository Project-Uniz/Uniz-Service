package com.uniz.domain;

import lombok.Getter;

@Getter
public enum MenuType {
	NONE(0, "None", "None"),
	REG_LIKE(1, "가입좋아요", "like"),
	REG_DISLIKE(2, "가입싫어요", "dislike"),
	RECOMMEND(3, "추천", "recommend"),
	HIT(4, "인기", "Hit"),
	SEARCH(5, "검색", "search");

	private final int menu;
	private final String nameKR;
	private final String nameEN;

	private MenuType(int menu, String nameKR, String nameEN) {
		this.menu	= menu;
		this.nameKR	= nameKR;
		this.nameEN	= nameEN;
	}
}
