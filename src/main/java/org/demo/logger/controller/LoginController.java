package org.demo.logger.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.demo.logger.constant.LoggerConstant;
import org.demo.logger.entity.User;
import org.demo.logger.result.Result;
import org.demo.logger.service.UserService;
import org.demo.logger.utils.CookieUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/account")
public class LoginController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Resource
	private UserService userService;
	
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	
	@ResponseBody
	@RequestMapping(value = "/login/do", method = RequestMethod.POST)
	public Result loginCheck(HttpServletResponse response, String username, String password) {
		Result result = null;
		try {
			if(StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password)) {
				User user = userService.login(username, password);
				if(user != null) {
					response.addCookie(CookieUtils.createLoggerCookie(user.getUserId(), user.getNickname()));
					result = new Result(LoggerConstant.SUCCESS, (Object) LoggerConstant.INDEX);
				} else {
					result = new Result(LoggerConstant.FAIL, "用户名密码错误");
				}
			} else {
				result = new Result(LoggerConstant.FAIL, "输入数据不能为空");
			}
		} catch(Exception e) {
			logger.error("登录错误", e);
			result = new Result(LoggerConstant.FAIL, "系统异常");
		}
		return result;
	}
	
	@RequestMapping("/logout")
	public String logout(HttpServletResponse response) {
		response.addCookie(CookieUtils.makeCookieExpire(CookieUtils.LOGGER_COOKIE));
		return "redirect:/account/login";
	}
	
}
