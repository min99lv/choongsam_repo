package com.postgre.choongsam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class mainController {
	
	
	
	@GetMapping(value="/")
	public String test(Model model) {
		System.out.println("컨트롤러옴");
		
		
		return "main";
	}

}
