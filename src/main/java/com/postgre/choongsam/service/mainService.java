package com.postgre.choongsam.service;

import org.springframework.stereotype.Service;

import com.postgre.choongsam.dao.mainDao;
import com.postgre.choongsam.dto.User_Info;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class mainService {

	private final mainDao md;
	
	public User_Info test() {
		User_Info test = md.test();
		
		return test;
		
	}
}
