package com.gura.spring05.users.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gura.spring05.users.dto.UsersDto;

@Repository
public class UsersDaoImpl implements UsersDao{
	//@Autowired어노테이션이 호출하는 메서드
	//public void setSession(SqlSession session) {
	//this.session = session;
	//}
	@Autowired
	private SqlSession session;
	
	/*
	 * 매개변수로 전달되는 아이디가 DB에 이미 존재하는지 여부를 리턴하는 메서드
	 */
	@Override
	public boolean isExist(String inputId) {
		UsersDto dto = session.selectOne("users.getData", inputId);
		boolean isExist = dto!=null ? true : false;
		return isExist;
		
	}

	@Override
	public void insert(UsersDto dto) {
		session.insert("users.insert", dto);
	}

	@Override
	public UsersDto getData(String id) {
		return session.selectOne("users.getData", id);
	}

	@Override
	public void updatePwd(UsersDto dto) {
		session.update("users.updatePwd", dto);
	}

	@Override
	public void update(UsersDto dto) {
		session.update("users.update", dto);
	}

	@Override
	public void delete(String id) {
		session.delete("users.delete", id);
	}

	
}
