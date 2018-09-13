package com.newlecture.web.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.newlecture.web.entity.Member;
import com.newlecture.web.service.MybatisHomeService;

@Controller
@RequestMapping("/member/")
public class MemberController {
	
/*	@Autowired
	private MemberDao memberDao;*/
	
	@Autowired
	private MybatisHomeService service;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@GetMapping("join")
	public String join(Model model) {
/*		Member member = memberDao.get("flwj");
		model.addAttribute("member", member);*/
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
	
	@GetMapping("is-id-duplicated")
	@ResponseBody // 뷰를 찾지 말아라.. ResponseBody
	public String isIdDuplicated(String id) {
		// 한글처리를 해줘야 한다.
		boolean duplicated =  service.isIdDuplicated(id);
		if(duplicated)
			return "true";
		return "false";
	}
	
	
	@GetMapping("email-duplicated-error")
	@ResponseBody // 뷰를 찾지 말아라.. ResponseBody
	public String emailDuplicatedError() {
		// 한글처리를 해줘야 한다. 
		return "<script>alert('이미 가입된 이메일입니다..'); location.href='join-email';</script>";
	}
	
	@GetMapping("join-invalide-error")
	@ResponseBody // 뷰를 찾지 말아라.. ResponseBody
	public String joinInvalideError() {
		// 한글처리를 해줘야 한다. 
		return "<script>alert('계산식이 올바르지 않습니다.'); location.href='join-reg';</script>";
	}
	
	@PostMapping("join-email")
	public String joinEmail(String email, HttpServletResponse response) {
		
		// 기 등록자 이메일 체크
		// 오류 페이지로 가야하나... 메시지 박스를 던져야 하나...
		boolean duplicated =  service.isEmailDuplicated(email);
		System.out.println(duplicated);
		if(duplicated)
			return "redirect:email-duplicated-error";
		
		//--------------------------------------------------------------
		
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
/*		if(key.equals("") || joinId.equals("") || !key.equals(joinId))
			return "member.join-error";*/
		// join-error페이지 이동
			

		
		// newelcture@namo.com 에서 앞에 newelcture만 발췌하는 코드
		String uid = email.split("@")[0];
		
		model.addAttribute("uid", uid);
		model.addAttribute("email", email);
		
		return "member.join-reg";
		
	}
	
	// 동적으로 이미지 만들기
	@GetMapping("moonjae.jpg")
	public void moonjae(HttpSession session, HttpServletResponse response) throws IOException {
		
		Random rand = new Random();
		int x = rand.nextInt(100);
		int y = rand.nextInt(10);
		
/*		int x = (int) (Math.random() * 50) + 1;
		int y = (int) (Math.random() * 9) + 1;*/
		
		String fmtString = String.format("%d+%d=", x,y);
		
		// 세션 값에 넣기
		session.setAttribute("moonjae", x+y); // checker
		
		// 메모리 상의 이미지가 필요하다. 비트맵이라는 도화지
		BufferedImage img = new BufferedImage(60, 30, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = img.createGraphics();
		g.setFont(new Font("돋움", 0, 13)); // 글씨체, 스타일, 크기
		g.setColor(Color.WHITE); 
		g.fillRect(0, 0, 60, 30); 
		g.setColor(Color.BLACK); 
		g.drawString(fmtString, 5, 19);
		response.setContentType("image/png");
		ImageIO.write(img, "png", response.getOutputStream()); // 예외처리 원래는 해야 함
		
	}
	
	@PostMapping("join-reg")
	public String joinReg(
			Member member, 
			@RequestParam("photo-file") MultipartFile photoFile,
			Integer moonjae,
			HttpServletRequest request) {
		
		// 사진을 보냄
		// resuorces/users/계정명/
		// "/resources/users/newlecture/photo1.jpg 
		// d:\home\www\ROOT\resources/users\newlecture
		// unix -> /var/local/web/resources/users/newlecture
		
		HttpSession session = request.getSession();
		Integer moonjaeSaved = (Integer) session.getAttribute("moonjae");
		System.out.println(moonjaeSaved);
		System.out.println(moonjae);
		
		if(moonjaeSaved != moonjae) { // 유효하지 않은 값인 경우
			System.out.println("문제가 다릅니다.");
			return "redirect:join-invalide-error";
		}
		
		// 사진 저장 path 찾기
		String resLocation = "/resources/users/newlec/";
		
		ServletContext context = request.getServletContext();
		
		String homeDir = context.getRealPath(resLocation);
		
 		String uploadedFileName = photoFile.getOriginalFilename();
 		
 		String filePaht = homeDir +  uploadedFileName;
 		
 		System.out.println(filePaht);
 		
 		// 사진 저장
 		File dir = new File(homeDir);
 		
 		if(!dir.exists())
 			dir.mkdirs();
 		
 		try {
			InputStream fis = photoFile.getInputStream();
			FileOutputStream fos = new FileOutputStream(filePaht);
			
			byte[] buf = new byte[1024];
			int size = 0;

			// fis에서 읽어서 fos으로 복사하기
			while((size = fis.read(buf, 0, buf.length)) > 0) // 0보다 클때까지
				fos.write(buf, 0, size);
			
			fis.close();
			fos.close();
			
		} catch (IOException e) {
			e.printStackTrace();
			//에러페이지 만들어야 함
		}
 		
 		//암호화
 		PasswordEncoder encoder = new BCryptPasswordEncoder();
 		
 		String encodedPwd = encoder.encode(member.getPwd());

 		member.setPhoto(uploadedFileName);
 		member.setPwd(encodedPwd);
 		
 		service.insertMember(member); // 암호화 한 서비스
 		
		return "redirect:../index";
		
	}
	
	@PostMapping("reset-pwd")
	public String resetPwd(String id, HttpServletResponse response) {
		
		Member member = service.getMember(id);
		String email = member.getEmail();
		
		
		//---unique key generationg-----------------------------------------------------------
		
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

		
		//---email send-----------------------------------------------------------
		
		// 메시지 만들기
		MimeMessage message = mailSender.createMimeMessage();
		try {
			//마임 = 멀티미디어 포함
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
			helper.setFrom("noreply@newlecture.com");
			helper.setTo(email);
			helper.setSubject("뉴렉처 비밀번호를 위한 인증메일");
			// 이메일보낼때.. 파라미터 이름 줄여서 쓰자~
			helper.setText("<a href='http://localhost:8080/member/pwd-edit?id="+digest+"&em="+email+"'>비밀번호재설정 링크</a>", true);
		} catch (MessagingException e) {
			e.printStackTrace();
		} // 쉽게 구현해주는 객체
		
		mailSender.send(message);

		
		return "member.reset-pwd";
	}
	
	@GetMapping("reset-pwd")
	public String resetPwd() {
		return "member.reset-pwd";
	}
	
	@GetMapping("pwd-edit")
	public String pwdEdit() {
		
		return "member.pwd-edit";
	}
	
	
	
}