package com.postgre.choongsam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AtrController {
	
	@RequestMapping(value = "/registerCourseForm")
	public String registerCourseForm(){
		
		return "view_Atr/registerCourseForm";
	}
	

}
