package com.ahctx.reward.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.baomidou.kisso.annotation.Action;
import com.baomidou.kisso.annotation.Login;
import com.baomidou.kisso.annotation.Permission;

/**
 * <p>
 * 首页
 * </p>
 * 
 * @Author wangping
 * @Create 2016-8-20 20:38:18
 */
@Controller
public class IndexController extends BaseController {

	/**
	 * 首页
	 */
	@Permission(action = Action.Skip)
	@RequestMapping("/index")
	public String index(Model model) {
		return "/index";
	}

	/**
	 * 主页
	 */
	@Login(action = Action.Skip)
	@Permission(action = Action.Skip)
	@RequestMapping("/home")
	public String home() {
		return "/home";
	}

}
