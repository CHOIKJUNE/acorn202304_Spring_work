package com.gura.step01;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.gura.step01.member.MemberDto;

/*
 * JSON문자열 응답하는 방법
 * 
 * 1. pom.xml에 jackson-databind dependency를 추가한다.
 * 2. 컨트롤러 메서드에 @ResponseBody어노테이션을 붙여준다.
 * 3. Dto, List, Map등을 컨트롤러에서 리턴해주면 해당 객체에 담긴 정보가 json으로 구성되어서 응답된다.
 */

@Controller
public class TestController {
	
	@ResponseBody //view page의 몸통으로 쓴다는 선언 
	@RequestMapping("/test/json1")
	public String json1() {
		return "{\"num\":1, \"name\": \"김구라\", \"addr\":\"노량진\"}";
	}
	
	//자동으로 json문자열로 변환시켜줌
	@ResponseBody
	@RequestMapping("/test/json2")
	public MemberDto json2() {
		MemberDto dto = new MemberDto();
		dto.setNum(2);
		dto.setName("해골");
		dto.setAddr("원숭이");
		return dto;
	}
	
	//자동으로 json문자열로 변환시켜줌
	@ResponseBody
	@RequestMapping("/test/json3")
	public Map<String, Object> json3() {
		Map<String, Object> map = new HashMap<>();
		map.put("num", 3);
		map.put("name", "원숭이");
		map.put("addr", "동물원");
		return map;
	}
	
	//Array type
	@ResponseBody
	@RequestMapping("/test/json4")
	public List<String> json4() {
		List<String> list = new ArrayList<>();
		list.add("김구라");
		list.add("해골");
		list.add("원숭이");
		return list;
	}
	
	//[{}] type
	@ResponseBody
	@RequestMapping("/test/json5")
	public List<MemberDto> json5() {
		List<MemberDto> list = new ArrayList<>();
		list.add(new MemberDto(1, "김구라", "노량진"));
		list.add(new MemberDto(2, "해골", "행신동"));
		list.add(new MemberDto(3, "원숭이", "동물원"));
		return list;
	}
	
	@ResponseBody
	@RequestMapping("/test/json6")
	public List<Map<String, Object>> json6() {
		List<Map<String, Object>> list = new ArrayList<>();
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("num", 1);
		map1.put("name", "조익준");
		map1.put("addr", "청덕동");
		
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("num", 2);
		map2.put("name", "이용승");
		map2.put("addr", "잠실동");

		Map<String, Object> map3 = new HashMap<String, Object>();
		map3.put("num", 3);
		map3.put("name", "원숭이");
		map3.put("addr", "동물원");
		
		list.add(map1);
		list.add(map2);
		list.add(map3);
		return list;
	}
}
