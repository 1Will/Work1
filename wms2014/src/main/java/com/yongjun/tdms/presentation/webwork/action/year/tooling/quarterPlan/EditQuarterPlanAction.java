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
package com.yongjun.tdms.presentation.webwork.action.year.tooling.quarterPlan;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.model.LabelValue;
import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.year.tooling.quarterPlan.QuarterPlan;
import com.yongjun.tdms.model.year.tooling.quarterPlan.QuarterType;
import com.yongjun.tdms.model.year.tooling.yearPlan.YearPlan;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.year.tooling.quarterPlan.QuarterPlanManager;

/**
 * 
 * <p>Title: EditQuarterPlanAction
 * <p>Description: 季度计划页面访问控制类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id:$
 */
public class EditQuarterPlanAction extends PrepareAction {

	private static final long serialVersionUID = -4784637570300366205L;
	
	private final QuarterPlanManager quarterPlanManager;
	private final DepartmentManager departmentManager;
	private final UserManager userManager;
	
	private QuarterPlan quarterPlan;
	
	public EditQuarterPlanAction(QuarterPlanManager quarterPlanManager,
			DepartmentManager departmentManager,
			UserManager userManager) {
		this.quarterPlanManager = quarterPlanManager;
		this.departmentManager = departmentManager;
		this.userManager = userManager;
	}
	
	/**
	 * 为执行save()方法准备数据
	 */
	public void prepare() throws Exception {
		if (null == quarterPlan) {
			if (this.hasId("quarterPlan.id")) {           //如果请求中包含"quarterPlan.id",则根据"quarterPlan.id"获取季度计划对象
				this.quarterPlan = this.quarterPlanManager.loadQuarterPlan(this.getId("quarterPlan.id"));
			} else {                                   //如果请求中不包含"quarterPlan.id",则创建季度计划对象
				this.quarterPlan = new QuarterPlan();
			}
		}
		
	}
	
	/**
	 * 当页面点击保存按钮,保存季度计划对象的信息
	 * @return
	 */
	public String save() {
		boolean isNew = this.quarterPlan.isNew();
		
		if (!StringUtils.isEmpty(request.getParameter("department.id"))) {          //设置季度计划中的部门
			//quarterPlan.setDepartment(this.departmentManager.loadDepartment(this.getId("department.id")));
		    Department dept=this.departmentManager.loadDepartment(this.getId("department.id"));
		    quarterPlan.setDepartment(dept);
		    quarterPlan.setDeptName(dept.getName());
		    
		}
		if (!StringUtils.isEmpty(request.getParameter("qarterType.id"))) {     //设置季度计划中的季度
			String quarterType = request.getParameter("qarterType.id");
			if (quarterType.equals(QuarterType.FIRST_QUARTER.toString())) {
				this.quarterPlan.setQarterType(QuarterType.FIRST_QUARTER);    //设置第一季度       
			} else if (quarterType.equals(QuarterType.SECOND_QUARTER.toString())) {
				this.quarterPlan.setQarterType(QuarterType.SECOND_QUARTER);   //设置第二季度
			} else if (quarterType.equals(QuarterType.THIRD_QUARTER.toString())) {
				this.quarterPlan.setQarterType(QuarterType.THIRD_QUARTER);    //设置第三季度
			} else {
				this.quarterPlan.setQarterType(QuarterType.FOURTH_QUARTER);    //设置第四季度
			}
		}
		if (!StringUtils.isEmpty(request.getParameter("planCreator.id"))) {        //设置季度计划中的编制人
			quarterPlan.setPlanCreator(this.userManager.loadUser(this.getId("planCreator.id")));
		}
		QuarterPlan quarterPlanByDepartANDYearANDQarterType =quarterPlanManager.loadQuarterPlanByDepartIdAndYearAndQuarterId(quarterPlan.getDepartment().getId(),quarterPlan.getYear(),quarterPlan.getQarterType().toString());
		  if(quarterPlanByDepartANDYearANDQarterType!=null && !quarterPlanByDepartANDYearANDQarterType.equals(quarterPlan)){
			  this.addActionMessage(this.getText("quarterPlan.add.error", Arrays
						.asList(new Object[] { quarterPlan.getName()})));
				return ERROR;
		  }
//		List quarterPlanByDepartANDYearANDQarterType =quarterPlanManager.loadQuarterPlanByDepartIdAndYearAndQuarterId(quarterPlan.getDepartment().getId(),quarterPlan.getYear(),quarterPlan.getQarterType().toString());
//		  if(quarterPlanByDepartANDYearANDQarterType.size()>1){
//			  this.addActionMessage(this.getText("quarterPlan.add.error", Arrays
//						.asList(new Object[] { quarterPlan.getName()})));
//				return ERROR;
//		  }
		try {
			this.quarterPlanManager.storeQuarterPlan(this.quarterPlan);	
		}catch (Exception e) {
			
			return SUCCESS;
		}
		
		if (isNew) {
//			List quarterPlanByDepartANDYearANDQarterType =quarterPlanManager.loadQuarterPlanByDepartIdAndYearAndQuarterId(quarterPlan.getDepartment().getId(),quarterPlan.getYear(),quarterPlan.getQarterType().toString());
//			  if(quarterPlanByDepartANDYearANDQarterType.size()>1){
//				  this.addActionMessage(this.getText("quarterPlan.add.error", Arrays
//							.asList(new Object[] { quarterPlan.getName()})));
//					return ERROR;
//			  }
			this.addActionMessage(this.getText("quarterPlan.add.success", Arrays
					.asList(new Object[] { quarterPlan.getName() })));
			return NEW;
		} else {
			List quarterPlanByDepartANDYearANDQarterTypeAndEdit =quarterPlanManager.loadQuarterPlanListByDepartIdAndYearAndQuarterId(quarterPlan.getDepartment().getId(),quarterPlan.getYear(),quarterPlan.getQarterType().toString());
			if(quarterPlanByDepartANDYearANDQarterTypeAndEdit.size()>1){
				 this.addActionMessage(this.getText("quarterPlan.edit.error", Arrays
							.asList(new Object[] { quarterPlan.getName()})));
					return ERROR;
			}
			this.addActionMessage(this.getText("quarterPlan.edit.success",
							Arrays.asList(new Object[] { quarterPlan.getName() })));
			return SUCCESS;
		} 
	}
	
   	/**
	 * 如果当前用户只能看本部门数据，则获取该部门数据，否则获取所有部门 
	 * @return List 部门集合
	 */
	 public List getDepartments() {
		if (!this.userManager.getUser().isViewAll()) {
			List<Department> list = new ArrayList<Department>();
			if (null == this.userManager.getUser().getDepartment()) {
				Department department = new Department();
				department.setId(Long.valueOf(-1L));
				department.setName("");
				list.add(department);
			} else {
				list.add(this.departmentManager.loadDepartment(this.userManager.getUser().getDepartment().getId()));
			}
			return list;
		}
		return this.departmentManager.loadAllDepartments();
	}
	 
	/**
	 * 获取当前登陆的用户
	 * 
	 * @return User 用户实体
	 */
	public User getLoginUser() {
		return this.userManager.getUser();
	}
	/**
	 * 获取所有的季度值集合
	 * @return List 季度值集合
	 */
	public List<LabelValue> getQarterTypes() {
		LabelValue [] arrays = this.wrapEnum(QuarterType.class);
		List<LabelValue> tmp = new ArrayList<LabelValue>();
		for (int i=0; i<arrays.length; i++) {
			tmp.add(arrays[i]);
		}
		return tmp;
	}
	public QuarterPlan getQuarterPlan() {
		return quarterPlan;
	}
	
	public void setQuarterPlan(QuarterPlan quarterPlan) {
		this.quarterPlan = quarterPlan;
	}

}
