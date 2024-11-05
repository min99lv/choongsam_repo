package com.postgre.choongsam.dao;


import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.postgre.choongsam.dto.Lecture;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class AtrDaoImpl implements AtrDao {
	
	public final SqlSession session;

	@Override
	public void registerCourse(Lecture lecture) {
		System.out.println("AtrDao register Course");
		try {
			session.insert("trRegisterCourse",lecture);
		} catch (Exception e) {
			System.out.println("trRegisterCourse"+e.getMessage());
		}
		
	}

	
	
}
