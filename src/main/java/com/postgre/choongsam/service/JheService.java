package com.postgre.choongsam.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.postgre.choongsam.dto.Attendance_Check;
import com.postgre.choongsam.dto.Homework;
import com.postgre.choongsam.dto.Lecture;


public interface JheService {
	List<Homework>	getLectureHomeworkList();
	List<Lecture>	getProfLectureInfo(String lctr_id);
	List<Homework>	getProfHomeworkList(String lctr_id);
	List<Homework>	getStudSubmitList(String lctr_id);
	Lecture			findByLCTR(String lctr_id);
	int				insertHomework(Homework homework, MultipartFile file);
	Homework		findById(int asmt_no);
	int				updateHomework(Homework homework, MultipartFile file);
	void			deleteHomeworkList(List<Integer> delCheck);
	List<Homework>	getStudHomeworkList(int user_seq);
	void			notifyStudents(String lctr_id);
	List<Attendance_Check> profAttMain(String lctr_id);
	List<Attendance_Check> getStudAtt(String lctr_id, int lctr_no);
	List<Attendance_Check> getOnlineStudAtt(String lctr_id);
	int				updatesubmitHomework(Homework homework, int user_seq);
	void			updateStudAtt(String lctr_id, int lctr_no, List<Integer> user_seq, Map<String, String> att_status, int onoff);
}
