package com.newlecture.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.newlecture.web.dao.MemberDao;

@Controller
@RequestMapping("/")
public class HomeController {

	@Autowired
	private MemberDao memberDao;

	@GetMapping("index")
	public String index(Model model) {
/*		Member member = memberDao.get("newlec");
		model.addAttribute("member", member);*/
		return "home.index";
	}

}