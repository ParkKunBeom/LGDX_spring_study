package com.lg.myapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.lg.myapp.entity.MemberEntity;
import com.lg.myapp.repository.MemberRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class MemberController {
	
	// 우리가 만든 인터페이스를 선언 후 주입받아서 사용 -> @Autowired
	@Autowired
	private MemberRepository repo;
	
	// 회원종보조회 기능(페이지이동과 동시에 DB에서 모든 회원정보를 가져와야 함)
	@GetMapping("/showForm")
	public String showForm(Model model) {//select * from member_entity
		List<MemberEntity> list = repo.findAll();
		model.addAttribute("list", list);
		return "ShowMember";
	}
	
	// 회원정보 수정 기능
	@PostMapping("/memberUpdate")
	public String memberUpdate(MemberEntity mem, HttpSession session) { //idx(PK), email, pw, tel, address
		// PK값을 넘겼을 때 없다면 insert, 있다면 update
		repo.save(mem);
		session.setAttribute("mem", mem);
		return "redirect:/";
	}
	
	// 회원정보수정 페이지 이동
	@GetMapping("/updateForm")
	public String updateForm() {
		return "UpdateMember";
	}
	
	// 로그아웃 기능
	@GetMapping("/memberLogout")
	public String memberLogout(HttpSession session) {
		session.invalidate(); // 세션 전체 삭제
		return "redirect:/";
	}
	
	// 로그인 기능
	@PostMapping("/memberLogin")
	public String memberLogin(MemberEntity mem, HttpSession session) { //email, pw, null, null
		mem = repo.findByEmailAndPw(mem.getEmail(), mem.getPw());
		// idx, email, pw, tel, address
		
		if(mem!=null) {
			session.setAttribute("mem", mem);
		}
		
		return "redirect:/";
	}
	
	// 회원가입 기능
	@PostMapping("/memberJoin")
	public String memberJoin(MemberEntity mem, Model model) { // email, pw, tel, address
		repo.save(mem);
		model.addAttribute("mem", mem);
		return "JoinSuccess";
	}
	
	@GetMapping("/")
	public String main() {
		return "Main";
	}
}
