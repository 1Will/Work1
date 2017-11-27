/*
 * Copyright (c) 2001-2009 YongJun Technology Pte.,Ltd. All Rights Reserved.
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
package com.yongjun.tdms.presentation.webwork.action.employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.base.institution.Institution;
import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.codevalue.CodeValue;
import com.yongjun.tdms.model.codevalue.Constant;
import com.yongjun.tdms.model.employee.Employee;
import com.yongjun.tdms.service.base.institution.InstitutionManager;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.codevalue.CodeValueManager;
import com.yongjun.tdms.service.employee.EmployeeManager;

/**
 * <p>
 * Title:ListEmployeeAction
 * <p>
 * Description:ListEmployeeAction
 * </p>
 * <p>
 * Copyright: Copyright (c) 2001 yj-technology
 * </p>
 * <p>
 * Company: www.yj-technology.com
 * </p>
 * 
 * @author xmZhang@yj-technology.com
 * @version
 */

public class ListEmployeeAction extends ValueListAction {

	private static final long serialVersionUID = -7952479992571055935L;

	private final EmployeeManager employeeManager; // 员工管理

	private final InstitutionManager institutionManager; // 单位管理

	private final DepartmentManager departmentManager; // 部门管理

	private final CodeValueManager codeValueManager;// 职务管理
	
	private final UserManager userManager;// 用户管理

	private List<Employee> employees;

	private String sysUser;

	public ListEmployeeAction(EmployeeManager employeeManager,
			InstitutionManager institutionManager,
			DepartmentManager departmentManager,
			CodeValueManager codeValueManager,
			UserManager userManager) {
		this.employeeManager = employeeManager;
		this.institutionManager = institutionManager;
		this.departmentManager = departmentManager;
		this.codeValueManager = codeValueManager;
		this.userManager = userManager;
	}

	@Override
	public void prepare() throws Exception {
		if (this.hasIds("employeeIds")) {
			this.employees = this.employeeManager.loadAllEmployee(this
					.getIds("employeeIds"));
		}
		//当系统用户从员工表选择是调用
		if (request.getParameter("sysUser") != null) {
			sysUser = request.getParameter("sysUser");
		}
	}

	private void delete() {
		this.employeeManager.deleteAllEmployee(employees);
	}

	@Override
	public String execute() throws Exception {
		if (this.isDisabled()) {
			return disabled();
		}
		if (this.isEnable()) {
			return enabled();
		}
		if (this.isDelete()) {
			this.delete();
		}
		return SUCCESS;
	}

	private String enabled() {
		this.employeeManager.enabledAllChecks(employees);
		this.addActionMessage(this.getText("enabled.employees.success"));
		return SUCCESS;
	}

	private String disabled() {
		this.employeeManager.disabledAllEmployees(employees);
		this.addActionMessage(this.getText("disabled.employees.success"));
		return SUCCESS;
	}

	@Override
	protected String getAdapterName() {
		return "employee";
	}

	/* 获取员工所在的公司集合 */
	public List<Institution> getAllInsts() {
		List<Institution> ins = this.institutionManager.getInstitutions();
		Institution in = new Institution();
		ins.remove(0);
		in.setId(-1L);
		in.setName(this.getText("select.option.all"));
		ins.add(0, in);
		return ins;
	}

	/* 获取员工所在的公司的部门集合 */
	public List<Department> getAllDepts() {
		List<Department> depts = new ArrayList<Department>();
		Department dept = new Department();
		dept.setId(-1L);
		dept.setName(this.getText("select.option.all"));
		depts.add(0, dept);
		return depts;
	}

	/* 获取职务集合 */
	public List<CodeValue> getAllDutys() {
		try {
			CodeValue cv = this.codeValueManager.loadByKey("code",
					Constant.DUTY).get(0);
			CodeValue c = new CodeValue();
			c.setId(-1L);
			c.setName(this.getText("select.option.all"));
			List<CodeValue> dutys = this.codeValueManager.loadByKey("parentCV",
					cv.getId());
			dutys.add(0,c);
			return dutys;
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ArrayList<CodeValue>();
		}

	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	/* 获取性别下拉列表的值 */
	@Override
	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
		if (null != request.getParameter("xb")
				&& request.getParameter("xb") != "") {
			map.put("sex", Boolean.valueOf(request.getParameter("xb")));
		}
		if (this.hasId("sysUser") || this.hasId("validUser")) {
			List<User> users = userManager.loadValidUsers();
			List<String> codes = new ArrayList<String>();
			for (int i = 0; i < users.size(); i++) {
				if(users.get(i).getCode()!=null){
					codes.add(users.get(i).getCode());
				}
			}
			if(codes.size() > 0){
				map.put("validUser",codes);
			}
		}
		return map;

	}

	public String getSysUser() {
		return sysUser;
	}

	public void setSysUser(String sysUser) {
		this.sysUser = sysUser;
	}

}
