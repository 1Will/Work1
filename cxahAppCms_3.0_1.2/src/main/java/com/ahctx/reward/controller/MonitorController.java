package com.ahctx.reward.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.baomidou.kisso.annotation.Action;
import com.baomidou.kisso.annotation.Permission;

/**
 * <p>
 * 监控
 * </p>
 * 
 * @Author wangping
 * @Create 2016-8-20 20:38:18
 */
@Controller
@RequestMapping("/monitor")
public class MonitorController extends BaseController {

	/**
	 * 实时监控
	 */
	@Permission(action = Action.Skip)
	@RequestMapping("/realTime")
	public String realTime(Model model) {
		
		return "/monitor/realTime";
	}

}
