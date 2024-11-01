package com.postgre.choongsam.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.postgre.choongsam.dto.Homework;

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
		} catch (Exception e) {
			System.out.println("selectHW error: " + e.getMessage());
		}
		return HWList;
	}

	@Override
	@Transactional
	public int insertHomework(Homework homework) {
		System.out.println("과제등록 와따오");
		int insHWList = 0;
		try {
			insHWList = session.insert("insertHomework", homework);
			System.out.println("insHWList: " + insHWList);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("insertHomework error: " + e.getMessage());
			System.out.println("insHWList: " + insHWList);
			
		}
		return insHWList;
	}
}
