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
package com.yongjun.tdms.presentation.webwork.action.runmaintenance.wash;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.RequestUtils;

import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.spring.controller.JasperReportsController;

import com.yongjun.tdms.service.base.org.DepartmentManager;


import com.yongjun.tdms.service.runmaintenance.wash.WashManager;

/**
 * <p>Title: EditDiscardAction
 * <p>Description: 清洗计划汇总打印控制类</p>
 * <p>Copyright: Copyright (c) 2001 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author lchen@yj-technology.com
 * @version $Id: ListAllWashPlanController.java 9353 2008-5-13 11:23:46Z lchen $
 */
public class ListAllWashPlanController extends JasperReportsController{
	private final WashManager washManager;
	private final DepartmentManager departmentManager;
    private Department dept;
	String deptName;
	List result;
	
	public ListAllWashPlanController (WashManager washManager,
			DepartmentManager departmentManager){
		this.washManager = washManager;
		this.departmentManager = departmentManager;
	}
	/**
	 * 通过页面获取所提交的查询字段，保存到数组queryInfo中，在Dao中拼SQL语句，进行复合查询。
	 * @return Map类型的model对象
	 */

	@Override
	protected Map getModel(HttpServletRequest httpservletrequest) throws Exception {
	
			
			String department_id = "";
			String dept_name = "";
			if (!RequestUtils.getStringParameter(httpservletrequest,
					"department.id", StringUtils.EMPTY).trim().equals("-1")) {
				department_id = RequestUtils.getStringParameter(httpservletrequest,
						"department.id", StringUtils.EMPTY).trim();
			}
			if (!department_id.equals("")) {
				dept = departmentManager.loadDepartment(Long.valueOf(department_id));
				dept_name = dept.getName();
			}
			String[] queryInfo = new String[] {
					RequestUtils.getStringParameter(httpservletrequest,
					"palnBeginDate_start", StringUtils.EMPTY).trim(), 
					RequestUtils.getStringParameter(httpservletrequest,
					"palnBeginDate_end", StringUtils.EMPTY).trim(),
					dept_name };
			result = washManager.loadAllWashPlanView(queryInfo);
			Map model = new HashMap();
			model.put("reportData", result);
			return model;
	}
	
}
