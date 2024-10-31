package com.postgre.choongsam.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.postgre.choongsam.dto.User_Info;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class HjhDaoImpl implements HjhDao {
	private final SqlSession session;

	@Override
	public List<User_Info> teastdlist() {
		List<User_Info> listteastd = null;
		try {
			listteastd = session.selectList("teastdlist");
		} catch (Exception e) {
		System.out.println("다오왔냐");
		}
		return listteastd;
	}
}
