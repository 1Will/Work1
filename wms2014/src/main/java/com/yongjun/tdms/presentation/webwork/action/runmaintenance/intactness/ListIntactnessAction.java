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
package com.yongjun.tdms.presentation.webwork.action.runmaintenance.intactness;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.runmaintenance.intactness.IntactnessManager;

import com.yongjun.tdms.model.runmaintenance.intactness.Intactness;


/**
 * <p>Title: ListIntactnessAction
 * <p>Description: 设备列表访问控制类</p>
 * <p>Copyright: Copyright (c) 2001 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id: ListIntactnessAction.java 10976 2008-02-17 09:30:23Z zbzhang $
 */
public class ListIntactnessAction extends ValueListAction {
	private static final long serialVersionUID = 4034778946163757828L;

	private final IntactnessManager intactnessManager;
	private final DepartmentManager departmentManager;

	private List<Intactness> intactness;

	public ListIntactnessAction(IntactnessManager intactnessManager,
			DepartmentManager departmentManager) {
		this.intactnessManager = intactnessManager;
		this.departmentManager = departmentManager;
	}
	
	/**
	 * 根据页面传来的鉴定单的ID集合,获取鉴定单的集合对象
	 */
	public void prepare() {
		if (this.hasIds("intactnessIds")) {
			this.intactness = this.intactnessManager.loadAllIntactnesss(this.getIds("intactnessIds"));
		}
	}
	
	public String execute() {
		if (this.isDisabled()) {
			return disabled();
		}
		if (this.isEnable()) {
			return enabled();
		}
		return SUCCESS;
	}
	
	/**
	 * 失效选择的鉴定单
	 * @return	String SUCCESS
	 */
	public String disabled() {
		this.intactnessManager.disabledAllIntactness(intactness);
	    this.addActionMessage(this.getText("intactnessBill.disabled.success"));
		return SUCCESS;
	}
	
	/**
	 * 有效选择的鉴定单
	 * @return	String SUCCESS
	 */
	public String enabled() {
		this.intactnessManager.enabledAllIntactness(intactness);
		this.addActionMessage(this.getText("intactnessBill.enabled.success"));
		return SUCCESS;
	}
	/**
	 * 得到 <i>valueList</i>中配置的，查询hql的ID
	 */
	@Override
	protected String getAdapterName() {
		return "intactnesss";
	}
	
	public List<Intactness> getIntactness() {
		return intactness;
	}

	public void setIntactness(List<Intactness> intactness) {
		this.intactness = intactness;
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
	
	/**
	 * 获取当前登陆的用户
	 * 
	 * @return User 用户实体
	 */
	public User getLoginUser() {
		return this.userManager.getUser();
	}
}
