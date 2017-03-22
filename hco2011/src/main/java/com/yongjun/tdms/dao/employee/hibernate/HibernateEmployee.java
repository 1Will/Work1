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
package com.yongjun.tdms.dao.employee.hibernate;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.dao.employee.EmployeeDao;
import com.yongjun.tdms.model.employee.Employee;

/**
 * <p>
 * Title: HibernateEmployee Entity
 * <p>
 * Description: HibernateEmployee,implements EmployeeDao
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

public class HibernateEmployee extends BaseHibernateDao implements EmployeeDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yongjun.tdms.dao.employee.EmployeeDao#deleteAllEmployee(java.util.Collection)
	 */
	public void deleteAllEmployee(Collection<Employee> employees) {
		this.deleteAll(employees);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yongjun.tdms.dao.employee.EmployeeDao#deleteEmployee(com.yongjun.tdms.model.employee.Employee)
	 */
	public void deleteEmployee(Employee employee) {
		this.delete(employee);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yongjun.tdms.dao.employee.EmployeeDao#loadAllEmployee()
	 */
	public List<Employee> loadAllEmployee() {
		return this.loadAll(Employee.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yongjun.tdms.dao.employee.EmployeeDao#loadAllEmployee(java.lang.Long[])
	 */
	public List<Employee> loadAllEmployee(Long[] employeeIds) {
		return this.loadAll(Employee.class, employeeIds);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yongjun.tdms.dao.employee.EmployeeDao#loadByKey(java.lang.String,
	 *      java.lang.Object)
	 */
	public List<Employee> loadByKey(String keyName, Object employee)
			throws DaoException {
		return this.loadByKey(Employee.class, keyName, employee);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yongjun.tdms.dao.employee.EmployeeDao#loadEmployee(java.lang.Long)
	 */
	public Employee loadEmployee(Long employeeId) {
		return this.load(Employee.class, employeeId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yongjun.tdms.dao.employee.EmployeeDao#storeEmployee(com.yongjun.tdms.model.employee.Employee)
	 */
	public void storeEmployee(Employee employee) {
		this.store(employee);
	}

}
