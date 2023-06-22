package com.gura.hello;

import java.util.List;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	// 이 프로젝트의 최상위 경로 요청이 오면
	@RequestMapping("/")
	//생성자에 임의로 객체를 선언할 수 있다.
	public String home(HttpServletRequest request) {
		// home.jsp페이지에서 필요한 모델(data)를 HttpServletRequest객체에 담아두기
		List<String> noticeList = new ArrayList<String>();
		noticeList.add("날씨가 많이 더워지고 있어요.");
		noticeList.add("어떡하죠");
		noticeList.add("물을 많이 마셔요");
		request.setAttribute("noticeList", noticeList);
		// /WEB-INF/views/home.jsp페이지로 forward이동해서 응답하겠다는 의미
		// "home"이라는 문자열을 리턴하면 앞에 "/WEB-INF/views/"뒤에 ".jsp"가 자동으로 붙는다.
		return "home";
	}
	
	@RequestMapping("/fortune")
	public String fortune(HttpServletRequest request) {
		//오늘의 운세라고 가정하자
		String fortuneToday = "오늘은 되는게 없어요";
		request.setAttribute("fortuneToday", fortuneToday);
		// "/WEB-INF/views/" + "test/fortune" + ".jsp"
		return "test/fortune";
	}
	
}
