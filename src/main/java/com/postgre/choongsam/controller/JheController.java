package com.postgre.choongsam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.postgre.choongsam.dto.Homework;
import com.postgre.choongsam.service.JheService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequestMapping(value="/Jhe")
@RequiredArgsConstructor
public class JheController {

	@Autowired
	private final JheService hes;

	@GetMapping(value = "/getHWList")
	public String selectHW(Homework homework, Model model) {
		System.out.println("깐따삐야 입성");
		List<Homework> HWList = hes.selectHW();
		System.out.println(HWList);
		model.addAttribute("HWList", HWList);
		return "view_Jhe/getHWList";
	}
}
