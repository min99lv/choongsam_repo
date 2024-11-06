package com.postgre.choongsam.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class Homework extends Lecture { // 과제 
	private int		asmt_no;		// 과제 번호
	private String	lctr_id;		// 강의 번호
	private String	asmt_nm;		// 과제 제목
	private	String	asmt_cn;		// 과제 내용
	private	String	sbmsn_bgng_ymd;	// 과제 제출 시작일
	private String	sbmsn_end_ymd;	// 과제 제출 마감일
	private int		file_group;		// 파일 그룹

	private String	user_name;		// 회원 이름
	private String	prof_name;		// 강사명
	private String	stud_name;		// 학생명
    private int    	user_status;	// 회원 분류
    private String	asmtStatus;		// 과제 등록 상태
    private String 	user_id;		// 회원 아이디
    private int		user_seq;		// 회원 번호
    private String	sbmsn_yn;		// 과제 제출 여부
    private int		submissionRate; // 제출률
    private String	fileName;		// 파일명
}
