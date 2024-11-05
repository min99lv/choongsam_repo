package com.postgre.choongsam.service;

import org.springframework.stereotype.Service;

import com.postgre.choongsam.dao.AtrDao;
import com.postgre.choongsam.dto.Lecture;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class AtrServiceImpl implements AtrService {
	
	private final AtrDao ad;

	@Override
	public void registerCourse(Lecture lecture) {
		ad.registerCourse(lecture);

	}

}
