package com.postgre.choongsam.dao;

import java.util.List;

import com.postgre.choongsam.dto.Lecture;

public interface AtrDao {

	String registerCourse(Lecture lecture);

	void registerSyllabus(String courseDetail, String parameter, int lctr_no);

	List<Lecture> getAllLectureList();

}
