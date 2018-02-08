package com.yongjun.tdms.presentation.webwork.action.year.device.purchasePlan;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.RequestUtils;

import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.spring.controller.JasperReportsController;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.runmaintenance.checkpoint.CheckPointReportManager;
import com.yongjun.tdms.service.year.device.purchasePlan.PurchasePlanManager;

public class ListAllPurchasePlanController extends JasperReportsController{

	private final PurchasePlanManager purchasePlanManager;
	private final DepartmentManager departmentManager;
	private Department dept;
	String year,deptName;
	List result;
	
	public ListAllPurchasePlanController (PurchasePlanManager purchasePlanManager,
			DepartmentManager departmentManager){
		this.purchasePlanManager = purchasePlanManager;
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
			dept_name = dept.getName();
		}
		String[] queryInfo = new String[] {
				RequestUtils.getStringParameter(httpServletRequest,
						"year", StringUtils.EMPTY).trim(), dept_name };
		result = purchasePlanManager.loadAllPurchasePlanView(queryInfo);
		Map model = new HashMap();
		model.put("reportData", result);
		return model;
	}
}
