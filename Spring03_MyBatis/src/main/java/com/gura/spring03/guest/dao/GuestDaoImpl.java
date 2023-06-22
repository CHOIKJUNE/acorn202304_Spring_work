package com.gura.spring03.guest.dao;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.gura.spring03.guest.dto.GuestDto;
import com.gura.spring03.member.dto.MemberDto;

//bean이 된 객체는 sbc가 필드로 객체를 공급해줄 수 있다.
@Repository //@Repository가 없으면 servlet-context-xml에 bean으로 직접 등록해야한다.
public class GuestDaoImpl implements GuestDao{
	
	//mybatis기반으로 DB연동을 하기 위한 핵심 의존객체를 Dependency Inection받는다.
	@Autowired
	private SqlSession session;
	
	//@Autowired어노테이션이 호출하는 메서드
//	public void setSession(SqlSession session) {
//		this.session = session;
//	}
	
	@Override
	public void insert(GuestDto dto) {
		session.insert("guest.insert", dto);
	}

	@Override
	public void update(GuestDto dto) {
		session.update("guest.update", dto);
	}
	
	@Override
	public void delete(GuestDto dto) {
		session.delete("guest.delete", dto);
	}

	@Override
	public GuestDto getData(int num) {
		return session.selectOne("guest.getData", num);
	}

	@Override
	public List<GuestDto> getList() {
		List<GuestDto> list = session.selectList("guest.getList");
		return list;
	}
	
}
