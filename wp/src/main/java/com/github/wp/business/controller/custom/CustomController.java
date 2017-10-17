package com.github.wp.business.controller.custom;


import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.wp.business.pojo.custom.Bcustomerinfo;
import com.github.wp.business.service.custom.CustomService;
import com.github.wp.system.Constants;
import com.github.wp.system.pojo.SysDatadic;
import com.github.wp.system.pojo.SysUser;
import com.github.wp.system.service.DatadicService;
import com.github.wp.system.util.common.DateUtil;
import com.github.wp.system.util.common.Pager;
import com.github.wp.system.util.common.Pagination;
import com.github.wp.system.web.bind.annotation.CurrentUser;
import com.github.wp.system.web.spring.aspectj.SystemLog;
import com.github.wp.system.web.spring.aspectj.SystemLog.UserOperationLog;


/**
 * 客户信息控制器
 * @author dupeng
 * @version 1.0
 * @since 2016年8月09日
 */
@Controller
@RequestMapping("/custom")
public class CustomController {
	
	@Autowired
	private CustomService customService;
	@Autowired
	private DatadicService datadicService;

	/**
	 * 跳转到列表界面
	 * @param model
	 * @param response
	 * @return 跳转页面地址
	 * @author dupeng
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, HttpServletResponse response) {
		return "business/custom/info/list";
	}
	
	/**
	 * 查询客户信息列表
	 * @param Bcustomerinfo
	 * @param pagination
	 * @return 分页对象数据
	 * @author 杜鹏
	 */
	@ResponseBody
	@RequestMapping(value = "/customList")
	public Pager<Bcustomerinfo> titleList(Bcustomerinfo custom, Pagination pagination) {
		Pager<Bcustomerinfo> list = customService.findPage(custom, pagination);
		
		return list;
		
	}
	
	@RequestMapping(value = "/customAdd")
	public String customAdd(Model model, HttpServletResponse response){
		Bcustomerinfo custom = new Bcustomerinfo();
		List<SysDatadic> qyxzList = datadicService.findChildByCodingname("QYXZ");//根据编码得到--企业性质--列表
		List<SysDatadic> khztList = datadicService.findChildByCodingname("KHZT");//根据编码得到--客户状态--列表
		List<SysDatadic> khdjList = datadicService.findChildByCodingname("KHDJ");//根据编码得到--客户等级--列表
		List<SysDatadic> xxlyList = datadicService.findChildByCodingname("XXLY");//根据编码得到--信息来源--列表
		List<SysDatadic> hangyeList = datadicService.findChildByCodingname("HANGYE");//根据编码得到--行业信息--列表
		
		String currentDate = DateUtil.formatDateToStr(new Date(),"yyyy-MM-dd HH:mm:ss.SSS");
		String code = currentDate.substring(2, 4)+currentDate.substring(5, 7)+currentDate.substring(8, 10)+currentDate.substring(20);
		custom.setCode(code);
		model.addAttribute("custom", custom);
		model.addAttribute("qyxzList", qyxzList);
		model.addAttribute("khztList", khztList);
		model.addAttribute("khdjList", khdjList);
		model.addAttribute("xxlyList", xxlyList);
		model.addAttribute("hangyeList", xxlyList);
		
		return "business/custom/info/add";
	}
	
	/**
	 * @author 杜鹏
	 * @param model
	 * @param Bcustomerinfo
	 * @return map
	 * @since 08-10-16
	 */
	@ResponseBody
	@RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
	@SystemLog(description=UserOperationLog.CUSTOM_SAVEORUPDATE)
	public Map<String, Object> saveOrUpdate(Model model, Bcustomerinfo custom,@CurrentUser SysUser user) {
		if(custom.getId() != null && !custom.getId().equals("")){
			custom.setLastModifiedTime(new Timestamp(System.currentTimeMillis()));
			custom.setLastOperator(user.getUsername());
		}else{
			custom.setCreatedTime(new Timestamp(System.currentTimeMillis()));
			custom.setCreator(user.getUsername());
		}
		customService.saveOrUpdate(custom);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Constants.Others.RESPONSE_MSG.value(), Constants.UserNotice.CUSTOM_SAVEORUPDATE_SUCESS.getInfo());
		return map;
	}
	
	/**
	 * 根据id假删除客户信息
	 * @param id
	 * @return map
	 * @author 杜鹏
	 */
	@ResponseBody
	@RequestMapping(value = "/delete")
	@SystemLog(description=UserOperationLog.CUSTOM_DELETEONE)
	public Map<String, Object> deleteOne(@RequestParam("ids[]") Long[] ids) {
		customService.deleteOne(ids);
	    Map<String, Object> map = new HashMap<String, Object>();
	    map.put(Constants.Others.RESPONSE_MSG.value(), Constants.UserNotice.CUSTOM_DELETEONE_SUCESS.getInfo());
	    return map;
	}
	
	/**
	 * 进入客户信息修改界面
	 * @param model
	 * @return 界面url
	 * @author 杜鹏
	 */
	@RequestMapping(value = "/customEdit")
	@SystemLog(description=UserOperationLog.CUSTOM_ADD)
	public String customEdit(Model model,Long id) {
		Bcustomerinfo custom = customService.findOne(id);
		List<SysDatadic> qyxzList = datadicService.findChildByCodingname("QYXZ");//根据编码得到--企业性质--列表
		List<SysDatadic> khztList = datadicService.findChildByCodingname("KHZT");//根据编码得到--客户状态--列表
		List<SysDatadic> khdjList = datadicService.findChildByCodingname("KHDJ");//根据编码得到--客户等级--列表
		List<SysDatadic> xxlyList = datadicService.findChildByCodingname("XXLY");//根据编码得到--信息来源--列表
		model.addAttribute("custom", custom);
		
		model.addAttribute("qyxzList", qyxzList);
		model.addAttribute("khztList", khztList);
		model.addAttribute("khdjList", khdjList);
		model.addAttribute("xxlyList", xxlyList);
		return "business/custom/info/add";
	}

}
