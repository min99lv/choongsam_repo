package com.postgre.choongsam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.postgre.choongsam.dto.Lecture;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class AtrController {
	
	@RequestMapping(value = "/registerCourseForm")
	public String registerCourseForm(){
		
		return "view_Atr/registerCourseForm";
	}
	
	@RequestMapping(value = "/registerCourse")
	public String registerCourse(HttpServletRequest request){
		System.out.println("Tr Service registerCourse Start");
		Lecture lecture = new Lecture();
		System.out.println(request.getParameter("selectedTimes"));
		lecture.setUser_seq(Integer.parseInt(request.getParameter("user_seq")));
		lecture.setOnoff(request.getParameter("onOff"));
		lecture.setLctr_category(request.getParameter("lctr_category"));
		lecture.setLctr_name(request.getParameter("lctr_name"));
		lecture.setLctr_count(Integer.parseInt(request.getParameter("lctr_count")));
		lecture.setRcrt_date(request.getParameter("rcrt_date"));
		lecture.setLctr_cost(Integer.parseInt(request.getParameter("lctr_cost")));
		lecture.setLctr_state(request.getParameter("lctr_state"));
		lecture.setEval_criteria(request.getParameter("eval_criteria"));
		lecture.setLctr_start_date(request.getParameter("lctr_start_date"));
		lecture.setLctr_date(Integer.parseInt(request.getParameter("lctr_date")));
		lecture.setLctr_cntschd(Integer.parseInt(request.getParameter("lctr_cntschd")));

		
		
		return "main";
	}

}
