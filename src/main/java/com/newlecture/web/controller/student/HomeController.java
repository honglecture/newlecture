package com.newlecture.web.controller.student;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("studentHomeController")
@RequestMapping("/student/")
public class HomeController {

	@RequestMapping("index")
	public String type(Model model) {
		return "student.index";
	}

}