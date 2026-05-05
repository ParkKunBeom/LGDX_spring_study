package com.lg.myapp.controller;

import com.lg.myapp.MessageSystemApplication;
import com.lg.myapp.mapper.MemberMapper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lg.myapp.model.Member;

import jakarta.servlet.http.HttpSession;

// @Controller
// 이 클래스는 일반 자바 클래스가 아니라 클라이언트의 요청을 받는 
@Controller
public class MemberController {
	
	// 인터페이스는 바디가 없는 추상메서드만 존재하기 때문에 객체 생성X
	@Autowired
	private MemberMapper mapper;
	
	// 전체회원정보 페이지로 이동 + DB에서 정보도 가져오기
	@GetMapping("/showForm")
	public String showForm(Model model) {
		
		List<Member> list = mapper.showMember();
		model.addAttribute("list",list);
		return "ShowMember";
	}

	// 회원수정 기능
	@PostMapping("/memberUpdate")
	public String memberUpdate(Member mem, HttpSession session) {
		mapper.memberUpdate(mem);
		session.setAttribute("mem", mem);
		return "redirect:/";
	}
	
	// 회원수정 페이지로 이동
	@GetMapping("/updateForm")
	public String updateForm() {
		return "UpdateMember";
	}
	
	
	//로그아웃 기능
	@GetMapping("/memberLogout")
	public String memberLogout(HttpSession session) {

		session.invalidate(); // 세션 전체 삭제

		return "redirect:/"; // 메인으로 이동
	}

	// 로그인 기능
	@PostMapping("/memberLogin")
	public String memberLogin(Member mem, HttpSession session) { //email, pw, null, null
		System.out.println(mem.toString());
		
		mem = mapper.memberLogin(mem);
		
		if(mem!=null) {
			session.setAttribute("mem", mem);
		}
		
		return "redirect:/";
	}
	
	// 회원가입 기능
	private final MessageSystemApplication messageSystemApplication;

	MemberController(MessageSystemApplication messageSystemApplication) {
		this.messageSystemApplication = messageSystemApplication;
	}

	@PostMapping("/memberJoin") // contextPath + "/memberJoin" 요청이 들어오면 아래 메소드 실행
	public String memberJoin(Member mem, Model model) {
		System.out.println("[회원가입 컨트롤러]");
		System.out.println(mem.toString());
		mapper.memberJoin(mem);

		model.addAttribute("mem", mem);
		return "JoinSuccess";
	}

	// get방식으로 "/"라고 요청이 들어오면 바로 아래에 있는 메소드를 실행시키겠습니다!
	@GetMapping("/") // contextPath() + 로 요청이 들어왔을 때 아래 메소드 실행
	public String main() {
		return "Main"; // .html 확장자는 우리 눈에 보이진 않지만 뷰 리볼버가 붙여줌
	}
}
