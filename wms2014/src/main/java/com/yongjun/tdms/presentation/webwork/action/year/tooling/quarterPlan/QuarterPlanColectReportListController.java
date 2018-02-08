package com.yongjun.tdms.presentation.webwork.action.year.tooling.quarterPlan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.RequestUtils;

import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.spring.controller.JasperReportsController;

import com.yongjun.tdms.model.year.tooling.QuarterPlanDetailView;
import com.yongjun.tdms.model.year.tooling.quarterPlan.QuarterType;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.year.tooling.quarterPlan.QuarterPlanManager;

public class QuarterPlanColectReportListController extends JasperReportsController{
	 private QuarterPlanManager quarterPlanManager;
	 private DepartmentManager departmentManager;
	 private List<QuarterPlanDetailView> details = new ArrayList();
	 String year ,deptName;
	 private Department depart;
	 private QuarterType qarterType;
	public QuarterPlanColectReportListController(QuarterPlanManager quarterPlanManager,DepartmentManager departmentManager){
		this.quarterPlanManager=quarterPlanManager;
		this.departmentManager=departmentManager;
	}
	@Override
	protected Map getModel(HttpServletRequest httpservletrequest) throws Exception {
		Map model = new HashMap();
		String department_id ="" ;
		String depart_Name="";
		String qarterType_id="";
		if (RequestUtils.getStringParameter(httpservletrequest,
				"department.id", StringUtils.EMPTY).trim().equals("-1") == false) {
		
			department_id = RequestUtils.getStringParameter(httpservletrequest,
					"department.id", StringUtils.EMPTY).trim();
			
		}
		
		if (RequestUtils.getStringParameter(httpservletrequest,
				"qarterType.id", StringUtils.EMPTY).trim().equals("-1") == false) {
			qarterType_id = RequestUtils.getStringParameter(httpservletrequest,
					"qarterType.id", StringUtils.EMPTY).trim();
			
		}
		if (!department_id.equals("")) {
			depart = departmentManager
					.loadDepartment(Long.valueOf(department_id));
			depart_Name = depart.getName();
		}
		String[] queryInfo = new String[] {
				RequestUtils.getStringParameter(httpservletrequest,
						"year", StringUtils.EMPTY).trim(), depart_Name,qarterType_id
						};
		details=quarterPlanManager.loadQuarterPlanDetailView(queryInfo);
		model.put("reportData", details);
		return model;
	}
}
