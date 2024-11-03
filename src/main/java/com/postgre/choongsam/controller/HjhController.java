package com.postgre.choongsam.controller;




import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.postgre.choongsam.dto.Paging;
import com.postgre.choongsam.dto.User_Info;
import com.postgre.choongsam.service.HjhService;

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
	public String myPageTeacher(Model model) {
		System.out.println("강사마이페이지");
	return "view_Hjh/myPageTeacher";
	
	}
	@RequestMapping(value = "/myPageStd")
	public String myPageStd(Model model) {
		System.out.println("학생마이페이지");
	return "view_Hjh/myPageStd";
	
	}
	@GetMapping("updateProfile")
	public String updateProfile(Model model) {
		
		return "view_Hjh/updateProfile";
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

	@GetMapping("detailProfile/{user_id}")
	public String detailProfile(@PathVariable("user_id") String user_id,Model model) {
		User_Info user_Info = hjh.detailProfile(user_id);
		model.addAttribute("user_Info",user_Info);
		return "view_Hjh/detailProfile";
		
	}


}
