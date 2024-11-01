package com.postgre.choongsam.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.postgre.choongsam.dto.Login_Info;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class LjmDaoImpl implements LjmDao {
	
	private final SqlSession session;
	
	// 로그인 처리
	@Override
	public Login_Info login(String user_id) {
		System.out.println("LjmDaoImpl login() start...");
		
		Login_Info login_info = new Login_Info();
		
		try {
			login_info.setUser_id(user_id);			
			login_info = session.selectOne("login", user_id);
			
		} catch (Exception e) {
			System.out.println("LjmDaoImpl login() Error ->" + e.getMessage() );
		}
		return login_info;
	}

	// 회원가입
	@Override
	public int signup(Login_Info login_Info) {
		System.out.println("LjmDaoImpl signup() start...");
		int signupResult = session.insert("signup", login_Info);
		return signupResult;
	}
	
}
