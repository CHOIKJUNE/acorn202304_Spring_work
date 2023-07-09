package com.maple.spring.boarddao;

import java.util.List;

import com.maple.spring.boarddto.BoardDto;
 
public interface BoardDao {
	public void insert(BoardDto dto);
	public void update();
	public List<BoardDto> getList(BoardDto dto);
	public int getCount();
}
