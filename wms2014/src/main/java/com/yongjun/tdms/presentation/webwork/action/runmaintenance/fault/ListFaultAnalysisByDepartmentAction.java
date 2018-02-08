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
package com.yongjun.tdms.presentation.webwork.action.runmaintenance.fault;

import java.util.List;

import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.service.base.org.DepartmentManager;


/**
 * <p>Title: ListFaultAnalysisByDepartmentAction
 * <p>Description: 部门故障统计访问控制类</p>
 * <p>Copyright: Copyright (c) 2001 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wliu@yj-technology.com
 * @version $Id: ListDeviceDepreciationDetail.java 11417 2009-09-10 13:42:51Z wliu $
 */
public class ListFaultAnalysisByDepartmentAction
extends ValueListAction{

	private static final long serialVersionUID = 1L;
	//部门业务逻辑层
	private final DepartmentManager departmentManager;
	
	/**
	 * 部门故障统计访问控制类构造函数
	 * 注入部门业务逻辑jiekou
	 * @param departmentManager 
	 */
	public ListFaultAnalysisByDepartmentAction(DepartmentManager departmentManager) {
		
		this.departmentManager = departmentManager;
	}
	
	/**
	 * 默认调用方法
	 * 若页面请求中有参数(存放部门标识和月份条件),将部门实体类和月份赋值
	 */
	@Override
	public void prepare() throws Exception {

		this.setFirst(true);
	}
	
	/**
	 * 调用valueList配置文件关联的hql语句
	 */
	@Override
	protected String getAdapterName() {
		
		return "FaultAnalysisByDepartmentHQL";
	}

	/**
	 * 获得所有部门信息，并添加首行选择所有
	 * @return
	 */
	public List<Department> getAllDepartments(){
		
		List<Department> list = departmentManager.loadAllDepartments();
		Department d = new Department();
		d.setName(this.getText("select.option.all"));
		d.setId(-1L);
		list.add(0, d);
		return list;
	}
	
}
