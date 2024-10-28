package com.postgre.choongsam.service;

import org.springframework.stereotype.Service;

import com.postgre.choongsam.dao.mainDao;
import com.postgre.choongsam.dto.Test01;
import com.postgre.choongsam.dto.User_info;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class mainService {

	private final mainDao md;
	
	public User_info test() {
		User_info test = md.test();
		
		return test;
		
	}
}
