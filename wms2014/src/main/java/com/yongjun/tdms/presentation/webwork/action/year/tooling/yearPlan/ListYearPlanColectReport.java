package com.yongjun.tdms.presentation.webwork.action.year.tooling.yearPlan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.RequestUtils;

import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.spring.controller.JasperReportsController;
import com.yongjun.tdms.model.year.tooling.YearPlanDetailView;
import com.yongjun.tdms.model.year.tooling.yearPlan.YearPlan;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.year.tooling.yearPlan.YearPlanManager;

public class ListYearPlanColectReport extends JasperReportsController{
	
    private List result; 
    private YearPlan yearPlan;
    private Department depart;
    private List<YearPlanDetailView> details = new ArrayList();
	private final YearPlanManager yearPlanManager;
	private final DepartmentManager departmentManager;
	String year ,deptName;
	
	public ListYearPlanColectReport(YearPlanManager yearPlanManager,DepartmentManager departmentManager){
		this.yearPlanManager=yearPlanManager;
		this.departmentManager=departmentManager;
	}
	@Override
	protected Map getModel(HttpServletRequest httpservletrequest) throws Exception {
		String department_id ="" ;
		String depart_Name="";
		if (RequestUtils.getStringParameter(httpservletrequest,
				"department.id", StringUtils.EMPTY).trim().equals("-1") == false) {
			department_id = RequestUtils.getStringParameter(httpservletrequest,
					"department.id", StringUtils.EMPTY).trim();
		}
		if (!department_id.equals("")) {
			depart = departmentManager
					.loadDepartment(Long.valueOf(department_id));
			depart_Name = depart.getName();
		}
		String[] queryInfo = new String[] {
				RequestUtils.getStringParameter(httpservletrequest,
						"year", StringUtils.EMPTY).trim(), depart_Name};
		
		result = yearPlanManager.loadYearPlanDetailView(queryInfo);
		Map model = new HashMap();
		model.put("reportData", result);
		return model;
		
	}

}
