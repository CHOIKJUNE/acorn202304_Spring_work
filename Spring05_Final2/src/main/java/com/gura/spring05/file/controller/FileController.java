package com.gura.spring05.file.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.gura.spring05.file.dto.FileDto;
import com.gura.spring05.file.service.FileService;

@Controller
public class FileController {

	@Autowired
	private FileService service;
	
	   @RequestMapping("/file/list")
	   public String list(HttpServletRequest request) {
	      service.getList(request);
	      return "file/list";
	   }
	   
	   @RequestMapping("/file/upload_form")
	   public String uploadForm() {
	      return "file/upload_form";
	   }
	   
	   @RequestMapping("/file/upload")
	   public ModelAndView upload(FileDto dto, ModelAndView mView, HttpServletRequest request) {
		   //FileDto에는 폼전송되는 title과 myFile이 들어있다.
	      service.saveFile(dto, mView, request);
	      mView.setViewName("file/upload");
	      return mView;
	   }
	   
	   @RequestMapping("/file/download")
	   public ModelAndView download(int num, ModelAndView mView) {
		   // num은 다운로드 해줄 파일의 번호(pk)
	      service.getFileData(num, mView);
	      // 응답을 할 bean 의 이름을 설정 
	      /*
	       * 원래대로라면 WEB-INF/views/fileDOwnView.jsp로 forward이동해야 하지만 
	       * servlet-context.xml에 beans
	       */
	      mView.setViewName("fileDownView");
	      return mView;
	   }
	   
	   @RequestMapping("/file/delete")
	   public ModelAndView delete(int num, ModelAndView mView, HttpServletRequest request) {
	      service.deleteFile(num, request);
	      mView.setViewName("redirect:/file/list");
	      return mView;
	   }
}
