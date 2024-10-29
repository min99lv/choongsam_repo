package com.postgre.choongsam.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.postgre.choongsam.dto.User_Info;

import lombok.RequiredArgsConstructor;


@Repository
@RequiredArgsConstructor
public class mainDao {
	
	private final SqlSession session;

	public User_Info test() {
		
		User_Info test = session.selectOne("com.postgre.choongsam.mapper.test.selectTest");
		
		return test;
	}

}
