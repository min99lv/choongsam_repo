package com.postgre.choongsam.controller;

import java.util.List;
import java.util.StringTokenizer;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.postgre.choongsam.dto.Lecture;
import com.postgre.choongsam.dto.Paging;
import com.postgre.choongsam.service.AtrService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AtrController {

	private final AtrService as;

	@RequestMapping(value = "/registerCourseForm")
	public String registerCourseForm() {

		return "view_Atr/registerCourseForm";
	}

	@RequestMapping(value = "/registerCourse")
	public String registerCourse(HttpServletRequest request, Lecture lecture,Model model) {
		System.out.println("Tr Service registerCourse Start");

		String CourseId = as.registerCourse(lecture);
		System.out.println(lecture.getLctr_cntschd());
		model.addAttribute("classCount",lecture.getLctr_cntschd());
		model.addAttribute("CourseId",CourseId);
		
		return "view_Atr/registerCourseForm2";
	}
	@RequestMapping(value = "/registerCourse2")
	public String registerCourse2(HttpServletRequest request, Model model) {
		System.out.println("Tr Service registerCourse Start");
		
		
		 for (int i = 0; i <= Integer.parseInt(request.getParameter("classCount")); i++) {
	            String courseDetail = request.getParameter("course" + i);
	            if (courseDetail != null && !courseDetail.isEmpty()) {
	                as.registerSyllabus(courseDetail,request.getParameter("lctrId"),i);
	            }
	        }

		 return "main";
	}
	
	@RequestMapping(value = "/courseApproveList")
	public String courseApproveList(@RequestParam(value = "page", required = false) String currentPage, Model model) {
	    List<Lecture> lectureList = as.getAllLectureList();
	    int total = lectureList.size(); // 전체 강의 수

	    // 페이징 객체 생성
	    Paging paging = new Paging(total, currentPage);
	    List<Lecture> paginatedLectureList = lectureList.subList(
	        Math.min(paging.getStart(), total),
	        Math.min(paging.getEnd(), total)
	    );

	    model.addAttribute("lectureList", paginatedLectureList);
	    model.addAttribute("paging", paging);
	    return "view_Atr/courseApproveList";
	}

}
