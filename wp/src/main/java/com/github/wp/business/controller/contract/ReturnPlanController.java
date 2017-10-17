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

import com.github.wp.business.pojo.qiandan.HReturnPlan;
import com.github.wp.business.service.contract.ReturnPlanService;
import com.github.wp.business.vo.PlanCustom;
import com.github.wp.system.Constants;
import com.github.wp.system.pojo.SysUser;
import com.github.wp.system.util.common.Pager;
import com.github.wp.system.util.common.Pagination;
import com.github.wp.system.web.bind.annotation.CurrentUser;
import com.github.wp.system.web.spring.aspectj.SystemLog;
import com.github.wp.system.web.spring.aspectj.SystemLog.UserOperationLog;


/**
 * 回款计划控制器
 * @author dupeng
 * @version 1.0
 * @since 2016年8月17日
 */
@Controller
@RequestMapping("/returnPlan")
public class ReturnPlanController {
	
	@Autowired
	private ReturnPlanService returnPlanService;
	/*@Autowired
	private DatadicService datadicService;*/

	/**
	 * 跳转到列表界面
	 * @param model
	 * @param response
	 * @return 跳转页面地址
	 * @author dupeng
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, HttpServletResponse response) {
		return "business/contract/plan/list";
	}
	
	/**
	 * 查询回款计划列表
	 * @param customid
	 * @param pagination
	 * @return 分页对象数据
	 * @author 杜鹏
	 */
	@ResponseBody
	@RequestMapping(value = "/planList")
	public Pager<PlanCustom> titleList(String code,String contractname,String customCode,String name, Pagination pagination) {
		Pager<PlanCustom> list = returnPlanService.findPage1(code, contractname, customCode, name, pagination);
		
		return list;
		
	}
	
	@RequestMapping(value = "/contractAdd")
	public String contractAdd(Model model, HttpServletResponse response){
		HReturnPlan plan = new HReturnPlan();
		model.addAttribute("plan", plan);
		return "business/contract/plan/add";
	}
	
	@ResponseBody
	@RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
	public Map<String, Object> saveOrUpdate(Model model, HReturnPlan plan,@CurrentUser SysUser user) {
		if(plan.getId() != null && !plan.getId().equals("")){
			plan.setLastModifiedTime(new Timestamp(System.currentTimeMillis()));
			plan.setLastOperator(user.getUsername());
		}else{
			plan.setCreatedTime(new Timestamp(System.currentTimeMillis()));
			plan.setCreator(user.getUsername());
		}
		returnPlanService.saveOrUpdate(plan);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Constants.Others.RESPONSE_MSG.value(), Constants.UserNotice.PLAN_SAVEORUPDATE_SUCESS.getInfo());
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value = "/delete")
	@SystemLog(description=UserOperationLog.CONTRACT_DELETEONE)
	public Map<String, Object> deleteOne(@RequestParam("ids[]") Long[] ids) {
		returnPlanService.deleteOne(ids);
	    Map<String, Object> map = new HashMap<String, Object>();
	    map.put(Constants.Others.RESPONSE_MSG.value(), Constants.UserNotice.PLAN_DELETEONE_SUCESS.getInfo());
	    return map;
	}
	
	@RequestMapping(value = "/planEdit")
	@SystemLog(description=UserOperationLog.CONTRACT_ADD)
	public String customEdit(Model model,Long id) {
		HReturnPlan plan = returnPlanService.findOne(id);
			model.addAttribute("plan", plan);
		
		return "business/contract/plan/add";
	}
}
