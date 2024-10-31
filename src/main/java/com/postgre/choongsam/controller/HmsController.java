package com.postgre.choongsam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.postgre.choongsam.dto.Lecture_Video;
import com.postgre.choongsam.service.HmsService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
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
	
	
	

}
