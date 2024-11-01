package com.postgre.choongsam.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.http.codec.multipart.Part;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.postgre.choongsam.dto.Class_Bookmark;
import com.postgre.choongsam.dto.Class_Schedule;
import com.postgre.choongsam.dto.Class_ScheduleAddVideo;
import com.postgre.choongsam.dto.Lecture_Video;
import com.postgre.choongsam.dto.Syllabus;
import com.postgre.choongsam.service.JshService;

import jakarta.persistence.criteria.CriteriaBuilder.In;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class JshController {
	
	private final JshService service;
	
	
	//교수
	
	@GetMapping("/sh_lecture_teacher")
	public String sh_lecture_teacher(Model model,
									 @RequestParam String lctr_id,
									 @RequestParam int user_seq) {
		
		model.addAttribute("lctr_id", lctr_id);
		model.addAttribute("user_seq", user_seq);
		
		
		return "view_Jsh/teaLecture";
	}
	
	//폼에 정보를 띄워주기 위함
	@GetMapping("/contsUploadForm")
	public String contsUploadForm(Model model,
							  @RequestParam String lctr_id,
							  @RequestParam int user_seq) {
		
		System.out.println("contsUploadForm lctr_id >> "+lctr_id);
		System.out.println("contsUploadForm user_seq >> "+user_seq);
		
		List<Class_ScheduleAddVideo> startInfo = service.getStartDay(lctr_id);
		
		Optional<String> startDay = startInfo.stream()
					                .map(Class_ScheduleAddVideo::getLctr_start_date)
					                .findFirst();
		Optional<Integer> max_lctr_no = startInfo.stream()
					               .map(Class_ScheduleAddVideo::getLctr_no)
					               .findFirst();
		Optional<Integer> chongChashi = startInfo.stream()
	               .map(Class_ScheduleAddVideo::getLctr_cntschd)
	               .findFirst();
		Optional<String> viewing_period = startInfo.stream()
	               .map(Class_ScheduleAddVideo::getViewing_period)
	               .findFirst();
		
		String endDay = "";
		
		//1차시라면 개강일에 맞춰서 시작
		if(max_lctr_no.get() == 1) {
			//시작날짜에 7일 더해주는 메소드 호출
			endDay = addDays(startDay.get(), 7);
			System.out.println(startDay.get());
			System.out.println(endDay);
		}
		//1차시가 아니라면 마지막 강의 날짜에 맞춰서 시작
		else {
			endDay = addDays(viewing_period.get(), 7);
			System.out.println(startDay.get());
			System.out.println(endDay);
		}
		
		model.addAttribute("lctr_id", lctr_id);
		model.addAttribute("max_lctr_no", max_lctr_no.get());
		model.addAttribute("user_seq", user_seq);
		model.addAttribute("startDay", startDay.get());
		model.addAttribute("endDay", endDay);
		model.addAttribute("chongChashi",chongChashi.get());
		model.addAttribute("viewing_period",viewing_period.get());
		
		return "view_Jsh/contsUploadForm";
	}
	
	//날짜계산기
	public static String addDays(String dateStr, int daysToAdd) {
        // Define the date format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d");
        
        // Parse the input date string to LocalDate
        LocalDate date = LocalDate.parse(dateStr.trim(), formatter);
        
        // Add the specified number of days
        LocalDate newDate = date.plus(daysToAdd, ChronoUnit.DAYS);
        
        // Return the new date as a formatted string
        return newDate.format(formatter);
    }
	
	
	//강의 영상 등록 폼 업로드
	@PostMapping("/contsUpload")
	public String contsUpload(Model model,
							  HttpServletRequest request,
							  Lecture_Video video, 
							  Class_Bookmark bookmark,
							  Syllabus syllabus,
							  Class_ScheduleAddVideo info,
							  @RequestParam("file") MultipartFile file/*파일 객체 가져옴*/
							  ) throws IOException {
		
		String lctr_id = syllabus.getLctr_id();
		int user_seq = syllabus.getUser_seq();
		
		System.out.println("contsUpload lctr_id >> "+lctr_id);
		System.out.println("contsUpload user_seq >> "+user_seq);
		
		
		String title = video.getVdo_file_nm(); 									//강의영상제목 가져오기
		int lctr_no = video.getLctr_no();											//차시 정보 가져오기
		String viewing_period = video.getViewing_period();				//출석인정기간 가져오기
		String vdo_url_addr = video.getVdo_url_addr();					//유튜브ID 가져오기
		int vdo_length = video.getVdo_length();								//영상 전체길이 가져오기
		Integer conts_chptime1 = bookmark.getConts_chptime_sec1(); // 챕터시간1 가져오기
		String conts_chpttl1 = bookmark.getConts_chpttl(); 						// 챕터제목1 가져오기
		Integer conts_chptime2 = bookmark.getConts_chptime_sec2(); 	// 챕터시간2 가져오기
		String conts_chpttl2 = bookmark.getConts_chpttl2(); 					// 챕터제목2 가져오기
		Integer conts_chptime3 = bookmark.getConts_chptime_sec3();	// 챕터시간3 가져오기
		String conts_chpttl3 = bookmark.getConts_chpttl3();					 // 챕터제목3 가져오기
		
		System.out.println("file.getOriginalFilename >> "+file.getOriginalFilename());
		
		
		System.out.println("title >> "+title);
		System.out.println("lctr_no >> "+lctr_no);
		System.out.println("vdo_url_addr >> "+vdo_url_addr);
		System.out.println("vdo_length >> "+vdo_length+"초");
		System.out.println("conts_chptime1 >> "+conts_chptime1);
		System.out.println("conts_chpttl1 >> "+conts_chpttl1);
		System.out.println("conts_chptime2 >> "+conts_chptime2);
		System.out.println("conts_chpttl2 >> "+conts_chpttl2);
		System.out.println("conts_chptime3 >> "+conts_chptime3);
		System.out.println("conts_chpttl3 >> "+conts_chpttl3);
		System.out.println("viewing_period >> "+viewing_period);
		
		//시간 초단위 변경 메소드 출력
		/*Integer chapTimeSec1 = getSec(conts_chptime1);
		Integer chapTimeSec2 = getSec(conts_chptime2);
		Integer chapTimeSec3 = getSec(conts_chptime3);
		String chapTimeSec1Str = null;
		String chapTimeSec2Str = null;
		String chapTimeSec3Str = null;
		
		info.setConts_chptime(chapTimeSec1);		//초단위챕터시간1
		info.setConts_chptime2(chapTimeSec2);	//초단위챕터시간2
		info.setConts_chptime3(chapTimeSec3);	//초단위챕터시간3*/
		
		info.setConts_chptime(conts_chptime1);		//초단위챕터시간1
		info.setConts_chptime2(conts_chptime2);	//초단위챕터시간2
		info.setConts_chptime3(conts_chptime3);	//초단위챕터시간3
		
		
		
		String originFile = file.getOriginalFilename();
		if (originFile != null && !originFile.isEmpty()) {
		    int lastIndex = originFile.lastIndexOf(".");
		    if (lastIndex != -1) {
		        String originFileName = originFile.substring(0, lastIndex);
		        String fileSuffix = originFile.substring(lastIndex + 1);
		        long  fileSize =file.getSize();
		        System.out.println("originFile >> " + originFile);
		        System.out.println("originFileName >> " + originFileName);
		        System.out.println("fileSuffix >> " + fileSuffix);
		        System.out.println("fileSize >> " + fileSize+"바이트");
		        
		        InputStream inputStream = file.getInputStream();
		        
		        // Servlet 상속 받지 못했을 떄 realPath 불러 오는 방법
				String uploadPath = request.getSession().getServletContext().getRealPath("/WEB-INF/chFile/lctrContsFileSh/"+lctr_id+"/"+lctr_no);
		        		
		        String result = uploadFile(originFile, inputStream, uploadPath, fileSuffix);
		        
		        System.out.println("JshController contsUpload fileUpload result >> "+originFile+" "+result);
		       
		        info.setFile_nm(originFileName);		//실제파일명
		        info.setFile_extn_nm(fileSuffix);			//파일 확장자명
		        info.setFile_sz(fileSize);						//파일 크기
		        info.setFile_path_nm(uploadPath+"\\"+originFile);	//파일경로
		        
		    } else {
		        // 파일 확장자가 없는 경우 처리
		        System.out.println("파일에 확장자가 없습니다.");
		    }
		} else {
		    System.out.println("업로드된 파일이 없습니다.");
		}
		
		//service.contsUpload(info);
		
		return "view_Jsh/teaLecture";
	}
	
	/*
	// 시간형식을 초로 변환해주는 메소드 (ex: 00:02:20 >> 140)
    public Integer getSec (Integer chptimeString) {

    	Integer totalSeconds = null;
    	
    	//null이 아닌 겨웅에만 실행, null인 경우 null반환
    	if(chptimeString != null) {
    		// HH:mm:ss 형식으로 LocalTime 객체로 변환
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            LocalTime time = LocalTime.parse(chptimeString, formatter);

            // 총 초 계산
            totalSeconds = time.toSecondOfDay();

            System.out.println("총 초: " + totalSeconds);
    	}
        
		return totalSeconds;
    }
    */
    
    
    
	 // 저장할 파일 경로 설정 및 파일 저장 메소드
	 	private String uploadFile(String originalName, 
	 											 InputStream inputStream, 
	 											 String uploadPath, 
	 											 String suffix) {
	
	 		String result = "업로드 실패";
	 		
	 		System.out.println("uploadPath->" + uploadPath);
	
	 		/* 파일을 저장할 폴더가 없는 경우 새로 생성 */
	 		/* File 메소드 선언 */
	 		File dir = new File(uploadPath);
	 		if (!dir.exists()) {
	 			try {
	 				// 폴더 생성
	 				dir.mkdirs();
	 				System.out.println(uploadPath + " 폴더 생성");
	 			} catch (Exception e) {
	 				System.out.println("mkDir exception >> " + e);
	 			}
	 		}
	
	 		// 임시파일 생성
	 		File tempFile = new File(uploadPath + "\\" + originalName);
	
	 		// 이미지 업로드
	 		try (FileOutputStream outputStream = new FileOutputStream(tempFile)) {
	 			int read;
	 			// 2K*K
	 			byte[] bytes = new byte[2048000];
	 			while ((read = inputStream.read(bytes)) != -1) {
	 				// 이미지 저장
	 				outputStream.write(bytes, 0, read);
	 				result = "업로드 성공";
	 			}
	 		} catch (IOException e) {
	 			System.out.println("이미지 업로드 실패 >> " + e);
	 			e.printStackTrace();
	 		} finally {
	 			System.out.println("UpLoad The End");
	 		}
	
	 		return result;
	 	}
    
	 	
	 	//************************************************************************************************************
	 	
	 	//학생
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
			String lectName = contentList.stream()
									                  .map(Class_ScheduleAddVideo::getLctr_name)
									                  .findFirst()
									                  .orElse("");
			String teacherName = contentList.stream()
									                  .map(Class_ScheduleAddVideo::getUser_name)
									                  .findFirst()
									                  .orElse("");
			System.out.println("강의명 >> "+lectName);
			System.out.println("강사명 >> "+teacherName);
			
			model.addAttribute("lectName", lectName);
			model.addAttribute("teacherName", teacherName);
			model.addAttribute("contentList", contentList);
			
			return "view_Jsh/stuLecture";
		}
    
	
	
	
	
	
}
