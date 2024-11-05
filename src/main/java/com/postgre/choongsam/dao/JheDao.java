package com.postgre.choongsam.dao;

import java.util.List;

import com.postgre.choongsam.dto.Homework;
import com.postgre.choongsam.dto.Homework_Submission;
import com.postgre.choongsam.dto.Lecture;

public interface JheDao {
	List<Homework>	getLectureHomeworkList();
	List<Homework>	getProfHomeworkList(String LCTR_ID);
	Lecture			findByLCTR(String LCTR_ID);
	int				insertHomework(Homework homework);
	Homework		findById(int ASMT_NO);
	int				updateHomework(Homework homework);
	void			deleteHomeworkSubmission(int asmtNo);
	void			deleteHomework(int asmtNo);
	List<Homework>	getStudHomeworkList(int USER_SEQ);
	List<Homework>	notifyStudents(String lctr_ID);
	int				insSubmission(Homework_Submission submission);
	int				updatesubmitHomework(Homework_Submission homework_Submission);
	int				getTotStudInCourse(String lctr_id);
	int				getSubmittedStuds(int ASMT_NO);
}
