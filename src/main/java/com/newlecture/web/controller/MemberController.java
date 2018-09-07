package com.newlecture.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.newlecture.web.dao.MemberDao;
import com.newlecture.web.entity.Member;

@Controller
@RequestMapping("/member/")
public class MemberController {
	
	@Autowired
	private MemberDao memberDao;
	
	@GetMapping("join")
	public String join(Model model) {
		
		Member member = memberDao.get("flwj");
		model.addAttribute("member", member);
		return "member.join";
	}

	@GetMapping("login")
	public String login() {
		return "member.login";
	}
	
	@GetMapping("join-email")
	public String joinEmail() {
		return "member.join-email";
	}
	
	@PostMapping("join-email")
	public String joinEmail(String email) {
		System.out.println(email);
		// POST한 다음 안내의 말을 해줘야 한다. 이메일을 확인해 주세요..
		//이메일을 보내기 위한 준비작업
		
		JavaMailSender mailSender = new JavaMailSenderImpl();
		
		
		return "member.join-email-info";
	}
	
	@GetMapping("join-reg")
	public String joinReg(Model model) {
		Member member = memberDao.get("flwj");
		model.addAttribute("member", member);
		return "member.join";
	}
	
	
	
	
	
	
	

}