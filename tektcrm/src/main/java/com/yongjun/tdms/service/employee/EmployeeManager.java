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
package com.yongjun.tdms.service.employee;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.employee.Employee;

/**
 * <p>
 * Title:EmployeeManager
 * <p>
 * Description:EmployeeManager
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

public interface EmployeeManager {

	/* 保存员工 */
	public void storeEmployee(Employee employee);

	/* 删除员工 */
	public void deleteEmployee(Employee employee);

	/* 删除所有员工集合 */
	public void deleteAllEmployee(Collection<Employee> employees);

	/* 根据员工编号集合，加载所有员工 */
	public List<Employee> loadAllEmployee(Long[] employeeIds);

	/* 根据员工编号，加载员工 */
	public Employee loadEmployee(Long employeeId);

	/* 加载所有员工 */
	public List<Employee> loadAllEmployee();

	/* 根据keyName,加载所有员工集合 */
	public List<Employee> loadByKey(String keyName, Object employee)
			throws DaoException;
	
	/* 失效所有员工 */
	void disabledAllEmployees(Collection<Employee> employees);

	/* 有效所有员工 */
	void enabledAllChecks(Collection<Employee> employees);
}
