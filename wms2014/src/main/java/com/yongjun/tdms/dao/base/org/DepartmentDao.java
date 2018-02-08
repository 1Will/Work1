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
package com.yongjun.tdms.dao.base.org;

import java.util.Collection;
import java.util.List;
import java.util.Set;



import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.ProductionLine;

/**
 * <p>Title: DepartmentDao
 * <p>Description: 部门数据访问接口定义类</p>
 * <p>Copyright: Copyright (c) 2001 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author qs@yj-technology.com
 * @version $Id: DepartmentDao.java 10818 2008-01-31 02:25:29Z qsun $
 */
public interface DepartmentDao {
	/**
	 * 保存部门
	 * 
	 * @param department	部门实体
	 * @return
	 */
	public void storeDepartment(Department department);
	
	/**
	 * 删除部门
	 * @param department	部门实体
	 * @return
	 */
	public void deleteDepartment(Department department);
	
	/**
	 * 根据传入的部门ID集合，删除集合中的部门
	 * 
	 * @param departmentIds	部门ID集合
	 * @return
	 */
	public void deleteAllDepartments(Collection<Department> departmentIds);
	
	/**
	 * 根据传入的部门ID集合，获取集合中的部门
	 * 
	 * @param departmentIds	部门ID集合
	 * @return List			部门集合
	 */
	public List<Department> loadAllDepartment(Long [] departmentIds);
	
	/**
	 * 根据传入的部门ID，获取部门
	 * 
	 * @param departmentId		部门ID
	 * @return Department 		部门实体
	 */
	public Department loadDepartment(Long departmentId);
	
	/**
	 * 获取所有的部门
	 * 
	 * @return	List	部门集合
	 */
	public List<Department> loadAllDepartments();
	
	List<ProductionLine> getUnJoinedProductionLines();

	/* Modify at 2009-08-24 16:15:00 by wliu*/
	/**
	 * 根据部门代码查询部门信息
	 * 
	 * @param code 部门代码
	 * @return 部门信息
	 */
	public Department loadDepartmentByCode(String code);
	
	/* Modify at 2009-08-25 10:00:00 by wliu*/
	/**
	 * 根据部门名称查询部门信息
	 * 
	 * @param name 部门名称
	 * @return 部门信息
	 */
	public Department loadDepartmentByName(String name);
	
	/**
	 *  根据分公司 级联 部门 
	 * @param filialeId
	 * @return 部门信息集合
	 */
	public List<Department> filialeSelectDept(Long filialeId);
	/**
	 * 根据分公司 级联 部门 
	 * @param filialeId
	 * @return
	 */
	
	public List<Department> loadDepartmentByFiliale(Long filialeId);
	
}
