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

import com.postgre.choongsam.dto.Notice;
import com.postgre.choongsam.dto.Paging;
import com.postgre.choongsam.service.SjmService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = "/api")
public class SjmController {
	private final SjmService ss;
	
	// NOTE -  공지사항 목록
	@GetMapping(value="/notice")
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
	@GetMapping(value="/notice/new")
	public String noticeCreateForm(){
		
		System.out.println("SjmController.noticeCreate() start .....");

		return "view_Sjm/noticeCreate";
	}

	// NOTE - 공지사항 상세 화면
	@GetMapping(value="/notice/{ntc_mttr_sn}")
	@ResponseBody
	public Notice noticeDetailForm(@PathVariable("ntc_mttr_sn") int ntc_mttr_sn) {

		System.out.println("SjmController.noticeDetailForm() start...");
		
		Notice notice = ss.noticeDetail(ntc_mttr_sn);

		return notice;
	}

	


}
