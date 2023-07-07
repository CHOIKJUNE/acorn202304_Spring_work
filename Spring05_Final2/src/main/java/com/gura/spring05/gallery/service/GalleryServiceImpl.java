package com.gura.spring05.gallery.service;

import java.io.File;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.gura.spring05.cafe.dto.CafeDto;
import com.gura.spring05.gallery.dao.GalleryDao;
import com.gura.spring05.gallery.dto.GalleryDto;

@Service
public class GalleryServiceImpl implements GalleryService {
	
	@Autowired
	private GalleryDao dao;

	@Override
	public Map<String, Object> saveImage(MultipartFile ajaxImage, HttpServletRequest request) {
	      //업로드된 이미지에 대한 정보를 MultipartFile 객체를 이용해서 얻어낼수 있다.   
	      
	      //원본 파일명
	      String orgFileName=ajaxImage.getOriginalFilename();
	      //upload 폴더에 저장할 파일명을 직접구성한다.
	      //저장할 파일 이름을 현재 시간을 기반으로 구성합니다. 이를 통해 파일명의 중복을 방지할 수 있습니다.
	      // 1234123424343xxx.jpg
	      String saveFileName=System.currentTimeMillis()+orgFileName;
	      
	      // webapp/upload 폴더까지의 실제 경로 얻어내기 
	      String realPath=request.getServletContext().getRealPath("/resources/upload");
	      // upload 폴더가 존재하지 않을경우 만들기 위한 File 객체 생성
	      File upload=new File(realPath);
	      if(!upload.exists()) {//만일 존재 하지 않으면
	         upload.mkdir(); //만들어준다.
	      }
	      try {
	         //파일을 저장할 전체 경로를 구성한다. 
	    	 //File.separator는 구분자 \이다
	         String savePath=realPath+File.separator+saveFileName;
	         //임시폴더에 업로드된 파일을 원하는 파일을 저장할 경로에 전송한다.
	         ajaxImage.transferTo(new File(savePath));
	      }catch(Exception e) {
	         e.printStackTrace();
	      }
	      
	      // json 문자열을 출력하기 위한 Map 객체 생성하고 정보 담기 
	      Map<String, Object> map=new HashMap<String, Object>();
	      map.put("imagePath", "/resources/upload/"+saveFileName);
	      return map;
	}
	
	

	@Override
	public Map<String, Object> saveImage2(MultipartFile image, HttpServletRequest request) {
		 //원본 파일명
	      String orgFileName=image.getOriginalFilename();
	      //upload 폴더에 저장할 파일명을 직접구성한다.
	      //저장할 파일 이름을 현재 시간을 기반으로 구성합니다. 이를 통해 파일명의 중복을 방지할 수 있습니다.
	      // 1234123424343xxx.jpg
	      String saveFileName=System.currentTimeMillis()+orgFileName;
	      
	      // webapp/upload 폴더까지의 실제 경로 얻어내기 
	      String realPath=request.getServletContext().getRealPath("/resources/upload");
	      // upload 폴더가 존재하지 않을경우 만들기 위한 File 객체 생성
	      File upload=new File(realPath);
	      if(!upload.exists()) {//만일 존재 하지 않으면
	         upload.mkdir(); //만들어준다.
	      }
	      try {
	         //파일을 저장할 전체 경로를 구성한다. 
	    	 //File.separator는 구분자 \이다
	         String savePath=realPath+File.separator+saveFileName;
	         //임시폴더에 업로드된 파일을 원하는 파일을 저장할 경로에 전송한다.
	         image.transferTo(new File(savePath));
	      }catch(Exception e) {
	         e.printStackTrace();
	      }
	      
	      // json 문자열을 출력하기 위한 Map 객체 생성하고 정보 담기 
	      Map<String, Object> map=new HashMap<String, Object>();
	      GalleryDto dto = new GalleryDto();
	      HttpSession session = request.getSession();
	      String writer = (String)session.getAttribute("id");
	      dto.setImagePath(request.getContextPath() + "/resources/upload/"+saveFileName);
	      dto.setCaption((String)request.getParameter("caption"));
	      dto.setWriter(writer);
	      try {
	    	  dao.InsertImage(dto);
	      }catch(Exception e) {
	    	  e.printStackTrace();
	      }
	      map.put("isSuccess", true);
	      return map;
	}



	@Override
	public void insertImgNCaption(HttpServletRequest request, GalleryDto dto) {
		HttpSession session = request.getSession();
		String writer = (String)session.getAttribute("id");
		dto.setWriter(writer);
		dto.setImagePath(request.getParameter("image"));
		dto.setCaption(request.getParameter("caption"));
		dao.InsertImage(dto);
	}

	@Override
	public void getList(HttpServletRequest request, GalleryDto dto) {
		  //한 페이지에 몇개씩 표시할 것인지
	      final int PAGE_ROW_COUNT=8;
	      //하단 페이지를 몇개씩 표시할 것인지
	      final int PAGE_DISPLAY_COUNT=5;
	      //보여줄 페이지의 번호를 일단 1이라고 초기값 지정
	      int pageNum=1;
	      //페이지 번호가 파라미터로 전달되는지 읽어와 본다.
	      String strPageNum=request.getParameter("pageNum");
	      //만일 페이지 번호가 파라미터로 넘어 온다면
	      if(strPageNum != null){
	         //숫자로 바꿔서 보여줄 페이지 번호로 지정한다.
	         pageNum=Integer.parseInt(strPageNum);
	      }   
	      
	      //이 부분은 하단 페이지바가 아닌 화면에 출력되는 글이다.
	      //보여줄 페이지의 시작 ROWNUM // 1
	      int startRowNum=1+(pageNum-1)*PAGE_ROW_COUNT;
	      //보여줄 페이지의 끝 ROWNUM // 5
	      int endRowNum=pageNum*PAGE_ROW_COUNT;
	      //FileDto 객체에 startRowNum 과 endRowNum 을 담는다.
	      dto.setStartRowNum(startRowNum); // 1
	      dto.setEndRowNum(endRowNum); // 5
	      //파일 목록을 select 해 온다.(검색 키워드가 있는경우 키워드에 부합하는 전체 글) 
	      List<GalleryDto> list = dao.getList(dto);
	      
	      //전체 글의 갯수(검색 키워드가 있는경우 키워드에 부합하는 전체 글의 갯수)
	      int totalRow=dao.getCount(dto);
	      
	      //하단 시작 페이지 번호 // 1
	      int startPageNum = 1 + ((pageNum-1)/PAGE_DISPLAY_COUNT)*PAGE_DISPLAY_COUNT;
	      //하단 끝 페이지 번호 // 5
	      int endPageNum=startPageNum+PAGE_DISPLAY_COUNT-1;
	      
	      //전체 페이지의 갯수 구하기
	      int totalPageCount=(int)Math.ceil(totalRow/(double)PAGE_ROW_COUNT); // 7
	      //끝 페이지 번호가 이미 전체 페이지 갯수보다 크게 계산되었다면 잘못된 값이다.
	      if(endPageNum > totalPageCount){
	         endPageNum=totalPageCount; //보정해 준다. 
	      }
	      
	      //응답에 필요한 데이터를 view page 에 전달하기 위해  request scope 에 담는다
	      request.setAttribute("list", list);
	      request.setAttribute("pageNum", pageNum); // 6
	      request.setAttribute("startPageNum", startPageNum); // 1
	      request.setAttribute("endPageNum", endPageNum); // 5
	      request.setAttribute("totalPageCount", totalPageCount); // 7
	      request.setAttribute("totalRow", totalRow);
	}

	@Override
	public void getDetail(HttpServletRequest request, GalleryDto dto) {
		int num = Integer.valueOf(request.getParameter("num"));
		dto.setNum(num);
		GalleryDto dto1 = dao.getDetail(dto);
		request.setAttribute("dto", dto1);
	}
	
	
	
	
	
	
}
