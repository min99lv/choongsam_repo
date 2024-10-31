package com.postgre.choongsam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LjmController {
	// 로그인 페이지 이동
			@GetMapping(value =  "view_Ljm/login")
		    public String showLoginPage() {
		        return "view_Ljm/login"; // JSP 파일 이름 (login.jsp)
		    }
			
			// 회원가입 페이지 이동
			@GetMapping(value =  "view_Ljm/signup1")
		    public String showSignupPage1() {
		        return "view_Ljm/signup1"; // JSP 파일 이름 (login.jsp)
		    }
			
			@GetMapping(value =  "view_Ljm/signup2")
		    public String showSignupPage2() {
		        return "view_Ljm/signup2"; // JSP 파일 이름 (login.jsp)
		    }
			
			@GetMapping(value =  "view_Ljm/signup3")
		    public String showSignupPage3() {
		        return "view_Ljm/signup3"; // JSP 파일 이름 (login.jsp)
		    }
}
