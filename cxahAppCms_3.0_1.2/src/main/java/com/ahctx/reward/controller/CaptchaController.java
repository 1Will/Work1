package com.ahctx.reward.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ahctx.reward.common.MyCaptcha;
import com.baomidou.kisso.annotation.Action;
import com.baomidou.kisso.annotation.Login;
import com.baomidou.kisso.annotation.Permission;

/**
 * <p>
 * 验证码服务
 * </p>
 * 
 * @Author wangping
 * @Create 2016-8-20 20:38:18
 */
@Controller
@RequestMapping("/captcha")
public class CaptchaController extends BaseController {

	/**
	 * 生成图片
	 */
	@ResponseBody
	@Login(action = Action.Skip)
	@Permission(action = Action.Skip)
	@RequestMapping("/image")
	public void image(String ctoken) {
		try {
			MyCaptcha.getInstance().generate(request, response.getOutputStream(), ctoken);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
