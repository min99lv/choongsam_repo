package com.postgre.choongsam.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.postgre.choongsam.dao.AtrDao;
import com.postgre.choongsam.dto.Classroom;
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

	@Override
	public Lecture getLectureDetail(String lctr_id) {
		Lecture lecture = new Lecture();
		lecture=ad.getLectureDetail(lctr_id);
		return lecture;
	}



	@Override
	public boolean overlapCheck(String schd,  String lctr_room) {
		boolean result =ad.overlapCheck(schd,lctr_room);
		return result;
	}

	@Override
	public void addClassRoom(String parameter, String parameter2, String parameter3) {
		ad.addClassRoom(parameter, parameter2,parameter3);
		
	}

	@Override
	public List<Classroom> getAllClassRoom() {
		List<Classroom> classroomList=ad.getAllClassRoom();
		return classroomList;
	}

	@Override
	public void approveCourse(String parameter) {
		ad.approveCourse(parameter);
	}

	@Override
	public List<Lecture> getRecruitLectureList() {
		List <Lecture> lectureList= ad. getRecruitLectureList();
		return lectureList;
	}

	@Override
	public void applyCourse(String parameter, String parameter2) {
		ad.applyCourse(parameter,parameter2);
		
	}

}
