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
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.base.institution.Institution;
import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.base.area.Area;
import com.yongjun.tdms.model.codevalue.CodeValue;
import com.yongjun.tdms.model.codevalue.Constant;
import com.yongjun.tdms.model.employee.Employee;
import com.yongjun.tdms.service.base.area.AreaManager;
import com.yongjun.tdms.service.base.institution.InstitutionManager;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.codevalue.CodeValueManager;
import com.yongjun.tdms.service.employee.EmployeeManager;

/**
 * <p>
 * Title:EditEmployeeAction
 * <p>
 * Description:EditEmployeeAction
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

public class EditEmployeeAction extends PrepareAction {
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork.Preparable#prepare()
	 */
	private static final long serialVersionUID = 6319367396099686081L;

	private final EmployeeManager employeeManager; // 员工管理

	private final InstitutionManager institutionManager; // 单位管理

	private final DepartmentManager departmentManager;// 部门管理

	private final AreaManager areaManager;// 籍贯管理

	private final CodeValueManager codeValueManager;// 政治面貌管理

	private Employee employee;

	public EditEmployeeAction(EmployeeManager employeeManager,
			InstitutionManager institutionManager,
			DepartmentManager departmentManager, AreaManager areaManager,
			CodeValueManager codeValueManager) {
		this.employeeManager = employeeManager;
		this.institutionManager = institutionManager;
		this.departmentManager = departmentManager;
		this.areaManager = areaManager;
		this.codeValueManager = codeValueManager;
	}

	public void prepare() throws Exception {
		if (this.employee == null && this.hasId("employee.id")) {
			this.employee = this.employeeManager.loadEmployee(this
					.getId("employee.id"));
		} else {
			this.employee = new Employee();
		}
	}

	public String save() {
		boolean isNew = this.employee.isNew();
		// 获取公司的值
		if (!StringUtils.isEmpty(request.getParameter("inst.id"))) {
			this.employee.setInst(this.institutionManager.loadInstitution(Long
					.valueOf(request.getParameter("inst.id"))));
		}
		// 获取部门的值
		if (!StringUtils.isEmpty(request.getParameter("dept.id"))) {
			this.employee.setDept(this.departmentManager.loadDepartment(Long
					.valueOf(request.getParameter("dept.id"))));
		}
		// 获取职务的值
		if (!StringUtils.isEmpty(request.getParameter("duty"))) {
			// this.employee.setDuty(request.getParameter("duty"));
			this.employee.setDuty(this.codeValueManager.loadCodeValue(Long
					.valueOf(request.getParameter("duty"))));
		}
		// 获取工作方式的值
		if (!StringUtils.isEmpty(request.getParameter("workingmode"))) {
			this.employee.setWorkingMode(this.codeValueManager
					.loadCodeValue(Long.valueOf(request
							.getParameter("workingmode"))));
		}
		// 获取性别的值
		if (!StringUtils.isEmpty(request.getParameter("sex"))) {
			if ("true".equals(request.getParameter("sex"))) {
				this.employee.setSex(true);
			} else {
				this.employee.setSex(false);
			}
		}
		// 获取籍贯的值
		if (!StringUtils.isEmpty(request.getParameter("nationality.id"))) {
			this.employee.setNationality(this.areaManager.loadArea(Long
					.valueOf(request.getParameter("nationality.id"))));
		}
		// 获取政治面貌的值
		if (!StringUtils.isEmpty(request.getParameter("politics.id"))) {
			this.employee.setPolitics(this.codeValueManager.loadCodeValue(Long
					.valueOf(request.getParameter("politics.id"))));
		}
		// 获取是否销售的值
		if (!StringUtils.isEmpty(request.getParameter("sales"))) {
			if ("true".equals(request.getParameter("sales"))) {
				this.employee.setIsSale(true);
			} else {
				this.employee.setIsSale(false);
			}
		}
		// 获取是否系统用户的值
		if (!StringUtils.isEmpty(request.getParameter("sys"))) {
			if ("true".equals(request.getParameter("sys"))) {
				this.employee.setIsSysUser(true);
			} else {
				this.employee.setIsSysUser(false);
			}
		}
		// 获取婚姻状况的值
		if (!StringUtils.isEmpty(request.getParameter("isMarried"))) {
			this.employee.setIsMarried(this.codeValueManager.loadCodeValue(Long
					.valueOf(request.getParameter("isMarried"))));
		}
		// 获取状态的值
		if (!StringUtils.isEmpty(request.getParameter("status"))) {
			this.employee.setStatus(this.codeValueManager.loadCodeValue(Long
					.valueOf(request.getParameter("status"))));
		}
		try {
			if (isNew) {
				// 员工编号唯一性验证
				if (null == this.employeeManager.loadByKey("employeeNo",
						this.employee.getEmployeeNo())) {
					// 身份证号唯一性验证
					if (null == this.employeeManager.loadByKey("identityCard",
							this.employee.getIdentityCard())) {
						this.employeeManager.storeEmployee(this.employee);
					} else {
						this.addActionMessage(this.getText(
								"identityCard.add.exist", Arrays
										.asList(new Object[] { this.employee
												.getIdentityCard() })));
						return ERROR;
					}
				} else {
					this.addActionMessage(this.getText("employeeNo.add.exist",
							Arrays.asList(new Object[] { this.employee
									.getEmployeeNo() })));
					return ERROR;
				}

			} else {
				this.employeeManager.storeEmployee(this.employee);
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionMessage(this.getText("employeeNo.add.exist", Arrays
					.asList(new Object[] { this.employee.getEmployeeNo() })));
			return ERROR;
		}

		if (isNew) {
			this.addActionMessage(this.getText("employee.add.success", Arrays
					.asList(new Object[] { this.employee.getEmployeeNo() })));
			return NEW;
		} else {
			this.addActionMessage(this.getText("employee.edit.success", Arrays
					.asList(new Object[] { this.employee.getEmployeeNo() })));
			return SUCCESS;
		}
	}

	/* 获取员工所在的公司集合 */
	public List<Institution> getAllInsts() {
		List<Institution> ins = this.institutionManager.getInstitutions();
		Institution in = new Institution();
		ins.remove(0);
		in.setId(-1L);
		in.setName("");
		if (null == ins) {
			ins = new ArrayList<Institution>();
		}
		ins.add(0, in);
		return ins;
	}

	/* 获取员工所在的公司的部门集合 */
	public List<Department> getAllDepts() {
		List<Department> depts = new ArrayList<Department>();
		Department dept = new Department();
		dept.setId(-1L);
		dept.setName("");
		if (null == depts) {
			depts = new ArrayList<Department>();
		}
		depts.add(0, dept);
		return depts;
	}

	/* 获取所有籍贯集合 */
	public List<Area> getAllNationality() {
		try {
			Area ar = this.areaManager.loadByKey("code", Constant.CHN).get(0);
			Area a = new Area();
			a.setId(-1L);
			a.setName("");
			List<Area> list = this.areaManager.loadByKey("parentArea", ar
					.getId());
			list.add(0, a);
			if (null == list) {
				return new ArrayList<Area>();
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<Area>();
		}
	}

	/* 获取所有政治面貌集合 */
	public List<CodeValue> getAllPolitics() {
		try {
			CodeValue cv = this.codeValueManager.loadByKey("code",
					Constant.POLITICAL).get(0);
			List<CodeValue> list = this.codeValueManager.loadByKey("parentCV",
					cv.getId());
			if (null == list) {
				return new ArrayList<CodeValue>();
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<CodeValue>();
		}
		
	}

	/* 获取职务集合 */
	public List<CodeValue> getAllDutys() {
		try {
			CodeValue cv = this.codeValueManager.loadByKey("code",
					Constant.DUTY).get(0);
			CodeValue c = new CodeValue();
			c.setId(-1L);
			c.setName("");
			List<CodeValue> dutys = this.codeValueManager.loadByKey("parentCV",
					cv.getId());
			dutys.add(0, c);
			if (null == dutys) {
				return new ArrayList<CodeValue>();
			}
			return dutys;
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ArrayList<CodeValue>();
		}

	}

	/* 获取所有婚姻状况集合 */
	public List<CodeValue> getAllMarries() {
		try {
			CodeValue cv = this.codeValueManager.loadByKey("code",
					Constant.MARRIAGE_STATE).get(0);
			List<CodeValue> list = this.codeValueManager.loadByKey("parentCV",
					cv.getId());
			if (null == list) {
				return new ArrayList<CodeValue>();
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<CodeValue>();
		}

	}

	/* 获取所有状态集合 */
	public List<CodeValue> getAllStatus() {
		try {
			CodeValue cv = this.codeValueManager.loadByKey("code",
					Constant.EMPLOYEE_STATE).get(0);
			List<CodeValue> list = this.codeValueManager.loadByKey("parentCV",
					cv.getId());
			if (null == list) {
				return new ArrayList<CodeValue>();
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<CodeValue>();
		}

	}

	/* 获取所有工作方式集合 */
	public List<CodeValue> getAllWorkingModes() {
		try {
			CodeValue cv = this.codeValueManager.loadByKey("code",
					Constant.WORK_MODE).get(0);
			List<CodeValue> list = this.codeValueManager.loadByKey("parentCV",
					cv.getId());
			if (null == list) {
				return new ArrayList<CodeValue>();
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<CodeValue>();
		}

	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}
