package com.postgre.choongsam.controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.postgre.choongsam.dao.HmsDao;
import com.postgre.choongsam.dto.Class_Bookmark;
import com.postgre.choongsam.dto.Class_Schedule;
import com.postgre.choongsam.dto.Lecture_Video;
import com.postgre.choongsam.dto.Syllabus;
import com.postgre.choongsam.service.HmsService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
@RequiredArgsConstructor
@CrossOrigin(origins="*")//모든 출처에서의 요청을 허용
public class HmsController {
	
	private final HmsService HS;
	private final HmsDao HD;
	
	
	//영상불러오기 api
	@GetMapping("/video-details")
	public String getVideoDetails(@RequestParam String videoId, Model model,
			@RequestParam int user_seq) {
		System.out.println("hmsController youtube api start..");
		System.out.println("Received videoId:"+ videoId);
		System.out.println("Received user_seq:"+ user_seq);
		//System.out.println("Received filename:"+ filename);
		
		String URL = HD.getURL(videoId); //videoId를 이용해서 Addr 다시 불러오기
		System.out.println("msController getURL ->"+ URL);
		
		String result = HS.getVideoDetails(URL);		
		System.out.println("mscontroller getVideoDetails result->"+result);
		
		//Lecture_video 객체 생성
		Lecture_Video lectureVideo =  new Lecture_Video();
		lectureVideo.setConts_id(videoId);
		
		//유튜브 url 생성 (addr로 변경)
		String videoUrl = "http://www.youtube.com/embed/" + URL;
		System.out.println("hmsController videoUrl:"+videoUrl);		
		model.addAttribute("videoUrl", videoUrl);
		model.addAttribute("videoId",URL); //videoId에 URL 주소값 넣어서 보내기.
		model.addAttribute("conts_id",videoId); //0101 등 영상번호
		
		String filename= HD.getfilename(videoId);
		
		model.addAttribute("filename",filename);
		
		return "view_Hms/videoView";
	}

	//postgre 모든 영상 조회
	@GetMapping("/video-list")
	public String getVideoList(Model model) {
		List<Lecture_Video> videoList = HS.findAllVideo();
		model.addAttribute("videoList",videoList);
		return "view_Hms/videoList";
	}

	
	//뷰에서 데이터 받아 저장하기
	@PostMapping("/api/progress/save")
	public String dataSave(Lecture_Video lecture_video, 
			@RequestParam String videoId, 
			@RequestParam long conts_final,
			@RequestParam long conts_max, 
			@RequestParam int vdo_length) {
		System.out.println("msController dataSave start..");
		System.out.println("videoId->"+videoId);
		System.out.println("conts_final->"+conts_final);		
		System.out.println("vdo_length->"+vdo_length);		
		System.out.println("conts_max 방금 재생한 currentmax값->"+conts_max);
	
		Syllabus lctrInfo = HD.findLctrInfo(videoId);
		System.out.println("msController datasave lctrInfo->"+lctrInfo);
		Class_Schedule class_schedule = new Class_Schedule();
		
		//현재 DB의 conts_max값 조회
		long currentContsMax = HS.findCurrentMax(videoId);	
		System.out.println("mscontroller 현재 DB max값->"+currentContsMax);	
			
		class_schedule.setConts_final(conts_final);		
		class_schedule.setConts_id(videoId);
		class_schedule.setLctr_id(lctrInfo.getLctr_id());
		class_schedule.setLctr_no(lctrInfo.getLctr_no()); 

		// conts_max가 현재 값보다 큰 경우에만 업데이트
	    if (conts_max > currentContsMax) {
	        class_schedule.setConts_max(conts_max); // 새로운 conts_max 설정
	        long conts_prgrt = (class_schedule.getConts_max()*100)/vdo_length;
			System.out.println("conts_prgrt->"+conts_prgrt);
			class_schedule.setConts_prgrt(conts_prgrt);      
	        HS.saveWatchTime(class_schedule); // conts_max를 포함한 업데이트
	    } else {
	        // conts_max가 현재 값보다 작거나 같은 경우, conts_final만 업데이트
	        class_schedule.setConts_max(currentContsMax); // 기존 값을 유지
	        long conts_prgrt = (class_schedule.getConts_max()*100)/vdo_length;
			System.out.println("conts_prgrt->"+conts_prgrt);
			class_schedule.setConts_prgrt(conts_prgrt);
	        HS.saveWatchTimeNoMaxUpdate(class_schedule); // conts_max 없이 업데이트
	    }   		
	    System.out.println("mscontroller dataSave class_schedule->"+class_schedule);
	
		return "view_Jsh/stuLecture";//강의목록으로
	}
	
	//마지막 위치 가져오기
	@GetMapping("/api/progress/{videoId}")
	public ResponseEntity<Class_Schedule> getProgress(@PathVariable String videoId) {
		System.out.println("msController getProgress start..");
		System.out.println("msController getProgress videoId.."+videoId);
	    // 비디오 ID를 사용하여 시청 시간 정보를 조회합니다.
	    int conts_final = HS.watchedFinalTime(videoId);
	    
	    System.out.println("mscontroller getProgress videoId"+videoId);
	    System.out.println("mscontroller getProgress conts_final"+conts_final);

	    // 결과를 DTO로 포장하여 반환.
	    Class_Schedule response = new Class_Schedule();
	    response.setConts_final(conts_final);
	    return ResponseEntity.ok(response);
	}
	
	//파일이름
	@GetMapping("/api/lectures/init")
	public String getfilename(@RequestParam String conts_id, Model model) {
		System.out.println("mscontroller getfilename start..");
		System.out.println("mscontroller getfilename conts_id.."+conts_id);
		String filename = HS.getfilename(conts_id);
		System.out.println("mscontroller getfilename filename->"+filename);
		model.addAttribute("filename",filename);
		return "view_Hms/videoView";
	}

	//강의자료 다운로드
	@GetMapping("/api/lectures/download/{filename}")
	public ResponseEntity<Resource> downloadLecture(@PathVariable String filename) {
	    System.out.println("msController downloadLecture start...");
	    System.out.println("msController downloadLecture filename..." + filename);

	    try {
	        // 데이터베이스에서 파일 경로 가져오기
	        String filePath = HS.getFilePath(filename);
	        System.out.println("filePath->" + filePath);
	        if (filePath == null) {
	            System.out.println("파일 경로를 찾을 수 없습니다." + filename);
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }	        
	        // 파일 경로를 Path 객체로 변환
	        Path file = Paths.get(filePath); // File path retrieved: C:/~~~	        
	        System.out.println("msController downLoad filePath->" + filePath);
	        System.out.println("msController downLoad file->" + file);

	        // UrlResource 생성 시 URI로 변환
	        Resource resource = new UrlResource(file.toUri());
	        System.out.println("msController downLoad resource->"+resource);
	        if (resource.exists() && resource.isReadable()) {
	            // 파일 이름을 URL 인코딩
	            String encodedFilename = URLEncoder.encode(resource.getFilename(), StandardCharsets.UTF_8.toString());
	            System.out.println("msController downLoad encodedFilename->"+encodedFilename);
	            // 파일의 MIME 타입을 설정
	            String contentType = Files.probeContentType(file);
	            contentType = (contentType != null) ? contentType : "application/octet-stream";

	            return ResponseEntity.ok()
	                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + encodedFilename + "\"") // 인코딩된 파일 이름 사용
	                    .header(HttpHeaders.CONTENT_TYPE, contentType)
	                    .body(resource);
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    }
	}
	
	//북마크
	@GetMapping("/api/bookmarks/{conts_id}")
	public  ResponseEntity< List<Class_Bookmark>> getBookmark(@PathVariable("conts_id") String conts_id,Model model) {
		System.out.println("msController getBookmark start...");
		System.out.println("msController getBookmark conts_id..."+conts_id);
		List<Class_Bookmark> bookmark = HS.getBookmark(conts_id);
		System.out.println("msController getBookmark bookmark..."+bookmark);
		model.addAttribute("bookmark",bookmark);
		model.addAttribute("conts_id");
		if(bookmark !=null) {
			return ResponseEntity.ok(bookmark);
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	
	
	
	
	
}
	

	
	
	

	
	


	

