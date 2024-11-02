package com.postgre.choongsam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.postgre.choongsam.dto.Homework;
import com.postgre.choongsam.service.JheService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
@RequestMapping(value="/Jhe")
@RequiredArgsConstructor
public class JheController {

	@Autowired
	private final JheService hes;

	@GetMapping(value = "/profHomeworkList")
	public String profHomeworkList(Homework homework, HttpSession session, Model model) {
		System.out.println("깐따삐야 입성");

//		Login_Info loginInfo = (Login_Info) session.getAttribute("user");
//		System.out.println("loginid: " + loginInfo.getUser_id());
//		homework.setUSER_STATUS(loginInfo.getUser_status());
		List<Homework> insertHomeworkList = hes.profHomeworkList();
		System.out.println(insertHomeworkList);
		model.addAttribute("HWList", insertHomeworkList);
		return "view_Jhe/profHomeworkList";
	}

	@GetMapping(value = "/insertHomework")
	public String getMethodName() {
		return "view_Jhe/insertHomework";
	}

	@PostMapping(value = "/insertHomework")
	public String writeHomework(@ModelAttribute Homework homework,
								HttpServletRequest request,
								RedirectAttributes redirectAttributes,
								Model model) {
		System.out.println("과제 등록하러 가자");

		homework.setLCTR_ID("0010");
		System.out.println("LCTR_ID: " + homework.getLctr_id());
		homework.setFile_group(1111);
		System.out.println("FILE_GROUP: " + homework.getFile_group());

		int insHWList = hes.insertHomework(homework);
		System.out.println("insHWList: " + insHWList);

		if (insHWList > 0) {
			System.out.println("과제해라 이눔자식들");
			redirectAttributes.addFlashAttribute("status", "success");
			return "redirect:/Jhe/profHomeworkList";
		} else {
			System.out.println("하.. 과제 다시 만들어 강사이눔아");
			redirectAttributes.addFlashAttribute("status", "failure");
			return "redirect:/Jhe/insertHomework";
		}
	}

	@PostMapping(value = "/updateHomework")
	public String updateHomework(HttpServletRequest request, Model model) {
		System.out.println("과제 수정 포오스트");

		Homework homework = new Homework();
		homework.setASMT_NO(Integer.parseInt(request.getParameter("ASMT_NO")));
		System.out.println("lctr_name: " + request.getParameter("ASMT_NO"));
		homework.setLctr_name(request.getParameter("lctr_name"));
		System.out.println("lctr_name: " + request.getParameter("lctr_name"));
		homework.setASMT_NM(request.getParameter("ASMT_NM"));
		System.out.println("ASMT_NM: " + request.getParameter("ASMT_NM"));
		homework.setASMT_CN(request.getParameter("ASMT_CN"));
		System.out.println("ASMT_CN: " + request.getParameter("ASMT_CN"));
		homework.setSBMSN_BGNG_YMD(request.getParameter("SBMSN_BGNG_YMD"));
		System.out.println("SBMSN_BGNG_YMD: " + request.getParameter("SBMSN_BGNG_YMD"));
		homework.setSBMSN_END_YMD(request.getParameter("SBMSN_END_YMD"));
		System.out.println("SBMSN_END_YMD: " + request.getParameter("SBMSN_END_YMD"));
		homework.setFile_group(1111);
		System.out.println("FILE_GROUP: " + homework.getFile_group());

		int upHomeworkList = hes.updateHomework(homework);
		System.out.println("upHomeworkList:" + upHomeworkList);
		model.addAttribute("upHomeworkList", upHomeworkList);
		return "redirect:/Jhe/profHomeworkList";
	}

	@GetMapping(value = "/updateHomework")
	public String getUpdateHomework(@ModelAttribute Homework homework, Model model) {
		System.out.println("과제 수정 겟");
		Homework findByASMT = hes.findById(homework);
		model.addAttribute("upHomework", findByASMT);
		System.out.println("findByASMT: " + findByASMT);
		return "view_Jhe/updateHomework";
	}

	@PostMapping("/deleteHomework")
	public String deleteHomework(@RequestParam List<Integer> delCheck) {
		System.out.println("과제 삭제를 해보까나");
		for (Integer asmtNo : delCheck) {
			hes.deleteHomework(asmtNo);
		}
		return "redirect:/Jhe/profHomeworkList";
	}
}
