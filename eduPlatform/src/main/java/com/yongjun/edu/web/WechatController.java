package com.yongjun.edu.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yongjun.edu.service.WechatService;


@Controller
@RequestMapping(value = "/WechatLogin")
public class WechatController {

	@Autowired
	private WechatService service;

	/**
	 * 微信平台接入
	 */
	@RequestMapping(value = "loginByWechat.do", method = RequestMethod.GET)
	public void loginByWechat(HttpServletRequest request, HttpServletResponse response) {
		// 获取微信后台传入的四个参数
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String echostr = request.getParameter("echostr");
		boolean flag = service.checkSignature(signature, timestamp, nonce);
		System.out.println(flag);
		PrintWriter p = null;
		try {
			p = response.getWriter();
			if (flag) {
				p.print(echostr);// 注意此处必须返回echostr以完成验证
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
