package com.maple.spring.boardservice;

import java.util.List;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import com.maple.spring.boarddao.BoardDao;
import com.maple.spring.boarddto.BoardDto;

@Service
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	private BoardDao dao;

	@Override
	public void insertContent(BoardDto dto, ModelAndView mView) {
		if(dto.getTitle()==null) {
			mView.addObject("msg", "제목을 입력해주세요.");
			return;
		}
		if(dto.getContent()==null) {
			mView.addObject("msg", "내용을 입력해주세요");
			return;
		}
		dao.insert(dto);
	}

	@Override
	public void getBoardList(BoardDto dto, HttpServletRequest request) {
		//한 페이지에 몇개의 글이 노출될 것인가? (1~10/ 11~20 등 총 10개의 글)
		//하단 에는 몇개의 페이지 디스플레이가 노출될 것인가?(1~5, 6~10 등 총 5개의 페이지디스플레이)
		final int PAGE_ROW_NUM = 10;
		final int PAGE_DISPLAY_NUM = 5;
		int pageNum = 1;
		
		String requestNum = (String)request.getParameter("pageNum");
		if(requestNum!=null) {
			pageNum = Integer.valueOf(requestNum);
		}
		
		int startRowNum = 1 + (pageNum - 1) * PAGE_ROW_NUM;	
		int endRowNum = pageNum * PAGE_ROW_NUM;	
		
		int startDisplayNum = 1 + (pageNum - 1) * PAGE_DISPLAY_NUM;
		int endDisplayNum = pageNum * PAGE_DISPLAY_NUM;
		
		dto.setStartRowNum(startRowNum);
		dto.setEndRowNum(endRowNum);
		List<BoardDto> list = dao.getList(dto);
	
		  //하단 시작 페이지 번호 // 1
	      int startPageNum = 1 + ((pageNum-1)/PAGE_DISPLAY_NUM)*PAGE_DISPLAY_NUM;
	      //하단 끝 페이지 번호 // 5
	      int endPageNum=startPageNum+PAGE_DISPLAY_NUM-1;
	      
	      //총 글의 개수
	      int totalRow = dao.getCount();
	      //전체 페이지의 갯수 구하기
	      int totalPageCount=(int)Math.ceil(totalRow/(double)PAGE_ROW_NUM); // 7
	      //끝 페이지 번호가 이미 전체 페이지 갯수보다 크게 계산되었다면 잘못된 값이다.
	      if(endPageNum > totalPageCount){
	         endPageNum=totalPageCount; //보정해 준다. 
	      }
	      
	      //응답에 필요한 데이터를 view page 에 전달하기 위해  request scope 에 담는다
	      request.setAttribute("list", list);
	      request.setAttribute("pageNum", pageNum); // 6
	      request.setAttribute("startPageNum", startPageNum); // 1
	      request.setAttribute("endPageNum", endPageNum); // 2
	      request.setAttribute("totalPageCount", totalPageCount); //2
	      request.setAttribute("totalRow", totalRow);
	}
	
	
}