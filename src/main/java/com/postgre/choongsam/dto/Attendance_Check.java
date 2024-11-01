package com.postgre.choongsam.dto;

import lombok.Data;

@Data
public class Attendance_Check {
	private	int		lctr_no;	// 차시
	private	String	lctr_id;		// 강의 번호
	private int		user_seq;	// 회원 번호 (학생)
	private	int		att_status;	// 출석 상태
}
