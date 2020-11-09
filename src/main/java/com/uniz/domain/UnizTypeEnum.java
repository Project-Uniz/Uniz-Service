package com.uniz.domain;

import lombok.Getter;

@Getter
public enum UnizTypeEnum {
	NONE(0, "", "None"),
	YOUTUBE(1, "유튜브카테고리", "Youtube"),
	TITLE(2, "제목", "Title"),
	AUTHOR(3, "게시자", "Author"),
	HIT(4, "인기영상", "Hit"),
	KIDS(5, "키즈", "Family"),
	SHORT(6, "짧은영상", "Short"),
	LONG(7, "긴영상", "Long"),
	MAINGROUP(8, "메인그룹", "MainGroup"),
	USERMADE(9, "유저생성유니즈", "UserMade"),
	COMMADE(10, "머신생성유니즈", "ComMade");

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
