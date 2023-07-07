package com.gura.spring05.gallery.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.gura.spring05.gallery.dto.GalleryDto;

public interface GalleryDao {
	public void InsertImage(GalleryDto dto);
	public List<GalleryDto> getList(GalleryDto dto);
	public int getCount(GalleryDto dto);
	public GalleryDto getDetail(GalleryDto dto);
}
