package com.gura.spring05.cafe.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gura.spring05.cafe.dto.CafeDto;
import com.gura.spring05.cafe.service.CafeServiceImpl;

@Controller
public class CafeController {
	
	@Autowired
	private CafeServiceImpl service;
	
	//댓글 삭제 요청 처리
	@RequestMapping("/cafe/comment_delete")
	@ResponseBody
	public Map<String, Object> commentDelete(HttpServletRequest request) {
		service.deleteComment(request);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("isSuccess", true);
		// {"isSuccess": true}형식의 JSON문자열이 응답되도록 한다.
		return map;
	}
	//새로운 댓글 저장 요청 처리
	@RequestMapping("/cafe/comment_insert")
	public String commentInsert(HttpServletRequest request, int ref_group) {
		//새로운 댓그릉ㄹ 저장하는 로직을 수행한다.
		//request영역에는 content, target_id // ref_group가 담긴 상태
		service.saveComment(request);
		//ref_group은 원글의 글번호이기 때문에 원글 자세히보기로 다시 리다일렉트 이동된다.
		return "redirect:/cafe/detail?num="+ref_group;
	}
	
	//댓글 더보기 요청 처리
	@RequestMapping("/cafe/ajax_comment_list")
	public String commentList(HttpServletRequest request) {
		//테스트를 위해 시간 지연시키기
		try {
			Thread.sleep(3000);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
		service.moreCommentList(request);
		return "cafe/ajax_comment_list";
	}
	//혹시라도 CafeDto객체를 받아올 수 있는 경우 String이 아니라 ModelAndView로 해야할수도
	@RequestMapping("/cafe/list")
	public String cafeList(CafeDto dto, HttpServletRequest request) {
		service.getList(dto, request);
		return "cafe/list";
	}
	
	@RequestMapping("/cafe/insertform")
	public String insertForm() {
		return "cafe/insertform";
	}
	
	//Controller에서 비즈니스 로직을 많이 처리한 느낌이다..
	@RequestMapping(method=RequestMethod.POST, value="/cafe/insert")
	public String insert(CafeDto dto, HttpSession session) {
		String writer = (String)session.getAttribute("id");
		dto.setWriter(writer);
		service.saveContent(dto);
		return "cafe/insert";
	}
	
	@RequestMapping("/cafe/detail")
	public String detail(HttpServletRequest request) {
		//request영역에 num, keyword, condition파라미터들을 담는다.
		service.getDetail(request);
		return "cafe/detail";
	}
	
	@RequestMapping("/cafe/updateform")
	public String updateform(HttpServletRequest request) {
		service.getData(request);
		return "cafe/updateform";
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/cafe/update") 
	public String update(CafeDto dto) {
		service.updateContent(dto);
		return "cafe/update";
	}
	
	@RequestMapping("/cafe/delete")
	public String delete(int num, HttpServletRequest request) {
		service.deleteContent(num, request);
		return "redirect:/cafe/list";
	}
}
