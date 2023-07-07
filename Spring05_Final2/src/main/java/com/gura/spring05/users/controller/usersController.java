package com.gura.spring05.users.controller;

import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.gura.spring05.users.dto.UsersDto;
import com.gura.spring05.users.service.usersService;

@Controller
public class usersController {
	/*
	 * GET방식 /users/signup_form요청을 처리할 메서드
	 *  - 요청방식이 다르면 실행되지 않는다.
	 */
	@Autowired
	private usersService service;
	
	@RequestMapping("/users/signup_form")
	public String signupform() {
		return "users/signup_form";
	}
	
	//회원가입 요청처리
	@RequestMapping(method = RequestMethod.POST, value="/users/signup")
	public ModelAndView signup(ModelAndView mView, UsersDto dto) {
		service.addUser(dto);
		mView.setViewName("users/signup");
		return mView;
	}
	
	@RequestMapping("/users/delete")
	public ModelAndView delete(HttpSession session, ModelAndView mView) {
		service.deleteUser(session, mView);
		mView.setViewName("users/delete");
		return mView;
	}
	
	//로그인폼 요청처리
	@RequestMapping(method = RequestMethod.GET, value="/users/loginform")
	public String loginform() {
		return "users/loginform";
	}
	
	//로그인 요청처리
	@RequestMapping("/users/login")
	public ModelAndView login(ModelAndView mView, UsersDto dto, String url, HttpSession session) {
		/*
		 * 서비스에서 비즈니스 로직을 처리할 때 필요로 하는 객체를 컨트롤러에서 직접 전달해야한다.
		 * 주로 HttpServletRequest, HttpServletResponse, HttpSession, ModelAndView
		 * 등의 객체이다
		 */
		service.loginProcess(dto, session);
		
		//로그인 후에 가야할 목적지 정보를 인코딩하지 않는것과 인코딩 한 것을 모두 ModelAndView객체에 담고
		String encodeUrl = URLEncoder.encode(url);
		mView.addObject("url", url);
		mView.addObject("encodeUrl", encodeUrl);
		
		//view page로 forward이동해서 응답한다
		mView.setViewName("users/login");
		return mView;
	}
	
	//로그아웃 요청처리
	@RequestMapping("/users/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("id");
		return "users/logout";
	}
	
	@RequestMapping("/users/info")
	public ModelAndView info(ModelAndView mView, HttpSession session) {

		service.getInfo(session, mView);

		mView.setViewName("users/info");
		return mView;
	}
	
	@RequestMapping("/users/pwd_updateform")
	public String pwdUpdateform() {
		return "users/pwd_updateform";
	}
	
	//비밀번호 수정요청 처리
	@RequestMapping("/users/pwd_update")
	public ModelAndView pwdUpdate(UsersDto dto, ModelAndView mView, HttpSession session) {
		//서비스에 필요한 객체의 참조값을 전달해서 비밀번호 수정 로직을 처리한다.
		service.updateUserPwd(session, dto, mView);
		//view page로 forward이동해서 작업 결과를 응답한다.
		mView.setViewName("users/pwd_update");
		return mView;
	}
	
	//개인정보 수정폼 요청처리
	@RequestMapping("/users/updateform")
	public ModelAndView updateform(HttpSession session, ModelAndView mView) {
		service.getInfo(session, mView);
		mView.setViewName("users/updateform");
		return mView;
	}
	
	//개인정보 수정 요청처리
	@RequestMapping(value = "/users/update", method = RequestMethod.POST)
	public ModelAndView update(HttpSession session, ModelAndView mView, UsersDto dto) {
		service.updateUser(dto, session);
		mView.setViewName("redirect:/users/info");
		return mView;
	}
	
	
	//ajax프로필 사진 업로드 요청처리
	@RequestMapping(value = "/users/profile_upload", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> profileUpload(HttpServletRequest request, MultipartFile image) {
		//서비스를 이용해서 이미지를 upload폴더에 저장하고 리턴되는 MaP을 리턴해서 json문자열 응답하기
		return service.saveProfileImage(request, image);
	}
}
