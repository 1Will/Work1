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
package com.yongjun.tdms.service.employee.pojo;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.tdms.dao.employee.EmployeeDao;
import com.yongjun.tdms.model.employee.Employee;
import com.yongjun.tdms.service.employee.EmployeeManager;

/**
 * <p>
 * Title:DefaultEmployeeManager Entity
 * <p>
 * Description: DefaultEmployeeManager,implement EmployeeManager
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

public class DefaultEmployeeManager extends BaseManager implements
		EmployeeManager {

	private final EmployeeDao employeeDao;

	public DefaultEmployeeManager(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}

	public void deleteAllEmployee(Collection<Employee> employees) {
		this.employeeDao.deleteAllEmployee(employees);
	}

	public void deleteEmployee(Employee employee) {
		this.employeeDao.deleteEmployee(employee);
	}

	public void disabledAllEmployees(Collection<Employee> employees) {
		for (Employee employee : employees) {
			employee.setDisabled(true);
			this.employeeDao.storeEmployee(employee);
		}
	}

	public void enabledAllChecks(Collection<Employee> employees) {
		for (Employee employee : employees) {
			employee.setDisabled(false);
			this.employeeDao.storeEmployee(employee);
		}
	}

	public List<Employee> loadAllEmployee(Long[] employeeIds) {
		return this.employeeDao.loadAllEmployee(employeeIds);
	}

	public List<Employee> loadAllEmployee() {
		return this.employeeDao.loadAllEmployee();
	}

	public List<Employee> loadByKey(String keyName, Object employee)
			throws DaoException {
		return this.employeeDao.loadByKey(keyName, employee);
	}

	public Employee loadEmployee(Long employeeId) {
		return this.employeeDao.loadEmployee(employeeId);
	}

	public void storeEmployee(Employee employee) {
		this.employeeDao.storeEmployee(employee);
	}

}
