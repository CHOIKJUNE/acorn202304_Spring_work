package com.gura.spring03.users.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gura.spring03.users.dao.UsersDao;
import com.gura.spring03.users.dto.UsersDto;

@Controller
public class UsersController {
	
	@Autowired
	UsersDao dao;
	
	@RequestMapping("/users/signupform")
	public String signupform() {
		return "users/signupform";
	}
	
	@RequestMapping("/home")
	public String home() {
		return "home";
	}
	
	@RequestMapping("/users/signup")
	public String signup(UsersDto dto) {
		dao.insert(dto);
		return "users/signup";
	}
	
	@RequestMapping("/users/loginform")
	public String loginform() {
		return "users/loginform";
	}
	
	@RequestMapping("/users/login")
	public String login(HttpServletRequest request, HttpSession session) {
		//회원가입한 회원의 정보를 가져와서 대치시켜야한다.
		String id = request.getParameter("id");
		UsersDto dto = dao.getData(id);
		if(id.equals(dto.getId())) {
			session.setAttribute("id", dto.getId());
			session.setAttribute("pwd", dto.getPwd());
			return "users/login";
		}
		return "users/loginform";
	}
	
	@RequestMapping("/users/logout")
	public String logout(HttpSession session, HttpServletRequest request) {
		session.removeAttribute("id");
		return "redirect:/";  
	}
}
