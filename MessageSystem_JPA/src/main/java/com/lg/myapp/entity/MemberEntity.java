package com.lg.myapp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 해당 클래스를 활용해서 DB 테이블을 만들것이기 때문에
// DB 테이블처럼 필드를 구성할 클래스다! 라는 어노테이션
@Entity
@SequenceGenerator( // Oracle 시퀀스 번호 생성
		name = "member_seq_gen", // JPA용 시퀀스 이름
		sequenceName = "member_seq", // DB에서 사용될 시퀀스 이름
		allocationSize = 1 // 1씩 증가
)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MemberEntity {
	
	// 이 클래스에 있는 필드를 테이블의 컬럼처럼 만들것임!
	// 필수사항
	// 각 레코드를 식별할 수 있는 PK가 존재해야 함! -> @Id
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
					generator = "member_seq_gen")
	private Long idx;
	
	@Column(unique = true)
	private String email;
	
	@Column(nullable = false)
	private String pw;
	
	@Column(length = 100)
	private String tel;
	
	private String address;
}
