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
	public List<Homework> profHomeworkList() {
		System.out.println("따오");
		List<Homework> HWList = null;
		try {
			HWList = session.selectList("profHomeworkList");
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

	@Override
	public Homework findById(Homework homework) {
		System.out.println("과제 수정 이따오");
		Homework findByASMT = null;
		try {
			findByASMT = session.selectOne("findById", homework);
		} catch (Exception e) {
			System.out.println("findById error: " + e.getMessage());
		}
		return findByASMT;
	}

	@Override
	public int updateHomework(Homework homework) {
		System.out.println("과제 수정 해따오");
		int upHomeworkList = 0;
		try {
			upHomeworkList = session.update("updateHomework", homework);
		} catch (Exception e) {
			System.out.println("updateHomework error: " + e.getMessage());
		}
		return upHomeworkList;
	}

	@Override
	public void deleteHomework(int asmtNo) {
		System.out.println("과제삭제 와따오");
		session.delete("delHomework", asmtNo);
	}

	public void deleteHomeworkList(List<Integer> asmtNoList) {
		System.out.println("과제삭제 리스트여따오");
		for (Integer asmtNo : asmtNoList) {
			deleteHomework(asmtNo);
		}
	}
}
