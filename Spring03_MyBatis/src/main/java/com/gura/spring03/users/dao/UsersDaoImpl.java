package com.gura.spring03.users.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gura.spring03.users.dto.UsersDto;

@Repository
public class UsersDaoImpl implements UsersDao{

	@Autowired
	SqlSession session;
	
	@Override
	public void insert(UsersDto dto) {
		session.insert("users.insert", dto);
	}

	@Override
	public void update(UsersDto dto) {
		session.update("users.update", dto);
	}

	@Override
	public void delete(int num) {
		session.delete("users.delete", num);
	}

	@Override
	public UsersDto getData(String id) {
		return session.selectOne("users.select", id);
	}

	@Override
	public List<UsersDto> getList() {
		return session.selectList("users.getList");
	}
	
}
