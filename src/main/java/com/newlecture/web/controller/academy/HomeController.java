package com.newlecture.web.controller.academy;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("academyHomeController")
@RequestMapping("/academy/")
public class HomeController {

	@RequestMapping("index")
	public String index(Model model) {
		return "academy/index";
	}

}