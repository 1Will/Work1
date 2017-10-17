package com.github.wp.business.controller.baseinfo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.wp.business.pojo.base.Tdepartment;
import com.github.wp.business.service.baseinfo.DepartmentService;
import com.github.wp.system.Constants;
import com.github.wp.system.pojo.SysUser;
import com.github.wp.system.web.bind.annotation.CurrentUser;
import com.github.wp.system.web.bind.method.JsonUtil;
import com.github.wp.system.web.spring.aspectj.SystemLog;
import com.github.wp.system.web.spring.aspectj.SystemLog.UserOperationLog;

/**
 * 部门管理Controller
 * @author 杜鹏
 * @version 1.0
 * @since 07-22-16
 */
@Controller
@RequestMapping("/department")
public class departmentController {

	@Autowired
	private DepartmentService departmentService;

	/**
	 * 进入部门管理界面
	 * @param model
	 * @return
	 * @author 杜鹏
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model) {
		return "business/baseinfo/department/list";
	}

	/**
	 * 组织数据加载，通过异步树的形式加载，id可以为null，null表示查询的机构为顶级节点。
	 * @param id 组织机构id
	 * @param model
	 * @return json
	 * @author 杜鹏
	 */
	@ResponseBody
	@RequestMapping(value = "/departmentLoad")
	public List<Map<String, Object>> departmentLoad(Long id, Model model) {
		List<?> department = departmentService.findChildById(id);
		List<Map<String, Object>> li = new ArrayList<Map<String, Object>>();
		li = JsonUtil.getDepTree(department);
		return li;
	}
	
	/**
	 * 部门的详细界面
	 * @param model
	 * @param id 部门id
	 * @param response
	 * @return 界面url
	 * @author 杜鹏
	 */
	@RequestMapping(value = "/departmentDetail")
	public String departmentDetail(Model model, Long id) {
		Tdepartment tdepartment = departmentService.findOne(id);
		model.addAttribute("tdepartment", tdepartment);
		return "business/baseinfo/department/detail";
	}
	
	/**
	 * @author 杜鹏
	 * @param model
	 * @param Tdepartment
	 * @return map
	 * @since 07-21-16
	 */
	@ResponseBody
	@RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
	@SystemLog(description=UserOperationLog.TIN_SAVEORUPDATE)
	public Map<String, Object> saveOrUpdate(Model model, Tdepartment tdepartment,String type,@CurrentUser SysUser user) {
		if(type != null && type.equals("update")){
			tdepartment.setLastModifiedTime(new Timestamp(System.currentTimeMillis()));
			tdepartment.setLastOperator(user.getUsername());
		}else if(type != null && type.equals("save")){
			tdepartment.setCreatedTime(new Timestamp(System.currentTimeMillis()));
			tdepartment.setCreator(user.getUsername());
			tdepartment.setLastModifiedTime(new Timestamp(System.currentTimeMillis()));
			tdepartment.setLastOperator(user.getUsername());
		}
		departmentService.saveOrUpdate(tdepartment);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Constants.Others.RESPONSE_MSG.value(), Constants.UserNotice.DEP_SAVEORUPDATE_SUCESS.getInfo());
		return map;
	}
	
	/**
	 * 进入部门管理新增界面
	 * @param model
	 * @param parentId 部门元素的父级节点
	 * @return 界面url
	 * @author 杜鹏
	 */
	@RequestMapping(value = "/departmentAdd")
	@SystemLog(description=UserOperationLog.TIN_ADD)
	public String departmentAdd(Model model, Long parentId) {
		Tdepartment tdepartment = new Tdepartment();
		tdepartment.setDepartment(new Tdepartment(parentId));
		model.addAttribute("tdepartment", tdepartment);
		return "business/baseinfo/department/Add";
	}

	
	/**
	 * 根据部门id假删除部门对象
	 * @param id
	 * @return map
	 * @author 杜鹏
	 */
	@ResponseBody
	@RequestMapping(value = "/{id}/deleteOne")
	@SystemLog(description=UserOperationLog.DEP_DELETEONE)
	public Map<String, Object> deleteOne(@PathVariable("id") Long id) {
		departmentService.deleteOne(id);
	    Map<String, Object> map = new HashMap<String, Object>();
	    map.put(Constants.Others.RESPONSE_MSG.value(), Constants.UserNotice.DEP_DELETEONE_SUCESS.getInfo());
	    return map;
	}
}
