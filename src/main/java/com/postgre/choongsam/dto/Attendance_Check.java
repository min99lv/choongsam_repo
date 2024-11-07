package com.postgre.choongsam.dto;

import lombok.Data;

@Data
public class Attendance_Check {
	private	int		lctr_no;			// 차시
	private	String	lctr_id;			// 강의 번호
	private int		user_seq;			// 회원 번호 (학생)
	private	int		att_status;			// 출석 상태
	private String	lctr_name;			// 강의명
	private int		reg_count;			// 수강인원
	private int		lctr_cntschd;		// 총 차시
	private int		present_count;		// 출석 수
	private int		late_count;			// 지각 수
	private int		absent_count;		// 결석 수
	private int		attendance_rate;	// 출석률
}
