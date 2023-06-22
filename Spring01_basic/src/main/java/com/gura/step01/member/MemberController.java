package com.gura.step01.member;

import com.gura.step01.member.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MemberController {
	
	@RequestMapping("/member/delete")
	public String delete(int num) {
		System.out.println(num + "번 회원을 삭제합니다.");
		/*
		 * Redirect응답을 할때는 "redirect: 새로 요청할 경로" 형식으로 view page정보를 작성하면 된다.
		 */
		return "redirect:/fortune1";
	}
	// "/member/insertform"요청을 처리할 메서드 만들기
	@RequestMapping("/member/insertform")
	public String insertform(HttpServletRequest request) {
		return "member/insertform";
	}
	/*
	 *  [요청 파라미터 추출하는 방법1]
	 *  HttpServletRequest객체를 Controller메서드로 전달받아 직접 추출한다.
	 */
	@RequestMapping("/member/insert")
	public String insert(HttpServletRequest request) {
		try {
		int num = Integer.valueOf(request.getParameter("num"));
		String name = request.getParameter("name");
		String addr = request.getParameter("addr");
		System.out.println(num+ "|" + name + "|" + addr);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "member/insert";
	}
	
	//2번째 방법
	@RequestMapping("/member/insert2")
	public String insert2(int num, String name, String addr) {
		System.out.println(num+ "|" + name + "|" + addr);
		return "member/insert2";
	}
	
	/*
	 * 파라미터명과 동일한 필드명을 가지고 있는 dto클래스 type을 메서드의 매개변수로 선언해놓으면 
	 * 자동으로 추출해서 dto에 추출한 값을 setter메서드를 이용해서 넣은 다음 해당 dto객체의 참조값이 
	 * 전달된다.
	 */
	@RequestMapping("/member/insert3")
	public String insert3(MemberDto dto) {
		System.out.println(dto.getNum()+ "|" + dto.getName() + "|" + dto.getAddr());
		return "member/insert3";
	}
}
