package com.yongjun.tdms.presentation.webwork.action.prophase.business;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.yongjun.pluto.model.LabelValue;
import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.year.tooling.quarterPlan.QuarterPlan;
import com.yongjun.tdms.model.year.tooling.quarterPlan.QuarterPlanDetail;
import com.yongjun.tdms.model.year.tooling.quarterPlan.QuarterType;
import com.yongjun.tdms.model.year.tooling.yearPlan.YearPlanDetailCategory;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.year.tooling.quarterPlan.QuarterPlanDetailManager;
import com.yongjun.tdms.service.year.tooling.quarterPlan.QuarterPlanManager;

public class ListQuarterPlanchooseAction extends ValueListAction{

	private List<QuarterPlanDetail> quarterPlanDetails;
	private QuarterPlanDetail quarterPlanDetail;
	private static final long serialVersionUID = 1L;
	private Department department;
	private QuarterPlan quarterPlan;
	
	private final DepartmentManager departmentManager;
	private final QuarterPlanDetailManager  quarterPlanDetailManager;
	private final  QuarterPlanManager  quarterPlanManager;
	
	public ListQuarterPlanchooseAction(DepartmentManager departmentManager,QuarterPlanDetailManager  quarterPlanDetailManager
			,QuarterPlanManager  quarterPlanManager){
		this.departmentManager=departmentManager;
		this.quarterPlanDetailManager=quarterPlanDetailManager;
		this.quarterPlanManager=quarterPlanManager;
	}
	
	public void prepare() {
		if (null == this.quarterPlan) {
			if (this.hasId("quarterPlan.id")) {
				this.quarterPlan = this.quarterPlanManager.loadQuarterPlan(getId("quarterPlan.id"));
			} 
		}
		if (this.quarterPlanDetails == null && this.hasIds("quarterPlanDetails")) {
            this.quarterPlanDetails = this.quarterPlanDetailManager.loadAllQuarterPlanDetails(this.getIds("subscribeDtlIds"));
        }
	    
	}
	
	@Override
	protected String getAdapterName() {
		return "quarterPlanSelector";
	}
	
	public List getDepartments() {
		if (!this.userManager.getUser().isViewAll()) {           //如果用户只有看本部门的权限
			List<Department> list = new ArrayList<Department>();
			if (null == this.userManager.getUser().getDepartment()) {      //如果用户不属于任何部门,置部门ID为-2
				Department department = new Department();
				department.setId(Long.valueOf(-2L));
				department.setName("");
				list.add(department);
			} else {
				list.add(this.departmentManager.loadDepartment(this.userManager.getUser().getDepartment().getId()));
			}
			//显示附属部门
			Set<Department> depts =userManager.getUser().getDepartments();
			if(depts!=null){
				list.addAll(depts);
			}
			return list;
		}
		return departmentManager.createSelectDepartments(this
				.getText("select.option.all"));
	}
	
	/**
	 * 获取所有的季度值集合
	 * @return List 季度值集合
	 */
	public List<LabelValue> getQarterTypes() {
		LabelValue [] arrays = this.wrapEnum(QuarterType.class);
		LabelValue array = new LabelValue("-1",this.getText("select.option.all"));
		List<LabelValue> tmp = new ArrayList<LabelValue>();
		tmp.add(array);
		for (int i=0; i<arrays.length; i++) {
			tmp.add(arrays[i]);
		}
		return tmp;
	}
	
	/**detailTypes
	 * 获取季度计划类别
	 */
	public List<LabelValue> getQuarterDetailTypes() {
		LabelValue [] arrays = this.wrapEnum(YearPlanDetailCategory.class);
		LabelValue array = new LabelValue("-1",this.getText("select.option.all"));
		List<LabelValue> tmp = new ArrayList<LabelValue>();
		tmp.add(array);
		for (int i=0; i<arrays.length; i++) {
			tmp.add(arrays[i]);
		}
		return tmp;
	}
	/**
	 * 获取当前登陆的用户
	 * 
	 * @return User 用户实体
	 */
	public User getLoginUser() {
		return this.userManager.getUser();
	}	

	public QuarterPlanDetail getQuarterPlanDetail() {
		return quarterPlanDetail;
	}

	public void setQuarterPlanDetail(QuarterPlanDetail quarterPlanDetail) {
		this.quarterPlanDetail = quarterPlanDetail;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}

	public QuarterPlan getQuarterPlan() {
		return quarterPlan;
	}

	public void setQuarterPlan(QuarterPlan quarterPlan) {
		this.quarterPlan = quarterPlan;
	}

}
