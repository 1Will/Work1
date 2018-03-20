package com.ahctx.reward.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.baomidou.kisso.annotation.Action;
import com.baomidou.kisso.annotation.Permission;

/**
 * <p>
 * 图表演示
 * </p>
 * 
 * @Author wangping
 * @Create 2016-8-20 20:38:18
 */
@Controller
@RequestMapping("/sys/echarts")
public class EchartsController extends BaseController {

	/**
	 * 地图
	 */
	@Permission(action = Action.Skip)
	@RequestMapping("/map")
	public String map(Model model) {
		
		return "/echarts/map";
	}

}
