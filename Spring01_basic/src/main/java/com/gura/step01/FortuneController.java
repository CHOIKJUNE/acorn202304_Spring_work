package com.gura.step01;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FortuneController {
	
	@RequestMapping("/fortune1")
	public String fortune(HttpServletRequest request) {
		//오늘의 운세
		String fortuneToday = "동쪽으로 가면 귀인을 만나요";
		request.setAttribute("fortuneToday", fortuneToday);
		//WEB-INF/views/fortune.jsp페이지로 forward이동해서 응답하기
		return "fortune1";
	}
	
	@RequestMapping("/fortune2")
	public ModelAndView fortune2() {
		//오늘의 운세
		String fortuneToday = "동쪽으로 가면 귀인을 만나요";
		
		//HttpServletRequest객체 대신에 ModelAndView객체를 생성해서
		ModelAndView mView = new ModelAndView();
		//view page에 전달할 내용을 담는다.
		mView.addObject("fortuneToday", fortuneToday);
		//view page의 위치도 담는다.
		mView.setViewName("fortune2");
		//모델과 view page정보가 모두 담겨있는 ModelAndView객체를 리턴해주면 똑같이 동작한다.
		return mView;
	}

	// ModelAndView를 매개변수로 선언하면 스프링 프레임워크가 알아서 객체생성 후 참조값을 넣어준다.
	@RequestMapping("/fortune3")
	public ModelAndView fortune3(ModelAndView mView) {
		//오늘의 운세
		String fortuneToday = "동쪽으로 가면 귀인을 만나요";
		//view page에 전달할 내용을 담는다.
		mView.addObject("fortuneToday", fortuneToday);
		//view page의 위치도 담는다.
		mView.setViewName("fortune3");
		//모델과 view page정보가 모두 담겨있는 ModelAndView객체를 리턴해주면 똑같이 동작한다.
		return mView;
	}
}
