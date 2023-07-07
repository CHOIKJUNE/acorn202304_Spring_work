package com.gura.spring05.gallery.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.gura.spring05.gallery.dto.GalleryDto;

@Repository
public class GalleryDaoImpl implements GalleryDao{

	@Autowired
	private SqlSession session;

	@Override
	public void InsertImage(GalleryDto dto) {
		session.insert("gallery.insert", dto);
	}

	@Override
	public List<GalleryDto> getList(GalleryDto dto) {
		return session.selectList("gallery.getList", dto);
	}

	@Override
	public int getCount(GalleryDto dto) {
		return session.selectOne("gallery.getCount");
	}

	@Override
	public GalleryDto getDetail(GalleryDto dto) {
		return session.selectOne("gallery.getDetail", dto);
	}
}
