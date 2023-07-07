package com.gura.spring05.gallery.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.gura.spring05.gallery.dto.GalleryDto;
import com.gura.spring05.gallery.service.GalleryService;

@Controller
public class GalleryController {
	
	@Autowired
	private GalleryService service;
	
	@RequestMapping("/gallery/list")
	public String getList(HttpServletRequest request, GalleryDto dto) {
		service.getList(request, dto);
		return "gallery/list";
	}
	
	@RequestMapping("/gallery/upload_form")
	public String uploadForm() {
		return "gallery/upload_form";
	}
	
	@RequestMapping("/gallery/upload_form2")
	public String uploadForm2() {
		return "gallery/upload_form2";
	}
	
	@RequestMapping("/gallery/upload_form3")
	public String uploadForm3() {
		return "gallery/upload_form3";
	}
	
	@RequestMapping("/gallery/test_form")
	public String testForm() {
		return "gallery/test_form";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/gallery/ajaxImage")
	@ResponseBody
	public Map<String, Object> ajaxImage(MultipartFile ajaxImage, HttpServletRequest request) {
		return service.saveImage(ajaxImage, request);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/gallery/ajax_upload")
	@ResponseBody
	public Map<String, Object> ajaxUpload(MultipartFile image, HttpServletRequest request) {
		return service.saveImage2(image, request);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/gallery/image_upload")
	public String imageUpload(HttpServletRequest request, GalleryDto dto) {
		service.insertImgNCaption(request, dto);
		return "gallery/image_upload";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/gallery/image_upload2")
	public String imageUpload2(HttpServletRequest request, GalleryDto dto) {
		service.insertImgNCaption(request, dto);
		return "gallery/image_upload2";
	}
	
	@RequestMapping("/gallery/detail")
	public String detail(HttpServletRequest request, GalleryDto dto) {
		service.getDetail(request, dto);
		return "gallery/detail";
	}
	
	
}
