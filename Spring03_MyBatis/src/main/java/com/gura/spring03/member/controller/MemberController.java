package com.gura.spring03.member.controller;

import java.util.List;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gura.spring03.member.dao.MemberDao;
import com.gura.spring03.member.dto.MemberDto;

@Controller
public class MemberController {
	
	//MemberDao인터페이스를 구현하는게 아니라 구현한 클래스와 연결시키는 개념
	@Autowired
	private MemberDao dao;
	
	/*
	 * @RequestMapping에 method속성의 값을 명시하지 않으면 요청 방식을 가리지 않고
	 * 경로만 맞으면 모두 처리해준다.(GET, POST, PUT, DELETE)
	 * 
	 * method속성의 값을 명시하면 경로가 맞더라도 요청방식이 다르면 처리해주지 않는다.
	 * 일반적으로 POST인 경우에는 요청방식을 명시해주는 것이 좋다.
	 */

	//회원 정보 수정 처리
	@RequestMapping(method = RequestMethod.POST, value = "/member/update")
	public String update(MemberDto dto) {
		dao.update(dto);
		return "member/update";
	}
	
	//회원추가 요청처리
	@RequestMapping("/member/insert")
	public String insert(MemberDto dto) {
		//MemberDao객체를 이용해서 DB에 저장
		dao.insert(dto);
		//view page로 forward이동해서 응답
		return "member/insert";
	}
	
	//회원추가 폼 요청 처리
	@RequestMapping("/member/insertform")
	public String insertform() {
		return "member/insertform";
	}
	
	//회원 목록 보기 요청 처리
	@RequestMapping("/member/list")
	public String list(HttpServletRequest request) {
		//회원목록을 얻어와서
		List<MemberDto> list = dao.getList();
		//request scope에 담고
		request.setAttribute("list", list);
		// /WEB-INF/views/member/list.jsp페이지로 forward이동해서 응답
		return "member/list";
	}
	
	//회원 정보 수정폼 요청 처리
	@RequestMapping("/member/updateform")
	public ModelAndView updateform(ModelAndView mView, int num) {
		//수정할 회원의 정보를 얻어온다.
		MemberDto dto = dao.getData(num);
		/*
		 * 수정할 회원의 정보를 ModelAndView 객체의 addObject(key, value)메서드를 이용해서 얻는다.
		 * ModelAndView객체에 담은 값은 결국 HttpServletRequest객체에 담긴다.(request scope에 담긴다)
		 */
		mView.addObject("dto", dto);
		//view page의 위치도 ModelAndView객체에 담아서 리턴해야한다.
		mView.setViewName("member/updateform");
		//모델(data)와 view page의 정보가 모두 담긴 ModelAndView객체를 리턴해주면 spring이 알아서 처리해준다.
		return mView;
	}
	
	//회원 정보 삭제 처리
	@RequestMapping("/member/delete")
	public String delete(@RequestParam() int num) {
		dao.delete(num);
		//목록보기로 Redirect응답
		return "redirect:/member/list";
	}
}
