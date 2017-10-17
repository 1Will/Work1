package com.github.wp.business.controller.contract;



import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.wp.business.pojo.qiandan.HBillingrecord;
import com.github.wp.business.service.contract.RecordService;
import com.github.wp.business.vo.RecordCustom;
import com.github.wp.system.Constants;
import com.github.wp.system.pojo.SysUser;
import com.github.wp.system.util.common.Pager;
import com.github.wp.system.util.common.Pagination;
import com.github.wp.system.web.bind.annotation.CurrentUser;
import com.github.wp.system.web.spring.aspectj.SystemLog;
import com.github.wp.system.web.spring.aspectj.SystemLog.UserOperationLog;


/**
 * 开票记录管理控制器
 * @author dupeng
 * @version 1.0
 * @since 2016年8月23日
 */
@Controller
@RequestMapping("/record")
public class RecordController {
	
	@Autowired
	private RecordService recordService;

	/**
	 * 跳转到列表界面
	 * @param model
	 * @param response
	 * @return 跳转页面地址
	 * @author dupeng
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, HttpServletResponse response) {
		return "business/contract/record/list";
	}
	
	/**
	 * 查询开票记录列表
	 * @param customid
	 * @param pagination
	 * @return 分页对象数据
	 * @author 杜鹏
	 */
	@ResponseBody
	@RequestMapping(value = "/recordList")
	public Pager<RecordCustom> recordList(String code,String contractname,String customCode,String name, Pagination pagination) {
		Pager<RecordCustom> list = recordService.findPage1(code, contractname, customCode, name, pagination);
		
		return list;
		
	}
	
	@RequestMapping(value = "/recordAdd")
	public String recordAdd(Model model, HttpServletResponse response){
		HBillingrecord record = new HBillingrecord();
		model.addAttribute("record", record);
		return "business/contract/record/add";
	}
	
	@ResponseBody
	@RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
	public Map<String, Object> saveOrUpdate(Model model, HBillingrecord record,@CurrentUser SysUser user) {
		if(record.getId() != null && !record.getId().equals("")){
			record.setLastModifiedTime(new Timestamp(System.currentTimeMillis()));
			record.setLastOperator(user.getUsername());
		}else{
			record.setCreatedTime(new Timestamp(System.currentTimeMillis()));
			record.setCreator(user.getUsername());
		}
		recordService.saveOrUpdate(record);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Constants.Others.RESPONSE_MSG.value(), Constants.UserNotice.PLAN_SAVEORUPDATE_SUCESS.getInfo());
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value = "/delete")
	@SystemLog(description=UserOperationLog.CONTRACT_DELETEONE)
	public Map<String, Object> deleteOne(@RequestParam("ids[]") Long[] ids) {
		recordService.deleteOne(ids);
	    Map<String, Object> map = new HashMap<String, Object>();
	    map.put(Constants.Others.RESPONSE_MSG.value(), Constants.UserNotice.PLAN_DELETEONE_SUCESS.getInfo());
	    return map;
	}
	
	@RequestMapping(value = "/recordEdit")
	@SystemLog(description=UserOperationLog.CONTRACT_ADD)
	public String customEdit(Model model,Long id) {
		HBillingrecord record = recordService.findOne(id);
			model.addAttribute("record", record);
		
		return "business/contract/record/add";
	}
}
