package com.maple.spring.boardservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.maple.spring.boarddao.BoardDao;
import com.maple.spring.boarddto.BoardDto;

@Service
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	private BoardDao dao;

	@Override
	public void insertContent(BoardDto dto) {
		try {
			dao.insert(dto);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
