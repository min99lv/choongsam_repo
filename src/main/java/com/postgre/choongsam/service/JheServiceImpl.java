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
	public List<Homework> selectHW() {
		System.out.println("쒈비스 등장!");
		List<Homework> HWList = hed.selectHW();
		return HWList;
	}

	@Override
	public int insertHomework(Homework homework) {
		System.out.println("과제 등록 서비스");
		int insHWList = hed.insertHomework(homework);
		return insHWList;
	}
}
