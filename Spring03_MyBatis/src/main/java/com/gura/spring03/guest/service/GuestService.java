package com.gura.spring03.guest.service;

import org.springframework.web.servlet.ModelAndView;

import com.gura.spring03.guest.dto.GuestDto;

public interface GuestService {
	public void addGuest(GuestDto dto);
	public void updateGuest(GuestDto dto);
	public void deleteGuest(GuestDto dto);
	public void getGuestInfo(ModelAndView mView, int num);
	public void getGuestList(ModelAndView mView);
}
