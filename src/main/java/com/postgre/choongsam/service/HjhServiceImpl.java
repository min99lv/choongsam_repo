package com.postgre.choongsam.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.postgre.choongsam.dao.HjhDao;
import com.postgre.choongsam.dto.User_Info;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HjhServiceImpl implements HjhService {
	private final HjhDao hjhdao;

	@Override
	public List<User_Info> userList() {
		List<User_Info> listteastd = null;
		listteastd = hjhdao.teastdlist();

		
		return listteastd;
	}


}
