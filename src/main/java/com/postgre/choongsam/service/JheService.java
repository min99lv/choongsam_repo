package com.postgre.choongsam.service;

import java.util.List;

import com.postgre.choongsam.dto.Homework;
import com.postgre.choongsam.dto.Homework_Submission;
import com.postgre.choongsam.dto.Lecture;


public interface JheService {
	List<Homework>	getLectureHomeworkList();
	List<Homework>	getProfHomeworkList(String LCTR_ID);
	Lecture			findByLCTR(String LCTR_ID);
	int				insertHomework(Homework homework);
	Homework		findById(int ASMT_NO);
	int				updateHomework(Homework homework);
	void			deleteHomeworkList(List<Integer> delCheck);
	List<Homework>	getStudHomeworkList(int USER_SEQ);
	void			notifyStudents(String lctr_ID, int asmt_no);
	int				updatesubmitHomework(Homework homework);
}
