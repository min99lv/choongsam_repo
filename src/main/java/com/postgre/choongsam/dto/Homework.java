package com.postgre.choongsam.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class Homework extends Lecture { // 과제 
	private int		ASMT_NO;				// 과제 번호
	private String	LCTR_ID;					// 강의 번호
	private String	ASMT_NM;				// 과제 제목
	private	String	ASMT_CN;				// 과제 내용
	private	String	SBMSN_BGNG_YMD;	// 과제 제출 시작일
	private String	SBMSN_END_YMD;		// 과제 제출 마감일
	private int		FILE_GROUP;			// 파일 그룹
	private String	user_name;				// 회원 이름
}
