package com.postgre.choongsam.service;

import java.util.List;
import java.util.Map;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.postgre.choongsam.dao.HjhDao;
import com.postgre.choongsam.dto.Login_Info;
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

	@Override
	public int deleteStd1(String user_id, String password) {
	    int result = 0;

	    // 로그인 정보 조회
	    Login_Info loginInfo = hjhdao.getLoginInfo(user_id);
	    System.out.println("로그인 정보: " + loginInfo);
	    if (loginInfo != null) {
	        // 비밀번호 검증
	        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	        if (passwordEncoder.matches(password, loginInfo.getPassword())) {
	            // 비밀번호가 일치하면 삭제 (UPDATE 쿼리 실행)
	            result = hjhdao.deleteStd1(user_id, password);
	        } else {
	            // 비밀번호 불일치 시 처리
	            System.out.println("비밀번호가 일치하지 않습니다.");
	        }
	    } else {
	        // 로그인 정보가 없을 때 처리
	        System.out.println("로그인 정보가 없습니다.");
	    }

	    return result;
	}


	@Override
	public Login_Info getLoginInfo(String userId) {
		Login_Info getLoginInfo = hjhdao.getLoginInfo(userId);
		System.out.println("서비스"+getLoginInfo);
		return getLoginInfo;
	}

	@Override
	public int updateCountAdmin(User_Info info) {
		int updateCountAdmin = hjhdao.updateCountAdmin(info);
		return updateCountAdmin;
	}




}
