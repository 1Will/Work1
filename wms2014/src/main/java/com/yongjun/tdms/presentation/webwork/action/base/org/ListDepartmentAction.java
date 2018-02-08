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
package com.yongjun.tdms.presentation.webwork.action.base.org;

import java.util.List;

import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.Filiale;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.service.base.filiale.FilialeManager;
import com.yongjun.tdms.service.base.org.DepartmentManager;

/**
 * <p>Title: ListDepartmentAction
 * <p>Description: 部门页面访问控制类</p>
 * <p>Copyright: Copyright (c) 2001 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author qs@yj-technology.com
 * @version $Id: ListDepartmentAction.java 10818 2008-01-31 02:25:29Z qsun $
 */
@Deprecated
public class ListDepartmentAction extends ValueListAction {
	private static final long serialVersionUID = -557026634791401535L;
	private final DepartmentManager departmentManager;
	private final FilialeManager filialeManager;
	private List<Department> departments;

	public ListDepartmentAction(DepartmentManager departmentManager,FilialeManager filialeManager) {
		this.departmentManager =  departmentManager;
		this.filialeManager = filialeManager;
	}

	/**
	 * 获取页面参数 <b>departmentIds</b>，如果得到，就根据ID获取部门；
	 */
	public void prepare() {
		if (null == this.departments && this.hasIds("departmentIds")) {
			this.departments = this.departmentManager.loadAllDepartments(this.getIds("departmentIds"));
		}
	}

	
	/**
	 * 删除选择的部门
	 */
	public void delete() {
		this.departmentManager.deleteAllDepartments(this.departments);
		this.addActionMessage(this.getText("department.delete.success"));
	}
	
	/**
	 * 页面执行，如果选择了失效就调用 <b>disabled</b>函数处理。
	 * 选择了有效就调用<b>endable</b>函数处理。
	 */
	public String execute() {
		if(this.isDisabled()){
			this.disabled();
		}
		if(this.isEnable()){
			this.enabled();
		}
		return SUCCESS;	
	}
	
	/**
	 * 选择的部门失效
	 * @return
	 */
    private String disabled()
    {
    	this.departmentManager.disableAllDepartments(this.departments);
    	this.addActionMessage(this.getText("departments.disable.success"));
        return SUCCESS;
    }
    /**
     * 选择的部门有效
     * @return
     */
    public String enabled() {
    	this.departmentManager.enabledAllDepartments(this.departments);
    	this.addActionMessage(this.getText("departments.enabled.success"));
		return SUCCESS;
	}



	/**
	 * 获得选择的部门列表
	 * @return
	 */
	public List<Department> getDepartment() {
		return departments;
	}

	public void setDepartment(List<Department> department) {
		this.departments = department;
	}
	
	/**
	 * 得到 <i>valueList</i>中配置的，查询hql的ID
	 */
	@Override
	protected String getAdapterName() {
		return "department";
	}
	
	   /**
     * 获取分公司的所有值
     * @return List 分公司集合
     */
	public List getFiliales() {
		return filialeManager.createSelectFilailes(this
				.getText("select.option.all"));
	}

}
