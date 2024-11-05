package com.postgre.choongsam.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.postgre.choongsam.dto.Login_Info;
import com.postgre.choongsam.dto.Note;
import com.postgre.choongsam.dto.Notice;
import com.postgre.choongsam.dto.Paging;
import com.postgre.choongsam.service.SjmService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
public class SjmController {
	private final SjmService ss;
	
	// NOTE -  공지사항 목록
	@GetMapping(value="/api/notice")
	public String notice(Model model,
			@RequestParam(value = "currentPage", defaultValue = "1") String currentPage,
			@RequestParam(value = "keyword", required = false) String keyword
			) 
			{
		
		
				log.info("noticeController start...");
				int total = ss.countNotice(keyword); 
				Paging page = new Paging(total,currentPage);
				// Map 객체 생성하여 파라미터 설정
				Map<String, Object> params = new HashMap<>();
				params.put("start", page.getStart());
				params.put("rowPage", page.getRowPage());
				params.put("keyword", keyword);
				
				System.out.println("page---->"+page);
				
				List<Notice> noticeList = ss.selectNoticeList(params);
				
				
				
				model.addAttribute("page",page);
				model.addAttribute("total",total);
				model.addAttribute("noticeList",noticeList);
		
		return "view_Sjm/notice";
		
	}

	// NOTE -  공지사항 작성 화면 
	@GetMapping(value="/api/notice/new")
	public String noticeCreateForm(){
		
		System.out.println("SjmController.noticeCreate start .....");

		return "view_Sjm/noticeCreate";
	}

	// NOTE - 공지사항 상세
	@GetMapping(value="/api/notice/{ntc_mttr_sn}")
	@ResponseBody
	public Notice noticeDetail(@PathVariable("ntc_mttr_sn") int ntc_mttr_sn) {

		System.out.println("SjmController.noticeDetailForm() start...");
		
		Notice notice = ss.noticeDetail(ntc_mttr_sn);

		return notice;
	}
	
	// NOTE - 공지사항 상세 화면 
	@GetMapping(value="/view_Sjm/noticeDetail")
	public String noticeDetailForm(@RequestParam("ntc_mttr_sn") int ntc_mttr_sn, Model model) {
	    model.addAttribute("ntc_mttr_sn", ntc_mttr_sn);  
	    return "view_Sjm/noticeDetail";  // view_Sjm/noticeDetail.jsp로 이동
	}
	
	
	// NOTE - 쪽지함 전체쪽지 화면
	@GetMapping(value = "/view_Sjm/noteBox")
	public String noteBoxForm(HttpSession session) {
		
		System.out.println("쪽지함 화면");

		System.out.println(session.getAttribute("user"));
		
		if(session.getAttribute("user") == null) {
			System.out.println("로그인 하시오");
			
			return "redirect:/view_Ljm/loginForm";
		}
	
		
		return "view_Sjm/noteBox";
	}
	
	// NOTE - 쪽지 목록
//	@GetMapping(value="/api/note")
//	@ResponseBody
//	public List<Note> noteList(HttpSession session) {
//		
//		Login_Info user = (Login_Info) session.getAttribute("user");
//		
//		System.out.println("user.getUser_seq() "+user.getUser_seq() );
//		
//		Map<String, Object> params = new HashMap<>();
//		params.put("user_seq", user.getUser_seq() );
//		
//	//	List<Note> noteList = ss.noteList(params);
//		
//		
//		return noteList;
//	}
//
//
}
