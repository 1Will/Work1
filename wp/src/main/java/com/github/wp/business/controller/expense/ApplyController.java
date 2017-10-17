package com.github.wp.business.controller.expense;


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

import com.github.wp.business.pojo.cw.CExpenseapply;
import com.github.wp.business.service.expense.ApplyService;
import com.github.wp.system.Constants;
import com.github.wp.system.pojo.SysUser;
import com.github.wp.system.service.DatadicService;
import com.github.wp.system.util.common.Pager;
import com.github.wp.system.util.common.Pagination;
import com.github.wp.system.web.bind.annotation.CurrentUser;


/**
 * 费用申请控制器
 * @author dupeng
 * @version 1.0
 * @since 2016年8月26日
 */
@Controller
@RequestMapping("/apply")
public class ApplyController {
	
	@Autowired
	private ApplyService applyService;
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
		return "business/expense/apply/list";
	}
	
	/**
	 * 查询费用申请列表
	 * @param CExpenseapply
	 * @param pagination
	 * @return 分页对象数据
	 * @author 杜鹏
	 */
	@ResponseBody
	@RequestMapping(value = "/applyList")
	public Pager<CExpenseapply> titleList(CExpenseapply apply, Pagination pagination) {
		Pager<CExpenseapply> list = applyService.findPage(apply, pagination);
		
		return list;
		
	}
	
	@RequestMapping(value = "/applyAdd")
	public String applyAdd(Model model, HttpServletResponse response){
		CExpenseapply apply = new CExpenseapply();
		
		model.addAttribute("apply", apply);
		
		
		return "business/expense/apply/add";
	}
	
	/**
	 * @author 杜鹏
	 * @param model
	 * @param CExpenseapply
	 * @return map
	 * @since 08-26-16
	 */
	@ResponseBody
	@RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
	public Map<String, Object> saveOrUpdate(Model model, CExpenseapply apply,@CurrentUser SysUser user) {
		if(apply.getId() != null && !apply.getId().equals("")){
			apply.setLastModifiedTime(new Timestamp(System.currentTimeMillis()));
			apply.setLastOperator(user.getUsername());
		}else{
			apply.setCreatedTime(new Timestamp(System.currentTimeMillis()));
			apply.setCreator(user.getUsername());
		}
		applyService.saveOrUpdate(apply);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Constants.Others.RESPONSE_MSG.value(), Constants.UserNotice.CUSTOM_SAVEORUPDATE_SUCESS.getInfo());
		return map;
	}
	
	/**
	 * 根据id假删除
	 * @param id
	 * @return map
	 * @author 杜鹏
	 */
	@ResponseBody
	@RequestMapping(value = "/delete")
	public Map<String, Object> deleteOne(@RequestParam("ids[]") Long[] ids) {
		applyService.deleteOne(ids);
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
	@RequestMapping(value = "/applyEdit")
	public String applyEdit(Model model,Long id) {
		CExpenseapply apply = applyService.findOne(id);
		model.addAttribute("apply", apply);
		
		return "business/expense/apply/add";
	}

}
