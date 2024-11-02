package com.postgre.choongsam.dao;

import java.util.List;

import com.postgre.choongsam.dto.Homework;

public interface JheDao {
	List<Homework>	profHomeworkList();
	int				insertHomework(Homework homework);
	Homework		findById(Homework homework);
	int				updateHomework(Homework homework);
	void			deleteHomework(int asmtNo);
}
