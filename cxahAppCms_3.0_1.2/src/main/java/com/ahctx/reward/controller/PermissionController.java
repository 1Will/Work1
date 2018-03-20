package com.ahctx.reward.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ahctx.reward.entity.Permission;
import com.ahctx.reward.service.IPermissionService;
import com.ahctx.reward.service.IRolePermissionService;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * <p>
 * 角色管理相关操作
 * </p>
 *
 *
 * @Author wangping
 * @Create 2016-8-20 20:38:18
 */
@Controller
@RequestMapping("/perm/permission")
public class PermissionController extends BaseController {

	@Autowired
	private IPermissionService permissionService;

	@Autowired
	private IRolePermissionService rolePermissionService;

	@com.baomidou.kisso.annotation.Permission("2003")
	@RequestMapping("/list")
	public String list(Model model) {
		return "/permission/list";
	}

	@ResponseBody
	@com.baomidou.kisso.annotation.Permission("2003")
	@RequestMapping("/getPermissionList")
	public String getPermissionList() {
		Page<Permission> page = getPage();
		return jsonPage(permissionService.selectPage(page, null));
	}

	@ResponseBody
	@com.baomidou.kisso.annotation.Permission("2003")
	@RequestMapping("/delete/{permId}")
	public String delete(@PathVariable Long permId) {
		boolean exist = rolePermissionService.existRolePermission(permId);
		if (exist) {
			return "false";
		}
		return booleanToString(permissionService.deleteById(permId));
	}

	@com.baomidou.kisso.annotation.Permission("2003")
	@RequestMapping("/add/{permId}")
	public String add(@PathVariable Long permId, Model model) {
		Permission permission = permissionService.selectById(permId);
		model.addAttribute("permission", permission);
		return "/permission/add";
	}

	@ResponseBody
	@com.baomidou.kisso.annotation.Permission("2003")
	@RequestMapping("/addPermission")
	public String addPermission(Permission permission) {
		boolean rlt = permissionService.addPermission(permission);
		return callbackSuccess(rlt);
	}

	@com.baomidou.kisso.annotation.Permission("2003")
	@RequestMapping("/update/{permId}")
	public String update(@PathVariable Long permId, Model model) {
		Permission permission = permissionService.selectById(permId);
		Permission fpermission = permissionService.selectById(permission.getPid());
		model.addAttribute("permission", permission);
		model.addAttribute("fpermission", fpermission);
		return "/permission/update";
	}
	
	@ResponseBody
	@com.baomidou.kisso.annotation.Permission("2003")
	@RequestMapping("/updatePermission")
	public String updatePermission(Permission permission) {
		boolean rlt = permissionService.updateById(permission);
		return callbackSuccess(rlt);
	}

}
