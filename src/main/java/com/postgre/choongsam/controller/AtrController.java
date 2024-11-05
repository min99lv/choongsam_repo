package com.postgre.choongsam.controller;

import java.util.StringTokenizer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.postgre.choongsam.dto.Lecture;
import com.postgre.choongsam.service.AtrService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AtrController {
	
	private final AtrService as;
	
	@RequestMapping(value = "/registerCourseForm")
	public String registerCourseForm(){
		
		return "view_Atr/registerCourseForm";
	}
	
	@RequestMapping(value = "/registerCourse")
	public String registerCourse(HttpServletRequest request,Lecture lecture){
		System.out.println("Tr Service registerCourse Start");
		
		as.registerCourse(lecture);
		

		
		
		return "main";
	}

}
