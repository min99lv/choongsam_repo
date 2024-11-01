package com.postgre.choongsam.service;

import java.util.List;

import com.postgre.choongsam.dto.Homework;

public interface JheService {
	List<Homework> selectHW();
	int insertHomework(Homework homework);
}
