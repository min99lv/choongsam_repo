package com.postgre.choongsam.dto;

import lombok.Data;

@Data
public class Lecture { // 강의 정보
	
	private String 		lctr_id;				// 강의번호
	private int 			user_seq;			// 회원번호(강사)
	private String 		onoff;				// 대면 여부
	private String 		lctr_category;		// 강의구분명
	private String 		lctr_name;			// 강의명
	private int 			lctr_count;			// 모집인원수
	private String 		rcrt_date;			// 모집시작일
	private int 			lctr_cost;			// 수강료
	private String 		lctr_state;			// 강의상태명
	private String 		eval_criteria;		// 평가기준
	private String 		lctr_start_date;	// 개강일
	private int 			lctr_cntschd;		// 총 차시
	private int 			file_group;			// 파일 그룹
	private String 		lctr_schd;			// 스케쥴

}