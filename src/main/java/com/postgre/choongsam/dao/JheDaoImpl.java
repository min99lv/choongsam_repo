package com.postgre.choongsam.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.postgre.choongsam.dto.Homework;
import com.postgre.choongsam.dto.User_Info;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class JheDaoImpl implements JheDao {

	@Autowired
	private final SqlSession session;

	@Override
	public List<Homework> selectHW() {
		System.out.println("따오");
		List<Homework> HWList = null;
		try {
			HWList = session.selectList("getAllHw",HWList);
			System.out.println("");
		} catch (Exception e) {
			System.out.println("HWList error: " + e.getMessage());
		}
		return HWList;
	}
}
