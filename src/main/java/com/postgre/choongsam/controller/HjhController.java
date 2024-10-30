package com.postgre.choongsam.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.postgre.choongsam.service.HjhService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping(value = "view_Hjh")
@RequiredArgsConstructor
public class HjhController {
	
	private final HjhService hjh;
	
	@RequestMapping(value = "/myPageStd")
	public String myPageStd(Model model) {
		System.out.println("학생마이페이지");
	return "view_Hjh/myPageStd";
	
	}
	@GetMapping("updateProfile")
	public String updateProfile(Model model) {
		
		return "view_Hjh/updateProfile";
	}

}
