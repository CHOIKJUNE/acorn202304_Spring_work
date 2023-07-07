package com.gura.spring05.gallery.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.gura.spring05.gallery.dto.GalleryDto;

public interface GalleryService {
	public Map<String, Object> saveImage(MultipartFile ajaxImage, HttpServletRequest request);
	public Map<String, Object> saveImage2(MultipartFile image, HttpServletRequest request);
	public void insertImgNCaption(HttpServletRequest request, GalleryDto dto);
	public void getList(HttpServletRequest request, GalleryDto dto);
	public void getDetail(HttpServletRequest request, GalleryDto dto);

}
