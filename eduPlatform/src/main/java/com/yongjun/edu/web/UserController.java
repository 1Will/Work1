package com.yongjun.edu.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.yongjun.edu.entity.User;
import com.yongjun.edu.service.UserService;

@Controller
@RequestMapping("/user") // url:/模块/资源/{id}/细分 /seckill/list
public class UserController {

	public String prefix ="yongjun/users/";
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	private String list(Model model) {
		List<User> list = userService.getList(5,0);
		// list.jsp + model = ModelAndView
		String jsonString = JSON.toJSONString(list);
		model.addAttribute("list", list);
		model.addAttribute("jsonList", jsonString);
		
		return prefix+"list";// WEB-INF/jsp/"list".jsp
	}

	@RequestMapping(value = "/{userId}/detail", method = RequestMethod.GET)
	private String detail(@PathVariable("userId") Long userId, Model model) {
		if (userId == null) {
			return "redirect:/user/list";
		}
		User user = userService.getById(userId);
		if (user == null) {
			return "forward:/user/list";
		}
		model.addAttribute("user", user);
		return prefix+"detail";
	}
	
	
//	@ResponseBody
	@RequestMapping(value = "/{userId}/update", method = RequestMethod.POST)
	private String update(@ModelAttribute User user ,HttpServletRequest request,@PathVariable("userId") Long userId, Model model) {
		if (userId == null) {
			return "redirect:/user/list";
		}
		User u = userService.getById(userId);
		if (u == null) {
			return "forward:/user/list";
		}
//		u.setName(request.getParameter("user.name"));
//		u.setLoginName(request.getParameter("user.loginName"));
		u.setLoginName(user.getLoginName());
		u.setName(user.getName());
		userService.update(u);
		model.addAttribute("user", u);
		return prefix+"detail";
	}

}
