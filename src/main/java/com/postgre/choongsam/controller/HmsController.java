package com.postgre.choongsam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.postgre.choongsam.dto.Class_Schedule;
import com.postgre.choongsam.dto.Lecture_Video;
import com.postgre.choongsam.service.HmsService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HmsController {
	
	private final HmsService HS;
	
	
	//영상불러오기 api
	@GetMapping("/video-details")
	public String getVideoDetails(@RequestParam String videoId, Model model) {
		System.out.println("hmsController youtube api start..");
		System.out.println("Received videoId:"+ videoId);
		
		//Lecture_video 객체 생성
		Lecture_Video lectureVideo =  new Lecture_Video();
		lectureVideo.setConts_id(videoId);
		
		//유튜브 url 생성
		String videoUrl = "http://www.youtube.com/embed/" + videoId;
		System.out.println("hmsController videoUrl:"+videoUrl);
		model.addAttribute("videoUrl", videoUrl);		
		HS.getVideoDetails(videoId);
		return "view_Hms/videoView";
	}

	//postgre 모든 영상 불러오기
	@GetMapping("/video-list")
	public String getVideoList(Model model) {
		List<Lecture_Video> videoList = HS.findAllVideo();
		model.addAttribute("videoList",videoList);
		return "view_Hms/videoList";
	}
	
	//youtube data insert
	@PostMapping("/youtube_insert")
	public String youtube_insert(@RequestParam String videoId){
		System.out.println("mscontroller youtube_insert start...");
		HS.getVideoDetails(videoId);
		return "view_Hms/videoList";
		
	}
//	//뷰에서 데이터 받아 저장하기
//	@PostMapping("/api/progress/save")
//	public String dataSave(Lecture_Video lecture_video, 
//			@RequestParam String videoId, 
//			@RequestParam int conts_final,
//			@RequestParam int conts_max, 
//			@RequestParam int vdo_length) {
//		System.out.println("msController dataSave start..");
//		System.out.println("videoId->"+videoId);
//		System.out.println("conts_final->"+conts_final);
//		System.out.println("conts_max->"+conts_max);
//		System.out.println("vdo_length->"+vdo_length);
//		
//		Class_Schedule class_schedule = new Class_Schedule();
//		//int conts_prgrt = (class_schedule.getConts_max()/lecture_video.getVdo_length())*100;
//		class_schedule.setConts_final(conts_final);
//		class_schedule.setConts_max(conts_max);
//		class_schedule.setConts_prgrt(conts_prgrt);
//		
//		HS.saveWatchTime(class_schedule);
//		
//		
//		return "view_Hms/test";
//	}
	
	
	
	

	
	
	

	
	


	
}
