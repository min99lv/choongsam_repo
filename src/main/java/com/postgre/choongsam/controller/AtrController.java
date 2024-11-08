package com.postgre.choongsam.controller;

import java.util.ArrayList;
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
	@RequestMapping(value = "/lectureDetail")
	public String lectureDetail(HttpServletRequest request, Model model,String lctr_id) {
		System.out.println("TrlectureDetail Start");

		Lecture lecture = new Lecture();
		lecture =as.getLectureDetail(lctr_id);
		model.addAttribute("lecture", lecture);
		return "view_Atr/lectureDetailAdmin";
	}
	@RequestMapping(value = "/addClassRoomForm")
	public String addClassRoom(HttpServletRequest request, Model model) {
		System.out.println("TraddClassRoom Start");
		String lctr_id= request.getParameter("lctr_id");
		String schd= request.getParameter("lctr_schd");
		String schdTGemp ="";
//		as.addClassRoomForm(lctr_id,schd);
		System.out.println("addClassRoomForm schd->"+schd);
		List<String> schdList = new ArrayList<>();
		StringTokenizer st = new StringTokenizer(schd,", ");
		while (st.hasMoreTokens()) {
			schdTGemp = st.nextToken();
			System.out.println(schdTGemp);
			schdList.add(schdTGemp);
       }
		model.addAttribute("lctr_id", lctr_id);
		model.addAttribute("schdList", schdList);
 
		
		
		return "view_Atr/addClassRoomForm";
	}
	

}
