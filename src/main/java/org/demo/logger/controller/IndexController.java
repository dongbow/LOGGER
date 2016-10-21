package org.demo.logger.controller;

import java.util.List;

import javax.annotation.Resource;

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
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/sytem")
public class IndexController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Resource
	private UserService userService;
	
	@RequestMapping("/user/list")
	public String index(Model model) {
		List<User> users = userService.getUserList();
		model.addAttribute("users", users);
		return "list";
	}
	
	@ResponseBody
	@RequestMapping(value = "/user/delete", method = RequestMethod.POST)
	public Result deleteUser(String ids) {
		Result result = null;
		try {
			userService.delete(ids);
			result = new Result(LoggerConstant.SUCCESS, "删除成功");
		} catch(Exception e) {
			logger.error("删除" + ids + "失败", e.getMessage());
			result = new Result(LoggerConstant.FAIL, "删除失败");
		}
		return result;
	}
	
}
