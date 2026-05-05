package com.lg.myapp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.lg.myapp.model.Board;

@Mapper
public interface BoardMapper {

	public void uploadBoard(Board board);

	public List<Board> boardList();

	public Board boardDetail(int a);

	public void boardDelete(int idx);

	
}
