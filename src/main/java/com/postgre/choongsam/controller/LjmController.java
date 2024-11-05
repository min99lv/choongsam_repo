package com.postgre.choongsam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.postgre.choongsam.dto.Login_Info;
import com.postgre.choongsam.dto.User_Info;
import com.postgre.choongsam.service.LjmService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LjmController {
	
	private final LjmService ljs;	
	
		// 로그인 페이지 이동
		@RequestMapping(value =  "view_Ljm/loginForm")
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
				model.addAttribute("user_id", user_id);
				System.err.println("로그인 실패");
				return "view_Ljm/loginForm"; 
			}
			
			// 기존 세션 무효화 후 새로운 세션 생성		
			session.invalidate();
			session = request.getSession(true);

			// 회원 번호, 회원 아이디, 회원 분류를 세션에 저장
			session.setAttribute("user_seq", login_info.getUser_seq()); // 세션에 회원 번호 저장
			session.setAttribute("user", login_info.getUser_id()); // 세션에 회원 아이디 저장
			session.setAttribute("usertype", login_info.getUser_status()); // 세션에 회원 분류 저장
			

			session.setMaxInactiveInterval(60 * 60); // 60분 동안 활동이 없으면 세션 만료
			
			System.out.println("로그인 성공");
			return "redirect:/"; // 로그인 성공 시 리다이렉트

		}
		
		// 로그아웃
		@GetMapping(value = "/logout")
		public String logout(HttpSession session, Model model) {
			// 세션 해제
			session.invalidate();
			System.out.println("로그아웃");
			return "redirect:/";
		}
		
		// 아이디 찾기 페이지 이동
		@GetMapping(value =  "view_Ljm/findId")
		public String findIdPage() {
			return "view_Ljm/findId";
		}
		
		// 아이디 찾기
		@RequestMapping(value = "view_Ljm/findIdResult")
		public String findId(@RequestParam("user_name") String user_name, @RequestParam("email") String email, Model model) {
			System.out.println("LjmController findId() start....");
			
			System.out.println("LjmController findId() user_name -> " + user_name);
			System.out.println("LjmController findId() email -> " + email);
			
			model.addAttribute("user_name", user_name);
		    model.addAttribute("email", email);
		    
			User_Info user_info = new User_Info();
			user_info.setUser_name(user_name);
			user_info.setEmail(email);
			
			String user_id = ljs.findId(user_info);
			
			if (user_id != null) {
				model.addAttribute("find_id", user_id);
				model.addAttribute("userCheckMessage", "회원님의 정보로 가입된 아이디는 아래와 같습니다.");
			} else {
				model.addAttribute("userCheckMessage", "입력하신 회원 정보와 일치하는 아이디가 존재하지 않습니다.");
			}
			
			return "view_Ljm/findIdResult";			
		}
		
		// 비밀번호 찾기 페이지 이동
		@GetMapping(value =  "view_Ljm/findPw")
		public String findPwPage() {
			return "view_Ljm/findPw";
		}
		
		// 비밀번호 찾기
		@RequestMapping(value = "view_Ljm/findPwResult")
		public String findPw(@ModelAttribute User_Info user_info, Model model) {
			System.out.println("LjmController findPw() start...");
			System.out.println("LjmController findPw() user_name -> " + user_info.getUser_id());
			System.out.println("LjmController findPw() email -> " + user_info.getEmail());
			
			
			return "view_Ljm/findPwResult";
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
		
		// 아이디 중복 체크
		@GetMapping(value = "view_Ljm/confirmId")
		@ResponseBody // JSON 응답을 위해 추가
		public int confirmId(@RequestParam("user_id") String user_id) {
			System.out.println("LjmController confirmId() start...");
			int result = ljs.confirmId(user_id);
			return result;
		}
		
		// 회원가입 처리
		@RequestMapping(value = "view_Ljm/signup")
		public String signup(@ModelAttribute Login_Info login_Info, User_Info user_info, Model model) {
			System.out.println(" signup Start...");
			
			int signupResult = ljs.signup(login_Info, user_info);
			System.out.println("LjmController signupResult -> " + signupResult);
			
			if (signupResult > 0) {
				return "view_Ljm/signup3";
			} else {
				model.addAttribute("msg", "회원가입에 실패하였습니다.");
				return "main";
			}
			
			
		}
}
