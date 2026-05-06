package com.lg.myapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lg.myapp.entity.MemberEntity;

import jakarta.transaction.Transactional;

// Mybatis에서 인터페잇 위쪽에 @mapper라는 어노테이션 사용
@Repository //JPA에서 사용하는 어노테이션
public interface MemberRepository extends JpaRepository<MemberEntity, Long>{

	// CRUD기능을 하기 위해서는 특정 클래스를 상속받아야 함!
	// JpaRepository<T, ID> T : EntityType, ID : entity클래스의 PK값의 데이터 타입
	
	// 기본적으로 사용할 수 있는 CRUD
	// 1. findAll()
	// -> select * from 테이블명
	// 2.findByID(PK값)
	// -> select * from 테이블명 where 컬럼명 = PK값
	// 3. save(매개변수 or entity객체)
	// -> insert into 테이블명 values(매개변수 or entity객체)
	// 4. delete(매개변수)
	// -> delete from 테이블명 where 컬럼명 = 매개변수
	
	// 사용자 정의 메소드
	// 로그인 -> select * from 테이블명 where email=#{email} and pw=#{pw}
	// find + (테이블명) + By + 컬럼명 + And + 컬럼명
	// -> findByEmailAndPw()
	public MemberEntity findByEmailAndPw(String email, String pw);
	
	// update member set count = count+1 where idx=2
//	@Transactional //insert, delete, update 실행 시 에러가 발생하면 rollback
//	@Modifying // insert, delete, update 시에는 무조건 써줘야함
//	@Query("update MemberEntity m set m.count = m.count+1 where m.idx= :idx")
//	public void countUp(Long idx);
	
}
