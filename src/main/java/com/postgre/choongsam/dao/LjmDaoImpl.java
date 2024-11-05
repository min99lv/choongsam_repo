package com.postgre.choongsam.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.postgre.choongsam.dto.Login_Info;
import com.postgre.choongsam.dto.User_Info;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class LjmDaoImpl implements LjmDao {
	
	private final SqlSession session;
	private final PlatformTransactionManager transactionManager;
	
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
	public int signup(Login_Info login_Info, User_Info user_info) {
		System.out.println("LjmDaoImpl signup() start...");
		TransactionStatus txStatus= transactionManager.getTransaction(new DefaultTransactionDefinition());
		int signupResult = 0;
		
		try {
			signupResult = session.insert("loginSignup", login_Info);
			signupResult = session.insert("userSignup", user_info);
			
			transactionManager.commit(txStatus);
			
		} catch (Exception e) {
			System.out.println("LjmDaoImpl signup() Error ->" + e.getMessage());
			transactionManager.rollback(txStatus);
		}
		
		
		return signupResult;
	}
	
	// 아이디 중복 체크
	@Override
	public int confirmId(String user_id) {
		System.out.println("LjmDaoImpl confirmId() start...");
		int result = session.selectOne("confirmId", user_id);
		return result;
	}
	
	// 아이디 찾기
	@Override
	public String findId(User_Info user_info) {
		System.out.println("LjmDaoImpl findId() start...");

		String user_id = session.selectOne("findId", user_info);

		System.out.println("LjmDaoImpl findId() user_id -> " + user_id);

		return user_id;
	}
	
}
