/*
 * Copyright (c) 2001-2007 YongJun Technology Pte.,Ltd. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of YongJun
 * Technology Pte.,Ltd. ("Confidential Information"). You shall not disclose
 * such Confidential Information and shall use it only in accordance with the
 * terms of the license agreement you entered into with YongJun.
 * 
 * YONGJUN MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
 * SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, OR
 * NON-INFRINGEMENT. YONGJUN SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY
 * LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
 * DERIVATIVES.
 */
package com.yongjun.tdms.presentation.webwork.action.runmaintenance.usualcheck;

import com.yongjun.pluto.model.LabelValue;
import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.SysModel;
import com.yongjun.tdms.model.runmaintenance.unused.UnUsedStatus;
import com.yongjun.tdms.model.runmaintenance.usualcheck.Check;
import com.yongjun.tdms.model.runmaintenance.usualcheck.CheckStatus;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.runmaintenance.usualcheck.CheckManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
/**
 * <p>Title: ListCheckAction
 * <p>Description: 设备或工装的日常检查列表访问控制类</p>
 * <p>Copyright: Copyright (c) 2001 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id:  $
 */
public class ListCheckAction extends ValueListAction {
	private static final long serialVersionUID = -6602736879243586775L;
	
	private final CheckManager checkManager;
	private final DepartmentManager departmentManager;
	private final UserManager userManager;
	
	private List<Check> checks;
	private String toolingDevFlag;                       //资产标识[工装]|[设备]
	
	public ListCheckAction(CheckManager checkManager, 
			DepartmentManager departmentManager,
			UserManager userManager) {
		this.checkManager = checkManager;
		this.departmentManager = departmentManager;
		this.userManager = userManager;
	}

	/**
	 * 根据页面传来的日常检查的ID集合,获取日常检查的集合对象
	 */
	public void prepare() {
		if (this.hasIds("checkIds")) {
			this.checks = this.checkManager.loadAllChecks(this.getIds("checkIds"));
		}
		if (this.hasId("toolingDevFlag")) {
			this.toolingDevFlag = request.getParameter("toolingDevFlag");
		}
	}
	
	/**
	 * 如果点击删除按钮,执行删除
	 */
	public String execute() {
		if (this.isDisabled()) {
			return disabled();
		}
		if (this.isEnable()) {
			return enabled();
		}
		return SUCCESS;
	}
	
	public String delete() {
		this.checkManager.deleteAllChecks(checks);
		this.addActionMessage(this.getText("delete.checks.success"));
		return SUCCESS;
	}
	
	public String disabled() {
		try {
			this.checkManager.disabledAllChecks(checks);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionMessage(this.getText("uausalCheck.disabled.failer"));
			return ERROR;
		}
	
		this.addActionMessage(this.getText("disabled.checks.success"));
		return SUCCESS;
	}
	
	public String enabled() {
		this.checkManager.enabledAllChecks(checks);
		this.addActionMessage(this.getText("enabled.checks.success"));
		return SUCCESS;
	}
	
	/**
	 * 得到 <i>valueList</i>中配置的，查询hql的ID
	 */
	@Override
	protected String getAdapterName() {
		if (toolingDevFlag.equals(SysModel.DEVICE.toString())) {
			return "deviceChecks";
		} else {
			return "toolingChecks";
		}
	}
	
	/**
	 * 如果不是点击查询按钮，则默认是根据当前登陆人的部门来对日常检查结果进行筛选
	 */
	@SuppressWarnings("unchecked")
	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
		if (!this.isSearch()) {
			map.put("department.id", this.getLoginUser().getDepartment().getId());
		}
		return map;
	}
	/**
	 * 判断是否点击了search按钮
	 * 
	 * @param 
	 * @return 是为true,否为false
	 */
	public boolean isSearch() {
		return this.hasKey("search");
	}
	/**
	 * 如果当前用户只能看本部门数据，则获取该部门数据，否则获取所有部门 
	 * @return List 部门集合
	 */
	public List getDepartments() {
		if (!this.userManager.getUser().isViewAll()) {           //如果用户只有看本部门的权限
			List<Department> list = new ArrayList<Department>();
			if (null == this.userManager.getUser().getDepartment()) {      //如果用户不属于任何部门,置部门ID为-2
				Department department = new Department();
				department.setId(Long.valueOf(-2L));
				department.setName("");
				list.add(department);
			} else {
				list.add(this.departmentManager.loadDepartment(this.userManager.getUser().getDepartment().getId()));
			}
			//显示附属部门
			Set<Department> depts =userManager.getUser().getDepartments();
			if(depts!=null){
				list.addAll(depts);
			}
			return list;
		}
		return departmentManager.createSelectDepartments(this
				.getText("select.option.all"));
	}
	public List<LabelValue> getUsualCheckStatus() {
		LabelValue[] arrays = this.wrapEnum(CheckStatus.class);
		LabelValue labelValue = new LabelValue(Long.valueOf(-1L).toString(),
				this.getText("select.option.all"));
		List<LabelValue> tmp = new ArrayList<LabelValue>();
		tmp.add(labelValue);
		for (int i = 0; i < arrays.length; i++) {
			tmp.add(arrays[i]);
		}
		return tmp;
	}
	/**
	 * 获取当前登陆的用户
	 * 
	 * @return User 用户实体
	 */
	public User getLoginUser() {
		return this.userManager.getUser();
	}

	public String getToolingDevFlag() {
		return toolingDevFlag;
	}

	public void setToolingDevFlag(String toolingDevFlag) {
		this.toolingDevFlag = toolingDevFlag;
	}

}
