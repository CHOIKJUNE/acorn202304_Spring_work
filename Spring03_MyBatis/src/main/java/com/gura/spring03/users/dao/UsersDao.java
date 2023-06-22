package com.gura.spring03.users.dao;

import java.util.List;
import com.gura.spring03.guest.dto.GuestDto;
import com.gura.spring03.users.dto.UsersDto;

public interface UsersDao {
	public void insert(UsersDto dto);
	public void update(UsersDto dto);
	public void delete(int num);
	public UsersDto getData(String id);
	public List<UsersDto> getList();
}
