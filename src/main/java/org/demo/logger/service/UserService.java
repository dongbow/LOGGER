package org.demo.logger.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.demo.logger.entity.User;

public interface UserService {

	public User login(String username, String password);
	
	public List<User> getUserList();
	
	public long getUserIdFromCookie(HttpServletRequest request);
	
	public String getNicknameFromCookie(HttpServletRequest request);
	
	public int delete(String ids);

}
