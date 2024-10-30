package com.postgre.choongsam.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.postgre.choongsam.dto.Class_Bookmark;
import com.postgre.choongsam.dto.Class_Schedule;
import com.postgre.choongsam.dto.Class_ScheduleAddVideo;
import com.postgre.choongsam.dto.Lecture_Video;
import com.postgre.choongsam.service.JshService;

import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class JshController {
	
	private final JshService service;
	
	
	@GetMapping("/sh_lecture_student")
	public String StudentLecture(Model model,
								 @RequestParam String lctr_id,
								 @RequestParam int user_seq) {
		System.out.println("JshController StudentLecture start...");
		System.out.println("JshController StudentLecture lctr_id >> "+lctr_id);
		System.out.println("JshController StudentLecture user_seq >> "+user_seq);
		
		List<Class_ScheduleAddVideo> contentList = service.studentLecture(lctr_id, user_seq);
		System.out.println("JshController StudentLecture contentList >> "+contentList);
		
		//강사의 이름만 필터링해서 꺼내는 동작
		Optional<String> lectName = contentList.stream()
								                  .map(Class_ScheduleAddVideo::getLctr_name)
								                  .findFirst();
		Optional<String> teacherName = contentList.stream()
								                  .map(Class_ScheduleAddVideo::getUser_name)
								                  .findFirst();
		System.out.println("강의명 >> "+lectName);
		System.out.println("강사명 >> "+teacherName);
		
		
		
		
		model.addAttribute("lectName", lectName);
		model.addAttribute("teacherName", teacherName);
		model.addAttribute("contentList", contentList);
		
		return "view_Jsh/stuLecture";
	}
	
	@GetMapping("/sh_lecture_teacher")
	public String sh_lecture_teacher(Model model,
									 @RequestParam String lctr_id,
									 @RequestParam int user_seq) {
		
		model.addAttribute("lctr_id", lctr_id);
		model.addAttribute("user_seq", user_seq);
		
		
		return "view_Jsh/teaLecture";
	}
	
	@GetMapping("/contsUploadForm")
	public String contsUpload(Model model,
							  @RequestParam String lctr_id,
							  @RequestParam int user_seq) {
		
		
		
		return "view_Jsh/contsUploadForm";
	}
	
	@PostMapping("/contsUpload")
	public String contsUpload(Model model, 
							  Lecture_Video video, 
							  Class_Bookmark bookmark) {
		
		
		
		return "4";
	}
	
	
	
	
	
}
