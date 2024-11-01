package com.postgre.choongsam.controller;




import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
		System.out.println("학생마이페이지");
	return "view_Hjh/adminPage";
	
	}
	@GetMapping("updateProfile")
	public String updateProfile(Model model) {
		
		return "view_Hjh/updateProfile";
	}

	@RequestMapping(value = "/updateProfileAdmin")
	public String updateProfileAdmin(Model model,User_Info user_Info) {
		List<User_Info> userList = hjh.userList();
		System.out.println("리스트내놔");
		model.addAttribute("userList",userList);
		return "view_Hjh/updateProfileAdmin";
	}


}
