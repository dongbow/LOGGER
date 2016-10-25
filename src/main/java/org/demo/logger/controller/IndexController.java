package org.demo.logger.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.demo.logger.constant.LoggerConstant;
import org.demo.logger.entity.User;
import org.demo.logger.result.Result;
import org.demo.logger.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/system")
public class IndexController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Resource
	private UserService userService;
	
	@ResponseBody
	@RequestMapping(value = "/get/name", method = RequestMethod.POST)
	public Result getName(HttpServletRequest request) {
		return new Result(LoggerConstant.SUCCESS, (Object) userService.getNicknameFromCookie(request));
	}
	
	@RequestMapping("/user/list")
	public String index(Model model) {
		List<User> users = userService.getUserList();
		model.addAttribute("users", users);
		return "list";
	}
	
	@RequestMapping("/user/addpanel")
	public String addPanel() {
		return "user-info";
	}
	
	@ResponseBody
	@RequestMapping(value = "/user/exist", method = RequestMethod.POST)
	public Result exist(String name) {
		Result result = null;
		try {
			if(StringUtils.isNotBlank(name)) {
				if(userService.exist(name) == LoggerConstant.SUCCESS) {
					result = new Result(LoggerConstant.SUCCESS, (Object) "用户名可用");
				} else {
					result = new Result(LoggerConstant.FAIL, "用户名已存在");
				}
			} else {
				result = new Result(LoggerConstant.FAIL, "用户名不能为空");
			}
		} catch(Exception e) {
			logger.error("系统异常" + name, e);
			result = new Result(LoggerConstant.FAIL, "系统异常");
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "/user/add/do", method = RequestMethod.POST)
	public Result add(String name, String password, String nickname) {
		Result result = null;
		try {
			if(this.isNotBlank(name, password, nickname)) {
				result = this.exist(name);
				if(result.getRc() == LoggerConstant.SUCCESS) {
					userService.add(name, password, nickname);
					result = new Result(LoggerConstant.SUCCESS, (Object) "添加成功");
				}
			} else {
				result = new Result(LoggerConstant.FAIL, "数据不能为空");
			}
		} catch(Exception e) {
			logger.error("添加用户" + name + "失败", e);
			result = new Result(LoggerConstant.FAIL, "添加失败");
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "/user/delete", method = RequestMethod.POST)
	public Result deleteUser(@RequestParam(value = "ids[]")long[] ids) {
		Result result = null;
		try {
			userService.delete(ids);
			result = new Result(LoggerConstant.SUCCESS, (Object) "删除成功");
		} catch(Exception e) {
			logger.error("删除" + ids + "失败", e);
			result = new Result(LoggerConstant.FAIL, "删除失败");
		}
		return result;
	}
	
	private boolean isNotBlank(String... value) {
		for (String str : value) {
			if(StringUtils.isEmpty(str)) {
				return false;
			}
		}
		return true;
	}
	
}
