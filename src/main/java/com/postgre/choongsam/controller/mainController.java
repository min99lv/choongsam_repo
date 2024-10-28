package com.postgre.choongsam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.postgre.choongsam.dto.Test01;
import com.postgre.choongsam.service.mainService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class mainController {
	
	private final mainService ms;
	
	
	@GetMapping(value="/")
	public String test(Model model) {
		System.out.println("컨트롤러옴");
		
		Test01 test = ms.test();
		
		System.out.println(test);
		
		model.addAttribute("test",test);
		return "test01";
	}

}
