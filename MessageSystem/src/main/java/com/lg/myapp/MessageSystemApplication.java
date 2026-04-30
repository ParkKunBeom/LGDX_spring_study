package com.lg.myapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// - 이 클래스의 위치를 기준으로 해서 하위의 설정 내용들을 읽음
// - 스프링 부트의 설정을 자동화 하는 기능
// - 따라서 해당 클래스는 프로젝트 최상단에 위치해야 함!
@SpringBootApplication
public class MessageSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(MessageSystemApplication.class, args);
	}

}
