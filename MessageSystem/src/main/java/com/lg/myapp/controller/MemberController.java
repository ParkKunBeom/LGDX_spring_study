package com.lg.myapp.controller;

import com.lg.myapp.MessageSystemApplication;
import com.lg.myapp.mapper.MemberMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.lg.myapp.model.Member;

// @Controller
// 이 클래스는 일반 자바 클래스가 아니라 클라이언트의 요청을 받는 
@Controller
public class MemberController {

	// 인터페이스는 바디가 없는 추상메서드만 존재하기 때문에 객체 생성X
	@Autowired
	private MemberMapper mapper;

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
