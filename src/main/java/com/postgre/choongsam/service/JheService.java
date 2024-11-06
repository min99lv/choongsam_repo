package com.postgre.choongsam.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.postgre.choongsam.dto.Attendance_Check;
import com.postgre.choongsam.dto.Homework;
import com.postgre.choongsam.dto.Lecture;


public interface JheService {
	List<Homework>	getLectureHomeworkList();
	List<Homework>	getProfHomeworkList(String lctr_id);
	Lecture			findByLCTR(String lctr_id);
	int				insertHomework(Homework homework, MultipartFile file);
	Homework		findById(int asmt_no);
	int				updateHomework(Homework homework, MultipartFile file);
	void			deleteHomeworkList(List<Integer> delCheck);
	List<Homework>	getStudHomeworkList(int user_seq);
	void			notifyStudents(String lctr_id);
	int				updatesubmitHomework(Homework homework);
	List<Attendance_Check> studOfflineAtt(int user_seq);
	List<Homework>	getStudSubmitList(String lctr_id);
}
