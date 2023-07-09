package com.maple.spring.boardcontroller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.maple.spring.boarddto.BoardDto;
import com.maple.spring.boardservice.BoardService;
import com.maple.spring.usersservice.UsersService;

@Controller
public class boardController {
	
	@Autowired
	private BoardService service;
	
	@RequestMapping("/board/insert_form")
	public String insertForm() {
		return "board/insert_form";
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/board/insert")
	@ResponseBody
	public Map<String, Object> insert(@RequestBody BoardDto dto, ModelAndView mView ) {
		System.out.println(dto.getTitle());
		System.out.println(dto.getWriter());
		System.out.println(dto.getContent());
		service.insertContent(dto, mView);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("isSuccess", true);
		return map;
	}
}