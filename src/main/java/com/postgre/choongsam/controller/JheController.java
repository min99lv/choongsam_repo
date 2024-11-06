package com.postgre.choongsam.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.postgre.choongsam.dto.Attendance;
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
		System.out.println("강사별 강의 과제 리스트 컨트롤러");
		List<Homework> lectureHomeworkList = hes.getLectureHomeworkList();
		System.out.println("lectureHomeworkList" + lectureHomeworkList);
		model.addAttribute("homeworkList", lectureHomeworkList);
		return "view_Jhe/lectureHomeworkList";
	}

	@GetMapping(value = "/profHomeworkList")
	public String getProfHomeworkList(@RequestParam("LCTR_ID") String LCTR_ID, HttpSession session, Model model) {
		System.out.println("강의 과제 리스트 컨트롤러");
		List<Homework> profHomeworkList = hes.getProfHomeworkList(LCTR_ID);
		List<Homework> studSubmitList = hes.getStudSubmitList(LCTR_ID);
		model.addAttribute("LCTR_ID", LCTR_ID);
		model.addAttribute("profHomeworkList", profHomeworkList);
		model.addAttribute("studList", studSubmitList);
		return "view_Jhe/profHomeworkList";
	}

	@GetMapping(value = "/insertHomework")
	public String getInsHomework(@RequestParam("LCTR_ID") String LCTR_ID, Model model) {
		System.out.println("과제 등록하러 가자");
		System.out.println("과제등록 lctr_id: " + LCTR_ID);
		Homework homework = new Homework();
		homework.setLctr_id(LCTR_ID);
		System.out.println("lcid: " + homework.getLctr_id());
		Lecture findByLctrName = hes.findByLCTR(LCTR_ID);
		model.addAttribute("homework", homework);
		model.addAttribute("findByLctrName", findByLctrName);
		return "view_Jhe/insertHomework";
	}

	@PostMapping(value = "/insertHomework")
	public String insertHomework(@RequestParam("LCTR_ID") String LCTR_ID,
								 @RequestParam(value = "file", required = false) MultipartFile file,
								 @ModelAttribute Homework homework,
								 RedirectAttributes redirectAttributes, Model model) {
		System.out.println("과제 등록 포오스트");
		System.out.println("강의 ID: " + LCTR_ID);

		homework.setLctr_id(LCTR_ID);

		int insHomeworkList = hes.insertHomework(homework, file);;
		System.out.println("insHWList: " + insHomeworkList);

		if (insHomeworkList > 0) {
			hes.notifyStudents(LCTR_ID);
		}
		redirectAttributes.addFlashAttribute("status", insHomeworkList > 0 ? "success" : "failure");
		return insHomeworkList > 0 ? "redirect:/Jhe/profHomeworkList?LCTR_ID=" + LCTR_ID : "redirect:/Jhe/insertHomework";
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
	public String updateHomework(@RequestParam("LCTR_ID") String LCTR_ID,
								 @RequestParam(value = "file", required = false) MultipartFile file,
								 @ModelAttribute Homework homework,
								 RedirectAttributes redirectAttributes) {
		System.out.println("과제 수정 포오스트");

		int upHomeworkList = hes.updateHomework(homework, file);
		redirectAttributes.addFlashAttribute("status", upHomeworkList > 0 ? "success" : "failure");
		return "redirect:/Jhe/profHomeworkList?LCTR_ID=" + LCTR_ID;
	}

	@PostMapping("/deleteHomework")
	public String deleteHomework(@RequestParam("LCTR_ID") String LCTR_ID, @RequestParam List<Integer> delCheck, RedirectAttributes redirectAttributes) {
		System.out.println("과제 삭제를 해보까나");
		System.out.println("LCTR_ID: " + LCTR_ID);
		hes.deleteHomeworkList(delCheck);
		return "redirect:/Jhe/profHomeworkList?LCTR_ID=" + LCTR_ID;
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
		System.out.println("ASMT_NO: " + homework.getAsmt_no());
		int upSubmitHomework = hes.updatesubmitHomework(homework);
		return "redirect:/Jhe/studHomeworkList";
	}

	@GetMapping(value = "/studAttByLecture")
	public String studAttByLecture(Model model) {
		System.out.println("강좌별 수강생 출결 현황");
//		List<Attendance> studAttByLecture = hes.getStudAttByLecture();
		return "";
	}

	@GetMapping(value = "/insertStudAtt")
	public String insertStudAtt(@RequestParam("LCTR_ID") String LCTR_ID) {
		System.out.println("차시별 출석 입력 컨트롤러");
		
		return "view_Jhe/insertStudAtt";
	}
}
