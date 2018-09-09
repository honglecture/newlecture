package com.newlecture.web.controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.newlecture.web.dao.MemberDao;
import com.newlecture.web.entity.Member;

@Controller
@RequestMapping("/member/")
public class MemberController {
	
	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private JavaMailSender mailSender;
	
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
	public String joinEmail(String email, HttpServletResponse response) {
		
		//유니크한 id를 뽑아내야 한다. guid
		UUID uuid = UUID.randomUUID(); // + 커스텀
		
		MessageDigest salt = null;
		String digest = null;
		
		//지문 채취 작업
		try {
			salt =  MessageDigest.getInstance("SHA-256");
			salt.update(uuid.toString().getBytes()); // 넘겨줄 값이 byte이다.
			
			//바이트열을 문자열로 바꾸기 위해서 더하기가 반복되어야 한다.
			byte[] key = salt.digest();
			
			// 문자열 연결에 효율적이다. 
			StringBuilder builder = new StringBuilder();
			
			for(byte b : key)
				builder.append(String.format("%02x", b)); //포맷팅
			
			digest = builder.toString(); 
			
			System.out.println(digest);
			//940dc385f2f8b6451fdf736329fedee08f6c5033fe621a146423983b05450442
			
		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		}
		
		
		// 표기법으로 데이터를 구분해준다.(json, xml)
		
		
		//쿠키를 심는 작업
		Cookie cookie = new Cookie("joinId", digest); //식별값, 문자열만 담아야 한다.
		
		
		// member로 경로를 줄인다
		cookie.setPath("/member/"); // 경로 member에서만 쓴다.
		cookie.setMaxAge(60*60*24); // 단위
		
		response.addCookie(cookie);
		
/*		
		System.out.println(uuid.toString());
		//f5f245f0-9fec-4b8b-a824-005d565e68c4
*/		
		
		// 메시지 만들기
		MimeMessage message = mailSender.createMimeMessage();
		try {
			//마임 = 멀티미디어 포함
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
			helper.setFrom("noreply@newlecture.com");
			helper.setTo(email);
			helper.setSubject("뉴렉처 회원가입을 위한 인증메일");
			// 이메일보낼때.. 파라미터 이름 줄여서 쓰자~
			helper.setText("<a href='http://localhost:8080/member/join-reg?id="+digest+"&em="+email+"'>가입링크</a>", true);
			
		} catch (MessagingException e) {
			e.printStackTrace();
		} // 쉽게 구현해주는 객체
		
		mailSender.send(message);
		return "member.join-email-info";
		
	}
	
	@GetMapping("join-reg")
	public String joinReg(
			@RequestParam(value="id", defaultValue="") String key, 
			@CookieValue(value="joinId", defaultValue="") String joinId,
			@RequestParam(value="em", defaultValue="") String email,
			Model model) {
		
		//에러가 나는 조건을 찾는다.
		if(key.equals("") || joinId.equals("") || !key.equals(joinId)) {
			
			// join-error페이지 이동
			return "member.join-error";
		}
		
		// newelcture@namo.com 에서 앞에 newelcture만 발췌하는 코드
		String uid = email.split("@")[0];
		
		model.addAttribute("uid", uid);
		model.addAttribute("email", email);
		
		return "member.join-reg";
		
	}
	
/*	@PostMapping("join-reg")
	public String joinReg(Model model) {
		Member member = memberDao.get("flwj");
		model.addAttribute("member", member);
		return "member.join";
	}*/
	
}