package com.postgre.choongsam.controller;




import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.postgre.choongsam.dto.Paging;
import com.postgre.choongsam.dto.User_Info;
import com.postgre.choongsam.service.HjhService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping(value = "view_Hjh")
@RequiredArgsConstructor
public class HjhController {
	
	private final HjhService hjh;
	
	@RequestMapping(value = "/adminPage")
	public String adminPage(Model model) {
		System.out.println("관리자마이페이지");
	return "view_Hjh/adminPage";
	
	}
	@RequestMapping(value = "/myPageTeacher")
	public String myPageTeacher(HttpSession session) {
		
		
		System.out.println("강사마이페이지");
		System.out.println(session.getAttribute("user"));
		
	return "view_Hjh/myPageTeacher";
	}
	
	@RequestMapping(value = "/myPageStd")
	public String myPageStd(HttpSession session) {
		System.out.println("학생마이페이지");
		System.out.println(session.getAttribute("user"));
	return "view_Hjh/myPageStd";
	}
	
	//학생 개인정보수정
	@GetMapping("updateProfile")
	public String updateProfile(HttpSession session, Model model, @RequestParam String user) {
	    // 세션에서 user_id 가져오기
	    String userId = (String) session.getAttribute("user");
	    
	    // userId를 사용하여 필요한 로직 추가
	    System.out.println("user: " + user);
	    System.out.println("user_id from session: " + userId);
	    
	    // 사용자 정보 가져오기
	    User_Info userInfo = hjh.detailProfileuser(userId);
	    
	    // 사용자 정보 확인
	    if (userInfo != null) {
	        System.out.println("User Info retrieved in controller: " + userInfo);
	        
	        // 모델에 사용자 정보 추가
	        model.addAttribute("user_seq", userInfo.getUser_seq());
	        model.addAttribute("user_id", userInfo.getUser_id());
	        model.addAttribute("user_name", userInfo.getUser_name());
	        model.addAttribute("birth", userInfo.getBirth());
	        model.addAttribute("profile_name", userInfo.getProfile_name());
	        model.addAttribute("profile_addr", userInfo.getProfile_addr());
	        model.addAttribute("address", userInfo.getAddress());
	        model.addAttribute("email", userInfo.getEmail());
	        model.addAttribute("phone_num", userInfo.getPhone_num());
	    } else {
	        System.out.println("No user info found for userId: " + userId);
	    }

	    model.addAttribute("userId", userId);
	    
	    return "view_Hjh/updateProfile"; // 뷰 이름 반환
	}



	@GetMapping("updateProfileteacher")
	public String updateProfileteacher(Model model) {
		
		return "view_Hjh/updateProfileteacher";
	}
	
	@RequestMapping(value = "/updateProfileAdmin")
	public String updateProfileAdmin(Model model,
			@RequestParam(value = "currentPage", defaultValue = "1") String currentPage,
			@RequestParam(value = "keyword", required = false) String keyword
			)  {
		
		int totaluser = hjh.totaluser(keyword);
		System.out.println("리스트내놔");
		Paging page = new Paging(totaluser,currentPage);
		Map<String, Object> params = new HashMap<>();
		params.put("start", page.getStart());
		params.put("rowPage", page.getRowPage());
		params.put("keyword", keyword);
		List<User_Info> userList = hjh.userList(params);
		
		model.addAttribute("page",page);
		model.addAttribute("totaluser",totaluser);
		model.addAttribute("userList",userList);
		return "view_Hjh/updateProfileAdmin";
	}
	//리스트에서 수정창나와서 수정
	/*
	 * @RequestMapping("detailProfile/{user_id}") public String
	 * detailProfile(@PathVariable("user_id") String user_id, Model model) {
	 * User_Info user_Info = hjh.detailProfile(user_id);
	 * System.out.println("detailProfile");
	 * 
	 * if (user_Info != null) { model.addAttribute("user_seq",
	 * user_Info.getUser_seq()); model.addAttribute("user_id",
	 * user_Info.getUser_id()); model.addAttribute("user_name",
	 * user_Info.getUser_name()); model.addAttribute("email", user_Info.getEmail());
	 * model.addAttribute("address", user_Info.getAddress());
	 * model.addAttribute("phone_num", user_Info.getPhone_num()); } else { // 사용자
	 * 정보를 찾지 못한 경우 처리
	 * 
	 * model.addAttribute("error", "사용자 정보를 찾을 수 없습니다."); }
	 * 
	 * return "view_Hjh/detailProfile"; // JSP 파일 이름 } //관리자 회원정보수정
	 * 
	 * @RequestMapping("/view_Hjh/detailProfile/{user_id}") public String
	 * userProfile1(@ModelAttribute User_Info info) {
	 * System.out.println("updateCount"); int updateCount = hjh.userProfile1(info);
	 * // 수정 작업 수행 System.out.println("updateCount"+updateCount); // 수정 결과에 따라 다르게
	 * 처리할 수 있습니다. return "redirect:/view_Hjh/updateProfileAdmin"; }
	 */
	
	@RequestMapping("updateUserInfo")
	public String updateUserInfo(@ModelAttribute User_Info info) {
		System.out.println("updateCount");
	    int updateCount = hjh.userProfile1(info); // 수정 작업 수행
	    System.out.println("updateCount"+updateCount);
	    // 수정 결과에 따라 다르게 처리할 수 있습니다.
	    return "redirect:/view_Hjh/myPageStd";
	}

	

	
	
	


	}
	
	
