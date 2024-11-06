package com.postgre.choongsam.dao;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.postgre.choongsam.dto.Lecture;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class AtrDaoImpl implements AtrDao {
	
	public final SqlSession session;

	@Override
	public String registerCourse(Lecture lecture) {
		System.out.println("AtrDao register Course");
		String courseId = "";
		try {
			session.insert("trRegisterCourse",lecture);
			courseId= lecture.getLctr_id();
			System.out.println("AtrDaoImpl registerCourse lecture->"+lecture);
		} catch (Exception e) {
			System.out.println("trRegisterCourse"+e.getMessage());
		}
		return courseId;
	}

	@Override
	public void registerSyllabus(String courseDetail, String parameter, int lctr_no) {
		try {
			session.insert("trRegisterSyllabus",Map.of("courseDetail",courseDetail,"parameter",parameter,"lctr_no",lctr_no));
		} catch (Exception e) {
			System.out.println("trRegisterSyllabus"+e.getMessage());
		}
		
	}

	@Override
	public List<Lecture> getAllLectureList() {
		List<Lecture> lectureList=null;
		try {
			lectureList=session.selectList("trGetAllLectureList");
		} catch (Exception e) {
			System.out.println("trGetAllLectureList"+e.getMessage());
		}
		return lectureList;
	}

	
	
}
