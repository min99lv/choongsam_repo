package com.postgre.choongsam.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.postgre.choongsam.dto.User_Info;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class HjhDaoImpl implements HjhDao {
	private final SqlSession session;

	@Override
	public List<User_Info> teastdlist(Map<String, Object> params) {
		List<User_Info> listteastd = null;
		try {
			listteastd = session.selectList("teastdlist",params);
		} catch (Exception e) {
		System.out.println("다오왔냐");
		}
		return listteastd;
	}

	@Override
	public int totaluser(String keyword) {
		int totalusercount = 0;
		try {
			totalusercount = session.selectOne("totalusercount",keyword);
		} catch (Exception e) {
			
		}
		return totalusercount;
	}

	@Override
	public User_Info detailProfile(String user_id) {
		User_Info detailProfile = null;
		Map<String, Object> params = new HashMap<>();
		params.put(user_id, user_id);
		detailProfile = session.selectOne("detailProfile",params);
		
		return detailProfile;
	}
}
