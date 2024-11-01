package com.postgre.choongsam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.postgre.choongsam.dto.Homework;
import com.postgre.choongsam.service.JheService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequestMapping(value="/Jhe")
@RequiredArgsConstructor
public class JheController {

	@Autowired
	private final JheService hes;

	@GetMapping(value = "/getHWList")
	public String selectHW(Model model) {
		System.out.println("깐따삐야 입성");
		List<Homework> HWList = hes.selectHW();
		System.out.println(HWList);
		model.addAttribute("HWList", HWList);
		return "view_Jhe/getHWList";
	}

	@GetMapping("/insertHomework")
	public String getMethodName() {
		return "view_Jhe/getHWList";
	}

	@RequestMapping(value = "/insertHomework")
	public String writeHomework(HttpServletRequest request, Model model) {
		System.out.println("과제 등록하러 가자");
		Homework homework = new Homework();
		homework.setLCTR_ID("0010");
		System.out.println("LCTR_ID: " + homework.getLctr_id());
		homework.setASMT_NM(request.getParameter("ASMT_NM"));
		System.out.println("ASMT_NM: " + request.getParameter("ASMT_NM"));
		homework.setASMT_CN(request.getParameter("ASMT_CN"));
		System.out.println("ASMT_CN: " + request.getParameter("ASMT_CN"));
		homework.setSBMSN_END_YMD(request.getParameter("SBMSN_END_YMD"));
		System.out.println("SBMSN_END_YMD: " + request.getParameter("SBMSN_END_YMD"));
		homework.setFile_group(1111);
		System.out.println("FILE_GROUP: " + homework.getFile_group());

		int insHWList = hes.insertHomework(homework);
		System.out.println("insHWList: " + insHWList);
		return "redirect:/view_Jhe/getHWList";
	}
}
