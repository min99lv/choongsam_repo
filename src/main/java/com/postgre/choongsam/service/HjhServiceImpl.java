package com.postgre.choongsam.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.postgre.choongsam.dao.HjhDao;
import com.postgre.choongsam.dto.User_Info;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HjhServiceImpl implements HjhService {
	private final HjhDao hjhdao;

	@Override
	public List<User_Info> userList(Map<String, Object> params) {
		List<User_Info> listteastd = hjhdao.teastdlist(params);
		return listteastd;
	}

	@Override
	public int totaluser(String keyword) {
		int totaluser = hjhdao.totaluser(keyword);
		return totaluser;
	}

	@Override
	public User_Info detailProfile(String user_id) {
		User_Info detailProfile = hjhdao.detailProfile(user_id);
		return detailProfile;
	}

	@Override
	public int userProfile1(User_Info info) {
		int userProfile1 = hjhdao.userProfile1(info);
		return userProfile1;
	}

	@Override
	public List<User_Info> profileupdate(String user_id) {
		List<User_Info> profileupdate = hjhdao.profileupdate(user_id);
		return profileupdate;
	}

	@Override
	public User_Info detailProfileuser(String userId) {
		User_Info detailProfileuser = hjhdao.detailProfileuser(userId);
		System.out.println("detailProfileuser service: " + detailProfileuser);
		return detailProfileuser;
	}




}
