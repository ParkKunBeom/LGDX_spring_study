package com.lg.myapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

// @Controller
// 이 클래스는 일반 자바클래스가 아니라 클라이언트의 요청을 받는 Controller임을 명시
@Controller
public class MemberController {
	
	// 회원가입 기능
	@PostMapping("/memberJoin") // contextPath + "/memvverJoin" 요청이 들어오면 아래 메소드 실행
	public String memberJoin() {
		System.out.println("[회원가입 컨트롤러]");
		return "JoinSuccess";
	}
	
	// get방식으로 "/"라고 요청이 들어오면 바로 아래에 있는 메소드를 실행시키겠습니다!
	@GetMapping("/") // contextPath(msg) + / 로 요청이 들어왔을 때 아래 메소드 실행
	public String main() {
		return "Main"; // .html 확장자는 우리 눈에 보이진 않지만 뷰 리졸버가 붙여줌
	}
}
