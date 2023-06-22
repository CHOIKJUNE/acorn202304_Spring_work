package com.gura.spring03.guest.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gura.spring03.guest.dao.GuestDao;
import com.gura.spring03.guest.dto.GuestDto;
import com.gura.spring03.guest.service.GuestService;
import com.gura.spring03.member.dao.MemberDao;
import com.gura.spring03.member.dto.MemberDto;

@Controller
public class GuestController {
	
	@Autowired
	private GuestService service;
	
	@RequestMapping("/guest/list")
	public ModelAndView list(ModelAndView mView) {
		 //방명록 목록 얻어오기
		 // 서비스의 메서드를 호출해서 ModelAndView객체의 참조값을 전달하면 방명록 목록이 담긴다.
	      service.getGuestList(mView);
	      //view page 정보도 담고
	      mView.setViewName("guest/list");
	      /*
	       *  두개의 정보가 모두 담긴 ModelAndView 객체를 리턴해주면 
	       *  addObject(key, value) 를 통해서 담은 정보는 request scope 에 담기고
	       *  setViewName(view page 위치) 를 통해서 담은 정보는 해당 view page 로 forward 이동해서 응답된다. 
	       */
	      return mView;

	}
	
	@RequestMapping("/guest/private/insertform")
	public ModelAndView insertform(ModelAndView mView) {
		mView.setViewName("guest/private/insertform");
		return mView;
	}
	
	@RequestMapping("/guest/private/insert")
	public String insert(GuestDto dto) {
		service.addGuest(dto);
		return "redirect:/guest/list";
	}
	
	@RequestMapping("/guest/private/updateform") 
	public ModelAndView updateform(ModelAndView mView, int num) {
		service.getGuestInfo(mView, num);
		//view page로 forward이동해서 수정폼 응답하기
		mView.setViewName("guest/private/updateform");
		return mView;
	}
	
	@RequestMapping("/guest/private/update")
	public String update(GuestDto dto) {
		service.updateGuest(dto);
		return "redirect:/guest/list";
	}
	
	@RequestMapping("/guest/private/delete")
	public String delete(GuestDto dto) {
		service.deleteGuest(dto);
		return "redirect:/guest/list";
	}
}
