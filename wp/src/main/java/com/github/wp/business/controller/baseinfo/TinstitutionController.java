package com.github.wp.business.controller.baseinfo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.wp.business.pojo.base.Tinstitution;
import com.github.wp.business.service.baseinfo.TinstitutionService;
import com.github.wp.system.Constants;
import com.github.wp.system.pojo.SysUser;
import com.github.wp.system.web.bind.annotation.CurrentUser;
import com.github.wp.system.web.bind.method.JsonUtil;
import com.github.wp.system.web.spring.aspectj.SystemLog;
import com.github.wp.system.web.spring.aspectj.SystemLog.UserOperationLog;

/**
 * 组织管理（单位）Controller
 * @author 杜鹏
 * @version 1.0
 * @since 07-20-16
 */
@Controller
@RequestMapping("/tinstitution")
public class TinstitutionController {

	@Autowired
	private TinstitutionService tinstitutionService;

	/**
	 * 进入组织机构管理界面
	 * @param model
	 * @return
	 * @author 杜鹏
	 */
	@RequestMapping(method = RequestMethod.GET)
	@SystemLog(description=UserOperationLog.ORG_LIST)
	public String list(Model model) {
		return "business/baseinfo/tinstitution/list";
	}

	/**
	 * 组织数据加载，通过异步树的形式加载，id可以为null，null表示查询的机构为顶级节点。
	 * @param id 组织机构id
	 * @param model
	 * @return json
	 * @author 杜鹏
	 */
	@ResponseBody
	@RequestMapping(value = "/tinstitutionLoad")
	public List<Map<String, Object>> tinstitutionLoad(Long id, Model model) {
		List<?> tinstitution = tinstitutionService.findChildById(id);
		List<Map<String, Object>> li = new ArrayList<Map<String, Object>>();
		li = JsonUtil.getTinstituTree(tinstitution);
		return li;
	}
	
	/**
	 * 单位的详细界面
	 * @param model
	 * @param id 单位id
	 * @param response
	 * @return 界面url
	 * @author 杜鹏
	 */
	@RequestMapping(value = "/tinstitutionDetail")
	@SystemLog(description=UserOperationLog.ORG_ORGANIZATIONDETAIL)
	@RequiresPermissions(Constants.SysPermission.ORG_ORGANIZATIONDETAIL)
	public String tinstitutionDetail(Model model, Long id,
			HttpServletResponse response) {
		Tinstitution tinstitution = tinstitutionService.findOne(id);
		model.addAttribute("tinstitution", tinstitution);
		return "business/baseinfo/tinstitution/detail";
	}
	
	/**
	 * @author 杜鹏
	 * @param model
	 * @param tinstitution
	 * @return map
	 * @since 07-21-16
	 */
	@ResponseBody
	@RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
	@SystemLog(description=UserOperationLog.TIN_SAVEORUPDATE)
	public Map<String, Object> saveOrUpdate(Model model, Tinstitution tinstitution,String type,@CurrentUser SysUser user) {
		if(type != null && type.equals("update")){
			tinstitution.setLastModifiedTime(new Timestamp(System.currentTimeMillis()));
			tinstitution.setLastOperator(user.getUsername());
		}else if(type != null && type.equals("save")){
			tinstitution.setCreatedTime(new Timestamp(System.currentTimeMillis()));
			tinstitution.setCreator(user.getUsername());
			tinstitution.setLastModifiedTime(new Timestamp(System.currentTimeMillis()));
			tinstitution.setLastOperator(user.getUsername());
		}
		tinstitutionService.saveOrUpdate(tinstitution);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Constants.Others.RESPONSE_MSG.value(), Constants.UserNotice.TIN_SAVEORUPDATE_SUCESS.getInfo());
		return map;
	}
	
	/**
	 * 进入单位管理新增界面
	 * @param model
	 * @param parentId 单位元素的父级节点
	 * @return 界面url
	 * @author 杜鹏
	 */
	@RequestMapping(value = "/tinstitutionAdd")
	@SystemLog(description=UserOperationLog.TIN_ADD)
	public String organizationAdd(Model model, Long parentId) {
		Tinstitution tinstitution = new Tinstitution();
		tinstitution.setSysTinstitution(new Tinstitution(parentId));
		model.addAttribute("tinstitution", tinstitution);
		return "business/baseinfo/tinstitution/tinstitutionAdd";
	}

	
	/**
	 * 根据单位id假删除单位对象
	 * @param id
	 * @return map
	 * @author 杜鹏
	 */
	@ResponseBody
	@RequestMapping(value = "/{id}/deleteOne")
	@SystemLog(description=UserOperationLog.TIN_DELETEONE)
	public Map<String, Object> deleteOne(@PathVariable("id") Long id) {
		tinstitutionService.deleteOne(id);
	    Map<String, Object> map = new HashMap<String, Object>();
	    map.put(Constants.Others.RESPONSE_MSG.value(), Constants.UserNotice.TIN_DELETEONE_SUCESS.getInfo());
	    return map;
	}
}
