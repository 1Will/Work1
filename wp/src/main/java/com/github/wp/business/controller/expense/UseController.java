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

import com.github.wp.business.pojo.cw.CExpenseuse;
import com.github.wp.business.service.expense.UseService;
import com.github.wp.system.Constants;
import com.github.wp.system.pojo.SysUser;
import com.github.wp.system.service.DatadicService;
import com.github.wp.system.util.common.Pager;
import com.github.wp.system.util.common.Pagination;
import com.github.wp.system.web.bind.annotation.CurrentUser;


/**
 * 费用使用控制器
 * @author dupeng
 * @version 1.0
 * @since 2016年8月29日
 */
@Controller
@RequestMapping("/use")
public class UseController {
	
	@Autowired
	private UseService useService;
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
		return "business/expense/use/list";
	}
	
	/**
	 * 查询费用使用列表
	 * @param CExpenseuse
	 * @param pagination
	 * @return 分页对象数据
	 * @author 杜鹏
	 */
	@ResponseBody
	@RequestMapping(value = "/useList")
	public Pager<CExpenseuse> titleList(CExpenseuse use, Pagination pagination) {
		Pager<CExpenseuse> list = useService.findPage(use, pagination);
		
		return list;
		
	}
	
	@RequestMapping(value = "/useAdd")
	public String useAdd(Model model, HttpServletResponse response){
		CExpenseuse use = new CExpenseuse();
		
		model.addAttribute("use", use);
		
		
		return "business/expense/use/add";
	}
	
	/**
	 * @author 杜鹏
	 * @param model
	 * @param CExpenseuse
	 * @return map
	 * @since 08-29-16
	 */
	@ResponseBody
	@RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
	public Map<String, Object> saveOrUpdate(Model model, CExpenseuse use,@CurrentUser SysUser user) {
		if(use.getId() != null && !use.getId().equals("")){
			use.setLastModifiedTime(new Timestamp(System.currentTimeMillis()));
			use.setLastOperator(user.getUsername());
		}else{
			use.setCreatedTime(new Timestamp(System.currentTimeMillis()));
			use.setCreator(user.getUsername());
		}
		useService.saveOrUpdate(use);
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
		useService.deleteOne(ids);
	    Map<String, Object> map = new HashMap<String, Object>();
	    map.put(Constants.Others.RESPONSE_MSG.value(), Constants.UserNotice.CUSTOM_DELETEONE_SUCESS.getInfo());
	    return map;
	}
	
	/**
	 * 进入费用使用修改界面
	 * @param model
	 * @return 界面url
	 * @author 杜鹏
	 */
	@RequestMapping(value = "/useEdit")
	public String useEdit(Model model,Long id) {
		CExpenseuse use = useService.findOne(id);
		model.addAttribute("use", use);
		
		return "business/expense/use/add";
	}

}
