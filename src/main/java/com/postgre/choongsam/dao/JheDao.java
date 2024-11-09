package com.postgre.choongsam.dao;

import java.util.List;

import com.postgre.choongsam.dto.Attendance_Check;
import com.postgre.choongsam.dto.File_Group;
import com.postgre.choongsam.dto.Grade;
import com.postgre.choongsam.dto.Homework;
import com.postgre.choongsam.dto.Homework_Submission;
import com.postgre.choongsam.dto.Lecture;

public interface JheDao {
	List<Lecture>	getLectureHomeworkList(int user_seq);
	List<Lecture>	getProfLectureInfo(String lctr_id);
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
	List<Attendance_Check> profAttMain(String lctr_id);
	List<Attendance_Check> getStudAtt(String lctr_id, int lctr_no);
	List<Attendance_Check> getOnlineStudAtt(String lctr_id);
	int				updateStudAtt(Attendance_Check attendance_Check);
	int upStudOnlineAtt(Attendance_Check attendance_Check);
	List<Lecture>	studLecture(int user_seq);
	List<Lecture>	studLectureMain(String lctr_id);
	List<Attendance_Check> studAtt(Attendance_Check attendance_Check);
	List<Attendance_Check> profAttDetail(Attendance_Check attendance_Check);
	List<Grade>		profGrade(String lctr_id);
	void			insertGrade(Grade grade);
}
