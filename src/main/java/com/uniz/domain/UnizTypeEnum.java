package com.uniz.domain;

import lombok.Getter;

@Getter
public enum UnizTypeEnum {
	NONE(0, "", "None"),
	TITLE(1, "제목", "Title"),
	AUTHOR(2, "게시자", "Author"),
	TEXT(3, "본문", "Text"),
	TAG(4, "해시태그", "Tag"),
	KIDS(5, "키즈", "Kids"),
	SHORT(6, "짧은영상", "Short"),
	LONG(7, "긴영상", "Long"),
	ETC1(8, "기타1", "Etc1"),
	ETC2(9, "기타2", "Etc2"),

	YOUTUBE(10, "유튜브카테고리", "Youtube"),
	MAINGROUP(11, "메인그룹", "MainGroup"),
	USERMADE(12, "유저생성유니즈", "UserMade"),
	COMMADE(13, "머신생성유니즈", "ComMade"),
	RECOMMEND(14, "추천", "Recommend"),
	SEARCH(15, "검색옵션", "SearchOpt"),
	HIT(16, "인기영상", "Hit"),
	;

	private final int typeSN;
	private final String typeNameKR;
	private final String typeNameEN;

	private UnizTypeEnum(int typeSN, String typeNameKR, String typeNameEN) {
		this.typeSN		= typeSN;
		this.typeNameKR	= typeNameKR;
		this.typeNameEN	= typeNameEN;
	}

	public static UnizTypeEnum valueOf( int typeSN ){
		UnizTypeEnum result = null;
		final UnizTypeEnum [] types = UnizTypeEnum.values();
		for( UnizTypeEnum type : types ){
			if( type.typeSN == typeSN ){
				result = type;
				break;
			}
		}

		return result;
	}
}
