package com.postgre.choongsam.dto;

import lombok.Data;

@Data
public class Homework { // 과제 
	private String	asmt_no;		// 과제 번호
	private String	lctr_id;		// 강의 번호
	private String	asmt_nm;		// 과제 제목
	private	String	asmt_cn;		// 과제 내용
	private	String	sbmsn_bgng_ymd;	// 과제 제출 시작일
	private String	sbmsn_end_ymd;	// 과제 제출 마감일
	private int		file_group;		// 파일 그룹
}
