package com.postgre.choongsam.service;

import java.util.List;

import com.postgre.choongsam.dto.Lecture;

public interface AtrService {

	String registerCourse(Lecture lecture);

	void registerSyllabus(String courseDetail, String parameter, int i);

	List<Lecture> getAllLectureList();

}
