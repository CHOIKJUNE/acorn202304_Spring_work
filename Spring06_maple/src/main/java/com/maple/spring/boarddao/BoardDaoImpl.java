package com.maple.spring.boarddao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.maple.spring.boarddto.BoardDto;

@Repository
public class BoardDaoImpl implements BoardDao{
	
	@Autowired
	private SqlSession session;

	@Override
	public void insert(BoardDto dto) {
		 session.insert("board.insert", dto);
	}

	@Override
	public void update() {
		
	}

	@Override
	public List<BoardDto> getList(BoardDto dto) {
		return session.selectList("board.getList", dto);
	}

	@Override
	public int getCount() {
		return session.selectOne("board.getCount");
	}
	
	
	
}
