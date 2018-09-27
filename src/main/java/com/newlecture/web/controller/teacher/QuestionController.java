package com.newlecture.web.controller.teacher;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.newlecture.web.entity.Question;
import com.newlecture.web.entity.QuestionLevel;
import com.newlecture.web.entity.Subject;
import com.newlecture.web.service.TeacherService;

@Controller
@RequestMapping("/teacher/question/")
public class QuestionController {
	
	@Autowired
	private TeacherService service;
	
	@GetMapping("list")
	public String list(Model model, Principal principal) {
		//교과목
		
		List<Subject> subjects = service.getSubjectList();
		List<QuestionLevel> levels = service.getLevelList();
		
		model.addAttribute("subjects", subjects);
		model.addAttribute("levels", levels);
		
		
		return "teacher.question.list";
	}

	@GetMapping("type")
	public String type(Model model) {
		model.addAttribute("hello", "servlet");
		return "teacher.question.type";
	}
	
	@GetMapping("choice-reg")
	public String choiceReg() {
		return "teacher.question.choice-reg";
	}

	@PostMapping("choice-reg")
	public String choiceReg(Question question) {
		return "redirect:type";
	}

}