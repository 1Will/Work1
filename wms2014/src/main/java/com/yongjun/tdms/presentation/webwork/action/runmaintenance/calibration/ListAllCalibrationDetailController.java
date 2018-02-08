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
package com.yongjun.tdms.presentation.webwork.action.runmaintenance.calibration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.RequestUtils;

import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.spring.controller.JasperReportsController;
import com.yongjun.tdms.model.runmaintenance.checkpoint.CheckPointReportView;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.runmaintenance.calibration.CalibrationManager;
import com.yongjun.tdms.service.runmaintenance.checkpoint.CheckPointReportManager;
import com.yongjun.tdms.service.runmaintenance.maintenance.MaintenanceManager;

/**
 * <p>Title: EditDiscardAction
 * <p>Description: 标定计划打印汇总打印控制类</p>
 * <p>Copyright: Copyright (c) 2001 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author lchen@yj-technology.com
 * @version $Id: ListAllCalibrationDetailController.java 9353 2008-4-14 11:46:46Z lchen $
 */
public class ListAllCalibrationDetailController extends JasperReportsController{
	private final CalibrationManager calibrationManager;
	private final DepartmentManager departmentManager;
    private Department dept;
	String month,deptName;
	List result;
	
	public ListAllCalibrationDetailController (CalibrationManager calibrationManager,
			DepartmentManager departmentManager){
		this.calibrationManager = calibrationManager;
		this.departmentManager = departmentManager;
	}
	/**
	 * 通过页面获取所提交的查询字段，保存到数组queryInfo中，在Dao中拼SQL语句，进行复合查询。
	 * @return Map类型的model对象
	 */
	protected Map getModel(HttpServletRequest httpServletRequest)
	throws Exception {
		String department_id = "";
		String dept_name = "";
		if (!RequestUtils.getStringParameter(httpServletRequest,
				"department.id", StringUtils.EMPTY).trim().equals("-1")) {
			department_id = RequestUtils.getStringParameter(httpServletRequest,
					"department.id", StringUtils.EMPTY).trim();
		}
		if (!department_id.equals("")) {
			dept = departmentManager
					.loadDepartment(Long.valueOf(department_id));
		}
		String[] queryInfo = new String[] {
				RequestUtils.getStringParameter(httpServletRequest,
						"month", StringUtils.EMPTY).trim(), dept_name };
		result = calibrationManager.loadAllCalibrationDetailView(queryInfo);
		Map model = new HashMap();
		model.put("reportData", result);
		return model;
	}
}
