package com.lg.myapp.controller;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.lg.myapp.mapper.BoardMapper;
import com.lg.myapp.model.Board;

@Controller
public class BoardController {
	
	@Autowired
	private BoardMapper mapper;
	
	// 상세보기 페이지로 이동 + 내가 선택한 특정 게시물 데이터 가져오기
	@GetMapping("/boardDetail/{a}") // 실제 요청 URL : /boardDetail?idx=3
	public String boardDetail(@PathVariable int a, Model model) {
		Board board = mapper.boardDetail(a);
		model.addAttribute("board", board);
		return "BoardDetail";
	}
	
	// 파일 업로드 기능
	@PostMapping("/boardUpload") //title, writer, content, uploadFile
	public String boardUpload(
			Board board, // title, writer, content
			@RequestParam("uploadFile") MultipartFile file
			) throws Exception { //경로가 잘못됐다던지, 용량을 초과한다던지 예외처리		
		
		// DB에 사진파일 자체를 저장하는게 아닌 파일의 이름만 저장
		// 실제 파일은 프로젝트 내에 폴더를 만들어서 저장
		// 이미지 가져오는 형태 : <img src="경로/${DB에서 가져온 파일명}">
		// 파일의 원래 이름 가져오기
		String originFileName= file.getOriginalFilename();

		// 만약 내가 파일을 업로드 하는데 같은 이름의 파일이 이미 서버에 있다면????
		// 파일 이름 중복 제거 후 폴더에도 저장하고, DB에도 중복제거가 된 파일명이 저장
		// 나비.jps가 이미 서버에 있다는걸 확인한 후, 만약 있다면 나비1.jpg
		// 만약 또 있다면 나비2.jpg
		// 파일 이름과 확장자 분리
		// lastIndexOf(".") -> 해당 문자가 마지막에 있는 위치(인덱스)를 알려줌 ex) 예시.jpg
		// substring(시작인덱스, 끝인덱스) -> 문자를 시작인덱스부터 끝인덱스까지 잘라줌
		// but, 끝인덱스는 포함X
		String baseName = originFileName.substring(0, originFileName.lastIndexOf("."));
		// substring(시작인덱스) -> 시작인덱스부터 끝까지 잘라서 반환 -> .jpg, .png
		String ext = originFileName.substring(originFileName.lastIndexOf("."));
		
		// 만약에 중복되는 이름이 있을 시 중복제거한 새로운 이 름을 담을 변수 선언
		String newFileName = originFileName;
		
		// 파일을 저장할 경로를 변수로 정리하기
		String savePath = System.getProperty("user.dir")+"/src/main/resources/static/upload/";
		File savedFile = new File(savePath + newFileName);
		
		// 중복제거 -> 예시1.jpg -> 예시2.jpg -> ... 
		// 1씩 증가되는 변수 필요
		int cnt = 1;
		while(savedFile.exists()) {
			newFileName = baseName + cnt + ext;
			savedFile = new File(savePath + newFileName);
			cnt++;
		}
		
		// 실제 파일을 폴더에 저장
		file.transferTo(savedFile);
		
		// DB에 데이터 저장
		board.setFilename(newFileName); // title, writer, filename, content
		mapper.uploadBoard(board);
		
		return "redirect:/boardMain";
	}
	
	// BoardWrite페이지로 이동
	@GetMapping("/boardWrite")
	public String boardWrite() {
		return "boardWrite";
	}
	
	// BoardMain페이지로 이동 + DB에 있는 모든 게시물들을 가져와서 목록에 띄우기
	@GetMapping("/boardMain")
	public String boardMain(Model model) {
		
		List<Board> boardList = mapper.boardList();
		model.addAttribute("boardList", boardList);
		
		return "BoardMain";
	}
	
	// 삭제
	@GetMapping("/boardDelete/{idx}")
	public String boardDelete(@PathVariable int idx) {
		mapper.boardDelete(idx);
		return "redirect:/boardMain";
	}
	
}
