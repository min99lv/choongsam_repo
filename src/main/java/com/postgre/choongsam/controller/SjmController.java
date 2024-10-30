package com.postgre.choongsam.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.postgre.choongsam.dto.Notice;
import com.postgre.choongsam.dto.Paging;
import com.postgre.choongsam.service.SjmService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value="/api")
public class SjmController {
	private final SjmService ss;
	
	
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

}
