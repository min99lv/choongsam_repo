package com.postgre.choongsam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LjmController {
		// 로그인 페이지 이동
		@GetMapping(value =  "view_Ljm/login")
		public String showLoginPage() {
			return "view_Ljm/login";
		}
		
		// 아이디 찾기 페이지 이동
		@GetMapping(value =  "view_Ljm/findId")
		public String findIdPage() {
			return "view_Ljm/findId";
		}
		
		// 비밀번호 찾기 페이지 이동
		@GetMapping(value =  "view_Ljm/findPw")
		public String findPwPage() {
			return "view_Ljm/findPw";
		}
			
		// 회원가입 페이지 (이용약관) 이동
		@GetMapping(value =  "view_Ljm/signup1")
		public String showSignupPage1() {
			return "view_Ljm/signup1";
		}
		
		// 회원가입 페이지 (정보입력) 이동
		@GetMapping(value =  "view_Ljm/signup2")
		public String showSignupPage2() {
			return "view_Ljm/signup2";
		}
		
		// 회원가입 페이지 (가입완료) 이동
		@GetMapping(value =  "view_Ljm/signup3")
		public String showSignupPage3() {
			return "view_Ljm/signup3";
		}
}
