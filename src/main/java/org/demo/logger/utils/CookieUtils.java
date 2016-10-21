package org.demo.logger.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class CookieUtils {

	public static String LOGGER_COOKIE = "LOGGER_COOKIE";
	public static String PATH = "/";
	public static int EXPIRE_TIME = 60 * 60 * 24 * 7;
	
	public static Cookie createCookie(String cookieName, String value, 
			String path, int expireTime) {
		Cookie cookie = new Cookie(cookieName, Base64Utils.getBase64(value));
		cookie.setPath(path);
		cookie.setMaxAge(expireTime);
		return cookie;
	}
	
	public static Cookie createLoggerCookie(long userId, String nickname) {
		String value = userId + ";" + nickname;
		Cookie cookie = new Cookie(LOGGER_COOKIE, Base64Utils.getBase64(value));
		cookie.setPath(PATH);
		cookie.setMaxAge(EXPIRE_TIME);
		return cookie;
	}
	
	public static String getCookieValue(String cookieName, HttpServletRequest request) {
		String cookieValue = null;
		Cookie cookie[] = request.getCookies();
		for (Cookie c : cookie) {
			if(cookieName.equals(c.getName())) {
				cookieValue = Base64Utils.getFromBase64(c.getValue());
			}
		}
		return cookieValue;
	}
	
	public static Cookie makeCookieExpire(String cookieName) {
		Cookie cookie = new Cookie(cookieName, "");
		cookie.setPath(PATH);
		cookie.setMaxAge(-1);
		return cookie;
		
	}
	
}
