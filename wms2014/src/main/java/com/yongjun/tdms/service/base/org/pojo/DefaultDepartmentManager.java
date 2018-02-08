/*
 * Copyright (c) 2001-2005 YongJun Digital Information Technology Co.,Ltd. All
 * Rights Reserved.
 * 
 * This software is the confidential and proprietary information of YongJun
 * Digital Information Technology Co.,Ltd. ("Confidential Information"). You
 * shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into with
 * YongJun.
 * 
 * YONGJUN MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
 * SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, OR
 * NON-INFRINGEMENT. YONGJUN SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY
 * LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
 * DERIVATIVES.
 */
package com.yongjun.tdms.service.base.org.pojo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.tdms.dao.base.org.DepartmentDao;
import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.Filiale;
import com.yongjun.pluto.model.security.ProductionLine;
import com.yongjun.pluto.model.security.User;
import com.yongjun.tdms.service.base.org.DepartmentManager;

/**
 * <p>Title: DefaultDepartmentManager
 * <p>Description: 部门管理业务实现类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author qs@yj-technology.com
 * @version $Id: DefaultDepartmentManager.java 10818 2008-01-31 02:25:29Z qsun $
 * @see com.yongjun.tdms.service.base.org.DepartmentManager
 */
public class DefaultDepartmentManager extends BaseManager implements DepartmentManager {
	private final DepartmentDao departmentDao;
	
	public DefaultDepartmentManager(DepartmentDao departmentDao) {
		this.departmentDao = departmentDao;
	}

	public void storeDepartment(Department department) {
		this.departmentDao.storeDepartment(department);
		
	}

	public Department loadDepartment(Long departmentId) {
		return this.departmentDao.loadDepartment(departmentId);
	}

	public void deleteDepartment(Department department) {
		this.departmentDao.deleteDepartment(department);
		
	}

	public void deleteAllDepartments(Collection<Department> departmentIds) {
		this.departmentDao.deleteAllDepartments(departmentIds);
		
	}

	public List<Department> loadAllDepartments(Long[] departmentIds) {
		return this.departmentDao.loadAllDepartment(departmentIds);
	}

	public List<Department> loadAllDepartments() {
		return this.departmentDao.loadAllDepartments();
	}
	
	public List<ProductionLine> getUnJoinedProductionLines(){
		return this.departmentDao.getUnJoinedProductionLines();
	}
	
	public List createSelectDepartments(String name) {
		List<Department> list = departmentDao.loadAllDepartments();
		Department dept = new Department();
		dept.setId(Long.valueOf(-1L));
		dept.setName(name);
		list.add(0, dept);
		return list;
	}

	public List createSelectPurchaseConDepartment(String name) {
		List<Department> list = departmentDao.loadAllDepartments();
		Department dept = new Department();
	//	dept.setId(Long.valueOf(-1L));
		dept.setName(name);
		list.add(0, dept);
		return list;
	}

	public void disableAllDepartments(List<Department> departments) {
		for(Department department : departments){
			department.setDisabled(true);
			this.departmentDao.storeDepartment(department);
		}
	}

	public void enabledAllDepartments(Collection<Department> departments) {
		for(Department department : departments){
			department.setDisabled(false);
			this.departmentDao.storeDepartment(department);
		}
	}
	
	/* Modify at 2009-08-24 16:15:00 by wliu*/
	/**
	 * 根据部门代码查询部门信息
	 * 
	 * @param code 部门代码
	 * @return 部门信息
	 */
	public Department loadDepartmentByCode(String code){
		
		return this.departmentDao.loadDepartmentByCode(code);
	}
	
	/* Modify at 2009-08-25 10:00:00 by wliu*/
	/**
	 * 根据部门名称查询部门信息
	 * 
	 * @param name 部门名称
	 * @return 部门信息
	 */
	public Department loadDepartmentByName(final String name){
		
		return this.departmentDao.loadDepartmentByName(name);
	}
	
	public List<Department> filialeSelectDept(String filialeId) {
		List<Department> list = new ArrayList<Department>();
		if (Long.parseLong(filialeId) != -1) {
			list = this.departmentDao.filialeSelectDept(Long.parseLong(filialeId));
		}
		Department dept = new Department();
		dept.setId(Long.valueOf(-1L));
	    dept.setName("所有");
		list.add(0,dept);
		return list;
		
//		return this.departmentDao.filialeSelectDept(Long.parseLong(filialeId));
	}

	public List<Department> loadDepartmentByFiliale(Long filialeId) {
		return this.departmentDao.loadDepartmentByFiliale(filialeId);
	}
	
}
