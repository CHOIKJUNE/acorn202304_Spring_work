package com.maple.spring.boarddao;

import com.maple.spring.boarddto.BoardDto;

public interface BoardDao {
	public void insert(BoardDto dto);
	public void update();
}
