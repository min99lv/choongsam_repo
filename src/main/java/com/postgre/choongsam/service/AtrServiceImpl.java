package com.postgre.choongsam.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.postgre.choongsam.dao.AtrDao;
import com.postgre.choongsam.dto.Lecture;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class AtrServiceImpl implements AtrService {
	
	private final AtrDao ad;

	@Override
	public String registerCourse(Lecture lecture) {
		String courseId = ad.registerCourse(lecture);
		return courseId;

	}

	@Override
	public void registerSyllabus(String courseDetail, String parameter,int lctr_no) {
		ad.registerSyllabus(courseDetail,parameter, lctr_no);
		
	}

	@Override
	public List<Lecture> getAllLectureList() {
		List <Lecture> lectureList= ad. getAllLectureList();
		return lectureList;
	}

}
