package com.gura.spring05;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gura.spring05.users.dto.UsersDto;
import com.gura.spring05.users.service.usersService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
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
}
