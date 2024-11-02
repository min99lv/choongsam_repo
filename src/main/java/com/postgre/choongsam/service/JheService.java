package com.postgre.choongsam.service;

import java.util.List;

import com.postgre.choongsam.dto.Homework;

public interface JheService {
	List<Homework>	profHomeworkList();
	int				insertHomework(Homework homework);
	Homework		findById(Homework homework);
	int				updateHomework(Homework homework);
	void			deleteHomework(int asmtNo);
}
