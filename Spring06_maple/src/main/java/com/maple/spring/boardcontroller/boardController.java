package com.maple.spring.boardcontroller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.maple.spring.boarddto.BoardDto;
import com.maple.spring.boardservice.BoardService;
import com.maple.spring.usersservice.UsersService;

@Controller
public class boardController {
	
	private BoardService service;
	
	@RequestMapping("/board/insert_form")
	public String insertForm() {
		return "board/insert_form";
	}
	
	@RequestMapping("/board/insert")
	public Map<String, Object> insert(BoardDto dto) {
		service.insertContent(dto);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("isSuccess", true);
		return map;
	}
}
