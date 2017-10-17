package com.github.wp.business.controller.custom;


import java.sql.Timestamp;
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

import com.github.wp.business.pojo.custom.Bcontactarchives;
import com.github.wp.business.pojo.custom.Bcustomerinfo;
import com.github.wp.business.service.custom.ContactService;
import com.github.wp.system.Constants;
import com.github.wp.system.pojo.SysDatadic;
import com.github.wp.system.pojo.SysUser;
import com.github.wp.system.service.DatadicService;
import com.github.wp.system.util.common.Pager;
import com.github.wp.system.util.common.Pagination;
import com.github.wp.system.web.bind.annotation.CurrentUser;
import com.github.wp.system.web.spring.aspectj.SystemLog;
import com.github.wp.system.web.spring.aspectj.SystemLog.UserOperationLog;


/**
 * 联系人管理控制器
 * @author dupeng
 * @version 1.0
 * @since 2016年8月3日
 */
@Controller
@RequestMapping("/contact")
public class ContactController {
	
	@Autowired
	private ContactService contactService;
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
		return "business/custom/contact/list";
	}
	
	/**
	 * 查询联系人列表
	 * @param Bcontactarchives
	 * @param pagination
	 * @return 分页对象数据
	 * @author 杜鹏
	 */
	@ResponseBody
	@RequestMapping(value = "/contactList")
	public Pager<Bcontactarchives> titleList(Bcontactarchives contact,String customId, Pagination pagination) {
		Bcustomerinfo custom = new Bcustomerinfo();
		custom.setId(Long.parseLong(customId));
		contact.setCustomInfo(custom);
		Pager<Bcontactarchives> list = contactService.findPage(contact, pagination);
		
		return list;
		
	}
	
	/**
	 * 进入联系人新增界面
	 * @param model
	 * @return 界面url
	 * @author 杜鹏
	 */
	@RequestMapping(value = "/contactAdd")
	@SystemLog(description=UserOperationLog.CONTACT_ADD)
	public String departmentAdd(Model model) {
		Bcontactarchives contact = new Bcontactarchives();
		model.addAttribute("contact", contact);
		List<SysDatadic> list = datadicService.findChildByCodingname("sxcd_01");
		model.addAttribute("list", list);
		return "business/custom/contact/add";
	}
	
	/**
	 * 进入联系人修改界面
	 * @param model
	 * @return 界面url
	 * @author 杜鹏
	 */
	@RequestMapping(value = "/contactEdit")
	@SystemLog(description=UserOperationLog.CONTACT_ADD)
	public String departmentAdd(Model model,Long id) {
		Bcontactarchives contact = contactService.findOne(id);
		model.addAttribute("contact", contact);
		List<SysDatadic> list = datadicService.findChildByCodingname("sxcd_01");
		model.addAttribute("list", list);
		return "business/custom/contact/add";
	}
	
	/**
	 * 根据id假删除联系人信息
	 * @param id
	 * @return map
	 * @author 杜鹏
	 */
	@ResponseBody
	@RequestMapping(value = "/delete")
	@SystemLog(description=UserOperationLog.CONTACT_DELETEONE)
	public Map<String, Object> deleteOne(@RequestParam("ids[]") Long[] ids) {
		contactService.deleteOne(ids);
	    Map<String, Object> map = new HashMap<String, Object>();
	    map.put(Constants.Others.RESPONSE_MSG.value(), Constants.UserNotice.CONTACT_DELETEONE_SUCESS.getInfo());
	    return map;
	}
	
	/**
	 * @author 杜鹏
	 * @param model
	 * @param Bcontactarchives
	 * @return map
	 * @since 08-08-16
	 */
	@ResponseBody
	@RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
	@SystemLog(description=UserOperationLog.CONTACT_SAVEORUPDATE)
	public Map<String, Object> saveOrUpdate(Model model, Bcontactarchives contact,@CurrentUser SysUser user) {
		if(contact.getId() != null && !contact.getId().equals("")){
			contact.setLastModifiedTime(new Timestamp(System.currentTimeMillis()));
			contact.setLastOperator(user.getUsername());
		}else{
			contact.setCreatedTime(new Timestamp(System.currentTimeMillis()));
			contact.setCreator(user.getUsername());
		}
		contactService.saveOrUpdate(contact);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Constants.Others.RESPONSE_MSG.value(), Constants.UserNotice.CONTACT_SAVEORUPDATE_SUCESS.getInfo());
		return map;
	}

}
