package com.postgre.choongsam.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.postgre.choongsam.dao.LjmDao;
import com.postgre.choongsam.dto.Login_Info;
import com.postgre.choongsam.dto.User_Info;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class LjmServiceImpl implements LjmService {
	
	private final LjmDao ljd;
	private final BCryptPasswordEncoder passwordEncoder;
	
	// 로그인 처리
    @Override
    public Login_Info login(String user_id, String password) {
        System.out.println("LjmServiceImpl login() start....");

        // 사용자 정보를 데이터베이스에서 조회
        Login_Info login_Info = ljd.login(user_id);
        
        // 로그인 실패 처리
        if (login_Info == null) {
            System.out.println("사용자를 찾을 수 없습니다.");
            return null; // 사용자 ID가 없으면 null 반환
        }

        // 비밀번호 확인
        if (passwordEncoder.matches(password, login_Info.getPassword())) {
            System.out.println(user_id + " 로그인 성공");
            return login_Info; // 로그인 성공
        } else {
            System.out.println("비밀번호가 일치하지 않습니다.");
            return null; // 비밀번호가 일치하지 않으면 null 반환
        }
    }
    
    // 회원가입
	@Override
	public int signup(Login_Info login_Info, User_Info user_info) {
		System.out.println("LjmServiceImpl signup() start....");
		
		String encodePassword = passwordEncoder.encode(login_Info.getPassword());
		login_Info.setPassword(encodePassword);
		int signupResult = ljd.signup(login_Info, user_info);
		System.out.println("LjmServiceImpl signupResult -> " + signupResult);
		
		return signupResult;
	}
	
	// 아이디 중복 체크
	@Override
	public int confirmId(String user_id) {
		System.out.println("LjmServiceImpl confirmId() start....");
		int result = ljd.confirmId(user_id);
		System.out.println("LjmServiceImpl confirmId() result -> " + result);
		return result;
	}
	
	// 아이디 찾기
	@Override
	public String findId(User_Info user_info) {
		System.out.println("LjmServiceImpl findId() start...");
		System.out.println("LjmServiceImpl findId() user_name -> " + user_info.getUser_name());
		System.out.println("LjmServiceImpl findId() user_email -> " + user_info.getEmail());

		String user_id = ljd.findId(user_info);

		System.out.println("LjmServiceImpl findId() user_id -> " + user_id);

		return user_id;
	}

	@Override
	public String getUserName(int user_seq) {
		String user_name = ljd.getUserName(user_seq);
		return user_name;
	}

}
