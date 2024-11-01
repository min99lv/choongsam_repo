package com.postgre.choongsam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.postgre.choongsam.dao.JheDao;
import com.postgre.choongsam.dto.Homework;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JheServiceImpl implements JheService {

	@Autowired
	private final JheDao hed;

	@Override
	public List<Homework> profHomeworkList() {
		System.out.println("쒈비스 등장!");
		List<Homework> HWList = hed.profHomeworkList();
		return HWList;
	}

	@Override
	public int insertHomework(Homework homework) {
		System.out.println("과제 등록 서비스");
		int insHWList = hed.insertHomework(homework);
		return insHWList;
	}

	@Override
	public Homework findById(Homework homework) {
		System.out.println("수정할 과제 불러오는 서비스");
		Homework findByASMT = hed.findById(homework);
		return findByASMT;
	}

	@Override
	public int updateHomework(Homework homework) {
		System.out.println("등록할 과제 수정 서비스");
		int upHomeworkList = hed.updateHomework(homework);
		return upHomeworkList;
	}

	@Override
	public void deleteHomework(int asmtNo) {
		System.out.println("과제 삭제 서비스");
		hed.deleteHomework(asmtNo);
	}

	public void deleteHomeworkList(List<Integer> asmtNoList) {
		System.out.println("과제 삭제리스트 서비스");
		for (Integer asmtNo : asmtNoList) {
			deleteHomework(asmtNo);
		}
	}
}
