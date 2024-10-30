package com.postgre.choongsam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.postgre.choongsam.dao.JheDao;
import com.postgre.choongsam.dto.Homework;
import com.postgre.choongsam.dto.User_Info;

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
}
