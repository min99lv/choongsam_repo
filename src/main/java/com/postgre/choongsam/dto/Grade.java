package com.postgre.choongsam.dto;

import lombok.Data;

@Data
public class Grade { // 성적
	private String	lctr_id;	// 강의 번호
	private int		user_seq;	// 회원 번호 (학생)
	private int		atndc_scr;	// 출석 점수
	private int 		asmt_scr;	// 과제 점수
	private int		last_scr;	// 최종 성적
	private String	fnsh_yn;	// 수료 여부
}
