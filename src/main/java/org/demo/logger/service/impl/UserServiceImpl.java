package org.demo.logger.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.demo.logger.annotation.SysLog;
import org.demo.logger.dao.UserDao;
import org.demo.logger.entity.User;
import org.demo.logger.service.UserService;
import org.demo.logger.utils.Base64Utils;
import org.demo.logger.utils.CookieUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

	@Resource
	private UserDao userDao;
	
	public User login(String username, String password) {
		return userDao.login(username, password);
	}

	public List<User> getUserList() {
		return userDao.getUserList();
	}
	
	public long getUserIdFromCookie(HttpServletRequest request) {
		return Long.parseLong(this.getLoggerCookie(request, "id"));
	}

	public String getNicknameFromCookie(HttpServletRequest request) {
		return this.getLoggerCookie(request, "nickname");
	}
	
	@Transactional
	@SysLog(module="用户管理", methods="删除用户")
	public int delete(String ids) {
		List<Integer> idList = new ArrayList<Integer>();
		for (String id : ids.split(",")) {
			idList.add(Integer.parseInt(id));
		}
		return userDao.delete(idList);
	}
	
	private String getLoggerCookie(HttpServletRequest request, String field) {
		String cookie = CookieUtils.getCookieValue(CookieUtils.LOGGER_COOKIE, request);
		cookie = Base64Utils.getFromBase64(cookie);
		if("id".equals(field)) {
			return cookie.split(";")[0];
		}
		return cookie.split(";")[1];
	}

}
