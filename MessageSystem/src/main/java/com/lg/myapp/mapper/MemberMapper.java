package com.lg.myapp.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.lg.myapp.model.Member;

// 이 인터페이스도 Spring Container로 올라가게 됨
// mapper파일임을 알려줘야 함!!
@Mapper
public interface MemberMapper {
	
	public void memberJoin(Member mem);
}
