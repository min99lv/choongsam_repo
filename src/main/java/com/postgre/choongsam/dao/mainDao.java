package com.postgre.choongsam.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.postgre.choongsam.dto.Test01;
import com.postgre.choongsam.dto.User_info;

import lombok.RequiredArgsConstructor;


@Repository
@RequiredArgsConstructor
public class mainDao {
	
	private final SqlSession session;

	public User_info test() {
		
		User_info test = session.selectOne("com.postgre.choongsam_repo.mapper.test.selectTest");
		
		return test;
	}

}
