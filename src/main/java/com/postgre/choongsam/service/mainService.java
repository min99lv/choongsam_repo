package com.postgre.choongsam.service;

import org.springframework.stereotype.Service;

import com.postgre.choongsam.dao.mainDao;
import com.postgre.choongsam.dto.Test01;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class mainService {

	private final mainDao md;
	
	public   Test01 test() {
		Test01 test = md.test();
		
		return test;
		
	}
}
