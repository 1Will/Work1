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
package com.yongjun.tdms.service.base.org;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.transaction.annotation.Transactional;

import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.ProductionLine;

/**
 * <p>Title: DepartmentManager
 * <p>Description: 部门管理业务接口定义类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author qs@yj-technology.com
 * @version $Id: DepartmentManager.java 10818 2008-01-31 02:25:29Z qsun $
 */
@Transactional(readOnly = true)
public interface DepartmentManager {
	
	/**
     * 传入部门实体，保存到数据库
     * 
     * @param department	部门实体
     * @return
     */
	@Transactional
	public void storeDepartment(Department department);
	
	/**
	 * 删除传入的部门实体
	 * 
	 * @param department	部门实体
	 * @return 
	 */
	@Transactional
	public void deleteDepartment(Department department);
	
	/**
	 * 根据传入的部门ID集合，删除集合中的部门
	 * 
	 * @param departmentIds	部门ID集合
	 * @return
	 */
	@Transactional
	public void deleteAllDepartments(Collection<Department> departmentIds);
	
	/**
	 * 根据传入的部门ID集合，获取集合中的部门
	 * 
	 * @param departmentIds	部门ID集合
	 * @return List			部门集合
	 */
	public List<Department> loadAllDepartments(Long [] departmentIds);
	
	/**
	 * 根据传入的部门ID，获取部门
	 * 
	 * @param departmentId		部门ID
	 * @return Department		部门
	 */
	public Department loadDepartment(Long departmentId);
	
	/**
	 * 获取所有部门
	 * 
	 * @return	List	部门集合
	 */
	public List<Department> loadAllDepartments();
	
	/**
	 * 创建可供选择的部门集合
	 * @param name		第一条记录的名称，如 "所有" 或者 ""
	 * @return	List	可选择的部门集合
	 */
	public List createSelectDepartments(String name);
	
	List<ProductionLine> getUnJoinedProductionLines();
	/**
	 * 创建从采购合同的选择的部门
	 * @param name        第一条记录为从采购合同选择过来的部门
	 * @return
	 */
    public List createSelectPurchaseConDepartment(String name);  
    
	/**
	 * 部门失效
	 * @return
	 */
	@Transactional
	public abstract void disableAllDepartments(List<Department> departments);
	/**
	 * 部门失效
	 * @return
	 */
	@Transactional
	void enabledAllDepartments(Collection<Department> departments);
	
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
	public List<Department> filialeSelectDept(String filialeId);
	
	/**
	 * 根据分公司 级联 部门 
	 * @param filialeId
	 * @return
	 */
	
	public List<Department> loadDepartmentByFiliale(Long filialeId);
}
