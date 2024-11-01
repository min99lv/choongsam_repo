package com.postgre.choongsam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.postgre.choongsam.dto.Login_Info;
import com.postgre.choongsam.service.LjmService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LjmController {
	
	private final LjmService ljs;	
	
		// 로그인 페이지 이동
		@GetMapping(value =  "view_Ljm/loginForm")
		public String showLoginPage() {
			return "view_Ljm/loginForm";
		}
		
		// 로그인 처리
		@RequestMapping(value = "view_Ljm/login")
		public String login(@RequestParam("user_id") String user_id,
							@RequestParam("password") String password, Model model,
							HttpSession session, HttpServletRequest request) {			
			System.out.println("로그인 컨트롤러 이동");
			
			System.out.println("user_id -> "+ user_id);
			Login_Info login_info = new Login_Info();
			login_info = ljs.login(user_id, password);
			
			System.out.println(user_id + "컨트롤러 로그인 성공");
			
			// 로그인 실패
			if (login_info == null) {
				System.out.println(user_id + "로그인 실패");
				model.addAttribute("loginError", "아이디 또는 비밀번호가 틀렸습니다.");
				model.addAttribute("user_id", user_id); // 입력한 아이디를 다시 전달
				System.err.println("로그인 실패");
				return "view_Ljm/loginForm"; 
			}
			
			// 기존 세션을 무효화하고 새로운 세션 ID 발급
			session.invalidate(); // 기존 세션을 무효화
			session = request.getSession(true); // 새로운 세션 생성

			// 사용자 정보를 세션에 저장
			session.setAttribute("user", login_info); // 새로운 세션에 사용자 정보 저장
			session.setMaxInactiveInterval(60 * 60); // 60분 동안 활동이 없으면 세션 만료 설정
			
			System.out.println("로그인 성공");
			return "redirect:/"; // 로그인 성공 시 리다이렉트

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
		
		// 아이디 중복체크
		
		
		// 회원가입 페이지 (가입완료) 이동
		@RequestMapping(value =  "view_Ljm/signup3")
		public String showSignupPage3() {
			return "view_Ljm/signup3";
		}
}
