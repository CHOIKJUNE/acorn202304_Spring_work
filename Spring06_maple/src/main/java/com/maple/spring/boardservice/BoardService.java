package com.maple.spring.boardservice;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

import com.maple.spring.boarddto.BoardDto;

public interface BoardService {
	public void insertContent(BoardDto dto, ModelAndView mView);
	public void getBoardList(BoardDto dto, HttpServletRequest request);
}
