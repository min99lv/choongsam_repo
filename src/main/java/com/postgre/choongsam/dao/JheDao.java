package com.postgre.choongsam.dao;

import java.util.List;

import com.postgre.choongsam.dto.File_Group;
import com.postgre.choongsam.dto.Homework;
import com.postgre.choongsam.dto.Homework_Submission;
import com.postgre.choongsam.dto.Lecture;

public interface JheDao {
	List<Homework>	getLectureHomeworkList();
	List<Homework>	getProfHomeworkList(String lctr_id);
	Lecture			findByLCTR(String lctr_id);
	int				insertHomework(Homework homework);
	Homework		findById(int asmt_no);
	int				updateHomework(Homework homework);
	void			deleteHomeworkSubmission(int asmt_no);
	void			deleteHomework(int asmt_no);
	List<Homework>	getStudHomeworkList(int user_seq);
	List<Homework>	notifyStudents(String lctr_id);
	int				insSubmission(Homework_Submission submission);
	int				updatesubmitHomework(Homework_Submission homework_Submission);
	int				getTotStudInCourse(String lctr_id);
	int				getSubmittedStuds(int asmt_no);
	void			insertFile(File_Group file_Group);
	List<Homework>	getStudSubmitList(String lctr_id);
}
