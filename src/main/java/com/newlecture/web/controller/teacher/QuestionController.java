package com.newlecture.web.controller.teacher;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.newlecture.web.entity.Question;

@Controller
@RequestMapping("/teacher/question/")
public class QuestionController {

	@GetMapping("type")
	public String type(Model model) {
		model.addAttribute("hello", "servlet");
		return "teacher.question.type";
	}
	
	@GetMapping("choice-reg")
	public String choiceReg() {
		return "teacher.question.choice-reg";
	}
	
	@GetMapping("list")
	public String list() {
		return "teacher.question.list";
	}

	@PostMapping("choice-reg")
	public String choiceReg(Question question) {
		return "redirect:type";
	}

}