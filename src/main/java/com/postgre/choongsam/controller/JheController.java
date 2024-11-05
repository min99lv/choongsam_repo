package com.postgre.choongsam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.postgre.choongsam.dto.Homework;
import com.postgre.choongsam.dto.Lecture;
import com.postgre.choongsam.service.JheService;

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

	@GetMapping(value = "/lectureHomeworkList")
	public String getLectureHomeworkList(Model model) {
		System.out.println("강사별 강의 과제 리스트 깐따삐아");
		List<Homework> lectureHomeworkList = hes.getLectureHomeworkList();
		System.out.println("lectureHomeworkList" + lectureHomeworkList);
		model.addAttribute("homeworkList", lectureHomeworkList);
		return "view_Jhe/lectureHomeworkList";
	}

	@GetMapping(value = "/profHomeworkList")
	public String getProfHomeworkList(HttpSession session, Model model) {
		System.out.println("강사 강의 과제 리스트 깐따삐아");
//		System.out.println("Received LCTR_ID1: " + LCTR_ID);
		String LCTR_ID = "0010";
//		Login_Info login_Info = (Login_Info) session.getAttribute("user");
		List<Homework> profHomeworkList = hes.getProfHomeworkList(LCTR_ID);
		System.out.println("profHomeworkList" + profHomeworkList);
		model.addAttribute("homeworkList", profHomeworkList);
		return "view_Jhe/profHomeworkList";
	}

	@GetMapping(value = "/insertHomework")
	public String getInsHomework(Model model) {
		System.out.println("과제 등록하러 가자");
//		System.out.println("Received LCTR_ID2: " + LCTR_ID);
		String LCTR_ID = "0010";
		Lecture findByLctrName = hes.findByLCTR(LCTR_ID);
		model.addAttribute("findByLctrName", findByLctrName);
		return "view_Jhe/insertHomework";
	}

	@PostMapping(value = "/insertHomework")
	public String insertHomework(@ModelAttribute Homework homework, RedirectAttributes redirectAttributes, Model model) {
		System.out.println("과제 등록 포오스트");
//		System.out.println("Received LCTR_ID3: " + LCTR_ID);

		homework.setLCTR_ID("0010");
		int insHomeworkList = hes.insertHomework(homework);
		System.out.println("insHWList: " + insHomeworkList);

		if (insHomeworkList > 0) {
			hes.notifyStudents(homework.getLCTR_ID(), homework.getASMT_NO());
		}
		redirectAttributes.addFlashAttribute("status", insHomeworkList > 0 ? "success" : "failure");
		return insHomeworkList > 0 ? "redirect:/Jhe/profHomeworkList" : "redirect:/Jhe/insertHomework";
	}

	@GetMapping(value = "/updateHomework")
	public String getUpdateHomework(@RequestParam int ASMT_NO, Model model) {
		System.out.println("과제 수정 겟");
		Homework findByASMT = hes.findById(ASMT_NO);
		System.out.println("findByASMT: " + findByASMT);
		model.addAttribute("upHomework", findByASMT);
		return "view_Jhe/updateHomework";
	}

	@PostMapping(value = "/updateHomework")
	public String updateHomework(@ModelAttribute Homework homework, RedirectAttributes redirectAttributes) {
		System.out.println("과제 수정 포오스트");
		int upHomeworkList = hes.updateHomework(homework);
		redirectAttributes.addFlashAttribute("status", upHomeworkList > 0 ? "success" : "failure");
		return "redirect:/Jhe/profHomeworkList";
	}

	@PostMapping("/deleteHomework")
	public String deleteHomework(@RequestParam List<Integer> delCheck, RedirectAttributes redirectAttributes) {
		System.out.println("과제 삭제를 해보까나");
		hes.deleteHomeworkList(delCheck);
		return "redirect:/Jhe/profHomeworkList";
	}

	@GetMapping(value = "/studHomeworkList")
	public String getStudHomeworkList(HttpSession session, Model model) {
		System.out.println("학생 강의 과제 리스트 깐따삐아");
		int USER_SEQ = 10051;
		List<Homework> studHomeworkList = hes.getStudHomeworkList(USER_SEQ);
		System.out.println(studHomeworkList);
		model.addAttribute("homeworkList", studHomeworkList);
		return "view_Jhe/studHomeworkList";
	}

	@GetMapping(value = "/submitHomework")
	public String getsubmitHomework(@RequestParam int ASMT_NO, Model model) {
		System.out.println("과제 제출 겟");
		Homework findByASMT = hes.findById(ASMT_NO);
		System.out.println("findByASMT: " + findByASMT);
		model.addAttribute("upHomework", findByASMT);
		return "view_Jhe/submitHomework";
	}

	@PostMapping(value = "/submitHomework")
	public String updatesubmitHomework(@ModelAttribute Homework homework) {
		System.out.println("과제 제출 포오스트");
		System.out.println("ASMT_NO: " + homework.getASMT_NO());
		int upSubmitHomework = hes.updatesubmitHomework(homework);
		return "redirect:/Jhe/studHomeworkList";
	}
}
