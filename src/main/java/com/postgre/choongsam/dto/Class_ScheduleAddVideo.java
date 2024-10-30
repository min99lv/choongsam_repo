package com.postgre.choongsam.dto;

import lombok.Data;

@Data
public class Class_ScheduleAddVideo {
	private	int		lctr_no;		// 차시
	private	String	lctr_id;			// 강의 번호
	private	String	conts_id;		// 영상 번호
	private int		user_seq;		// 학생 번호
	private	String	conts_final;	// FINAL 위치
	private	String	conts_max;	// MAX 위치
	private int		conts_prgrt;	// 강의진행율
	
	private	String	vdo_file_nm;	// 영상 제목
	private	String	vdo_url_addr;	// 영상 URL 주소
	private String	viewing_period;	// 시청 가능 기간
	private	String	vdo_length;		// 영상 길이
	private int		file_group;		// 파일 그룹
	
	private String 		onoff;				// 온오프라인 여부
	private String 		lctr_category;		// 강의구분명
	private String 		lctr_name;			// 강의명
	private int 		lctr_count;			// 모집인원수
	private String 		rcrt_date;			// 모집시작일
	private int 		lctr_cost;			// 수강료
	private String 		lctr_state;			// 강의상태명
	private String 		eval_criteria;		// 평가기준
	private String 		lctr_start_date;	// 개강일
	private int 		lctr_date;			// 수업일수 
	private int 		lctr_cntschd;		// 총 차시
	
	private String 	user_id;			//아이디
	private String 	user_name;		//이름
	private String 	birth;				//생년월일
	private String 	profile_name;		//증명사진명
	private String 	profile_addr;		//증명사진명로
	private String 	address;			//주소
	private String 	email;				//메일
	private String 	phone_num;		//전화번호
	private String 	del_state;			//삭제여부
    private int    	user_status;     	//회원분류
}
