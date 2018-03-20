package com.ahctx.reward.controller;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ahctx.reward.common.MyCaptcha;
import com.ahctx.reward.common.util.encrypt.Des3;
import com.ahctx.reward.common.util.http.DevDecide;
import com.ahctx.reward.common.util.http.PreventLink;
import com.ahctx.reward.entity.RegisterUser;
import com.ahctx.reward.service.IRegisterUserService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.framework.controller.SuperController;
import com.baomidou.kisso.annotation.Action;
import com.baomidou.kisso.annotation.Login;
import com.baomidou.kisso.annotation.Permission;
import com.baomidou.kisso.common.util.RandomUtil;
import com.baomidou.kisso.web.waf.request.WafRequestWrapper;

@Controller
public class RegisterUserController extends SuperController {

	@Autowired
	private IRegisterUserService iRegisterUserService;
	
	@Login(action = Action.Skip)
	@Permission(action = Action.Skip)
	@RequestMapping({ "/transfer" })
	public String toTransfer(Model model, RedirectAttributes attr){
		String secrity = null;
		try {
			secrity = Des3.encode(new java.util.Date().getTime() + "");
		} catch (Exception e) {
		}
		attr.addAttribute("secrity", secrity); 
        return "redirect:/";
	}
	
	@Login(action = Action.Skip)
	@Permission(action = Action.Skip)
	@RequestMapping({ "/peopleProtect" })
	public String peopleProtect(Model model, RedirectAttributes attr){
		String secrity = null;
		try {
			secrity = Des3.encode(new java.util.Date().getTime() + "");
		} catch (Exception e) {
		}
		attr.addAttribute("secrity", secrity); 
        return "redirect:/";
	}
	
	@Login(action = Action.Skip)
	@Permission(action = Action.Skip)
	@RequestMapping({ "/2016/transfer" })
	public String toTransferback(Model model, RedirectAttributes attr){
		String secrity = null;
		try {
			secrity = Des3.encode(new java.util.Date().getTime() + "");
		} catch (Exception e) {
		}
		attr.addAttribute("secrity", secrity); 
        return "redirect:/";
	}
	
	@Login(action = Action.Skip)
	@Permission(action = Action.Skip)
	@RequestMapping({ "/2016/peopleProtect" })
	public String peopleProtectback(Model model, RedirectAttributes attr){
		String secrity = null;
		try {
			secrity = Des3.encode(new java.util.Date().getTime() + "");
		} catch (Exception e) {
		}
		attr.addAttribute("secrity", secrity); 
        return "redirect:/";
	}


	@Login(action = Action.Skip)
	@Permission(action = Action.Skip)
	@RequestMapping({ "/" })
	public String list(Model model, HttpServletRequest request) {
		Boolean isOutLink = PreventLink.isOutLink(request);
		model.addAttribute(MyCaptcha.CAPTCHA_TOKEN, RandomUtil.get32UUID());
		Boolean b = Boolean.valueOf(DevDecide.isMobileDevice(request));
		if (isOutLink && b.equals(Boolean.valueOf(true))) {
			java.util.Date date = new java.util.Date();// 创建一个时间对象，获取到当前的时间
			WafRequestWrapper wr = new WafRequestWrapper(request);
			String secrityDes3 = wr.getParameter("secrity");
			try {
				String secrity = Des3.decode(secrityDes3);
				Long secrityTime = Long.parseLong(secrity);
				Long nowTime = date.getTime();
				if (nowTime - secrityTime > 5 * 1000) {
					return "/luckDraw/index-blank";
				}
			} catch (Exception e) {
				return "/luckDraw/index-blank";
			}
		}
		if (b.equals(Boolean.valueOf(true)) && !preventRegister()) {
			return "/luckDraw/index-m";
		} else if (b.equals(Boolean.valueOf(true)) && preventRegister()) {
			return "/luckDraw/notice-m";
		} else if (b.equals(Boolean.valueOf(false)) && !preventRegister()) {
			return "/luckDraw/index-pc";
		} else {
			return "/luckDraw/notice-pc";
		}
	}

	public boolean preventRegister() {
		java.util.Date nowdate = new java.util.Date();
		String startTimeString = "2016-07-27";
		String endTimeString = "2016-10-32";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
		try {
			java.util.Date startTimeDate = sdf.parse(startTimeString);
			java.util.Date endTimeDate = sdf.parse(endTimeString);
			boolean afterStartTIme = startTimeDate.before(nowdate);
			boolean beforeEndTime = endTimeDate.after(nowdate);
			if (afterStartTIme && beforeEndTime) {
				return false;
			}
		} catch (ParseException e) {
			e.printStackTrace();
			return false;
		}
		return true;

	}

	@SuppressWarnings("static-access")
	@ResponseBody
	@Login(action = Action.Skip)
	@Permission(action = Action.Skip)
	@RequestMapping({ "/luckDraw/registerApply" })
	public String addRegisterUser(Model model, HttpServletResponse response) throws IOException {

		JSONObject jo = new JSONObject();
		Map<String, Object> map = new HashMap<String, Object>();
		if (preventRegister()) {
			map.put("msg", "报名活动还未开始或已经结束");
			return jo.toJSONString(map, true);
		}

		/**
		 * 过滤 XSS SQL 注入
		 */
		WafRequestWrapper wr = new WafRequestWrapper(request);
		String ctoken = wr.getParameter("ctoken");
		String captcha = wr.getParameter("captcha");
		RegisterUser registerUser = new RegisterUser();
		String driverNumber = wr.getParameter("driverNumber") == null ? null
				: wr.getParameter("driverNumber").toUpperCase();
		String plateNumber = wr.getParameter("plateNumber") == null ? null
				: wr.getParameter("plateNumber").toUpperCase();
		String frameNumber = wr.getParameter("frameNumber") == null ? null
				: wr.getParameter("frameNumber").toUpperCase();
		registerUser.setNameOfDriver(wr.getParameter("nameOfDriver"));
		registerUser.setDriverNumber(driverNumber);
		registerUser.setPhoneNumber(wr.getParameter("phoneNumber"));
		registerUser.setPlateNumber(plateNumber);
		registerUser.setVehicleType(wr.getParameter("vehicleType"));
		String vehicleTime = wr.getParameter("vehicleTime");
		if (vehicleTime != null && !vehicleTime.equals("")) {
			try {
				Date date = Date.valueOf(vehicleTime);
			    registerUser.setVehicleTime(date);
			} catch (Exception e) {
				System.out.println(vehicleTime);
				registerUser.setVehicleTimeString(vehicleTime);
			}
		}
		registerUser.setFrameNumber(frameNumber);
		registerUser.setDomain(wr.getParameter("domain"));
		if (StringUtils.isNotBlank(ctoken) && StringUtils.isNotBlank(captcha)
				&& MyCaptcha.getInstance().verification(wr, ctoken, captcha)
				|| Boolean.valueOf(DevDecide.isMobileDevice(request))) {
			if (iRegisterUserService.validUnique(registerUser.getDriverNumber(), registerUser.getPhoneNumber()) > 0) {
				map.put("msg", "报名失败，驾驶证号或者手机号重复");
				return jo.toJSONString(map, true);
			}
			Boolean b = Boolean.valueOf(this.iRegisterUserService.AddRegisterUser(registerUser));
			if (b.equals(Boolean.valueOf(true)))
				map.put("msg", "报名成功，返回首页");
			else {
				map.put("msg", "系统异常");
			}
		} else {
			map.put("msg", "验证码超过二十分钟无效");
		}
		return jo.toJSONString(map, true);
	}

	@Login(action = Action.Skip)
	@Permission(action = Action.Skip)
	@RequestMapping({ "/luckDraw/confirmDlg" })
	public String confirmDlg(Model model, HttpServletRequest request, RegisterUser registerUser) {

		model.addAttribute("registerUser", registerUser);
		return "/luckDraw/confirmDlg-pc";
	}

	@SuppressWarnings("static-access")
	@ResponseBody
	@Login(action = Action.Skip)
	@Permission(action = Action.Skip)
	@RequestMapping({ "/luckDraw/validData" })
	public String validData(Model model) {
		WafRequestWrapper wr = new WafRequestWrapper(request);
		String param = wr.getParameter("param");
		String name = wr.getParameter("name");
		RegisterUser registerUser = new RegisterUser();
		Map<String, Object> map = new HashMap<String, Object>();
		String msg = null;
		if (name != null && name.equals("driverNumber")) {
			registerUser.setDriverNumber(param);
			msg = "驾驶证号重复，请重新输入";
		}
		if (name != null && name.equals("phoneNumber")) {
			registerUser.setPhoneNumber(param);
			msg = "手机号重复，请重新输入";
		}
		int count = iRegisterUserService.selectCount(registerUser);
		JSONObject jo = new JSONObject();
		if (count == 0) {
			map.put("status", "y");
		} else {
			map.put("status", "n");
			map.put("info", msg);
		}
		return jo.toJSONString(map, true);
	}

	@SuppressWarnings("static-access")
	@ResponseBody
	@Login(action = Action.Skip)
	@Permission(action = Action.Skip)
	@RequestMapping({ "/luckDraw/validCaptcha" })
	public String validCaptcha(Model model) {
		WafRequestWrapper wr = new WafRequestWrapper(request);
		String ctoken = wr.getParameter("ctoken");
		String captcha = wr.getParameter("param");
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject jo = new JSONObject();
		if (StringUtils.isNotBlank(ctoken) && StringUtils.isNotBlank(captcha)
				&& MyCaptcha.getInstance().verification(wr, ctoken, captcha)) {
			map.put("status", "y");
		} else {
			map.put("status", "n");
			map.put("info", "请输入正确的验证码");
		}
		return jo.toJSONString(map, true);
	}

	@Login(action = Action.Skip)
	@Permission(action = Action.Skip)
	@RequestMapping({ "/luckDraw/needInfo" })
	public String needInfo(Model model) {
		Boolean b = Boolean.valueOf(DevDecide.isMobileDevice(request));
		if (b.equals(Boolean.valueOf(true))) {
			return "/luckDraw/needInfo-m";
		} else {
			return "/luckDraw/needInfo-pc";
		}
	}

	@SuppressWarnings("static-access")
	@ResponseBody
	@Login(action = Action.Skip)
	@Permission(action = Action.Skip)
	@RequestMapping({ "/luckDraw/getToken" })
	public String getToken(Model model) {
		JSONObject jo = new JSONObject();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MyCaptcha.CAPTCHA_TOKEN, RandomUtil.get32UUID());
		return jo.toJSONString(map, true);
	}
	
}