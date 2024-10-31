package com.postgre.choongsam.dto;

import lombok.Data;

@Data
public class Class_Bookmark {	// 영상 북마크
	private	String	conts_id;			// 영상 번호
	private	String	conts_chptime;	// 챕터 시간
	private String	conts_chpttl;		// 챕터 내용
	
	//챕터 북마크 버퍼 추가 -성희
	private	String	conts_chptime2;	// 챕터 시간
	private String	conts_chpttl2;		// 챕터 내용
	private	String	conts_chptime3;	// 챕터 시간
	private String	conts_chpttl3;		// 챕터 내용
}
