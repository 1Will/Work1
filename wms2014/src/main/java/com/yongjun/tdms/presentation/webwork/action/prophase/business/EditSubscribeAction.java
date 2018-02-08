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
package com.yongjun.tdms.presentation.webwork.action.prophase.business;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.model.LabelValue;
import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.SysModel;
import com.yongjun.tdms.model.base.codevalue.CodeConstants;
import com.yongjun.tdms.model.base.codevalue.CodeValue;

import com.yongjun.tdms.model.prophase.business.Subscribe;
import com.yongjun.tdms.model.prophase.business.SubscribeStatus;
import com.yongjun.tdms.model.prophase.business.SubscribeTypeStatus;
 
import com.yongjun.tdms.model.year.budget.BudgetDetail;
import com.yongjun.tdms.service.base.codevalue.CodeValueManager;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.prophase.business.SubscribeManager;
import com.yongjun.tdms.service.year.budget.BudgetDetailManager;

/**
 * @author qs
 * @version $Id: EditSubscribeAction.java 11279 2008-03-12 01:12:13Z mwei $
 */
@SuppressWarnings("serial")
public class EditSubscribeAction extends PrepareAction{
	private final SubscribeManager subscribeManager;
	private final UserManager userManager;
	private final DepartmentManager departmentManager;
	private final CodeValueManager codeValueManager;
	private final BudgetDetailManager budgetDetailManager;
	private User buyPerople;		//申购人、采购人
	private Subscribe subscribe;    //采购单对象
	private CodeValue feeSource;      
	private BudgetDetail budgetDetail;              //预算明细
	private String toolingDevFlag; //标示[工装][设备]
	private Department department; //部门对象 
	private String report;//是从报表管理 进入标识
	
	public EditSubscribeAction(SubscribeManager subscribeManager,
			DepartmentManager departmentManager,UserManager userManager,
			CodeValueManager codeValueManager,BudgetDetailManager budgetDetailManager) {
		this.subscribeManager = subscribeManager;
		this.departmentManager = departmentManager;
		this.userManager=userManager;
		this.codeValueManager=codeValueManager;
		this.budgetDetailManager = budgetDetailManager;
	}
	
	
	public void prepare() throws Exception {
		if (null == this.subscribe) {   //根据页面传来的值 获得申购单对象
			if (this.hasId("subscribe.id")) {
				this.subscribe = this.subscribeManager.loadSubscribe(getId("subscribe.id"));
				if(subscribe.getStatus()==null){
					subscribe.setStatus(SubscribeStatus.NEWPURCHASE);
				}
				if (request.getParameter("budgetDetail.id")==null) {
					this.budgetDetail = this.subscribe.getBudgetDetail();
				}
			} else {
				this.subscribe = new Subscribe();
				subscribe.setBuyingPerson(userManager.getUser());
				//给申购单初始化状态
				subscribe.setStatus(SubscribeStatus.NEWPURCHASE);
			}
			
		}
		if(this.hasId("toolingDevFlag")){
			this.toolingDevFlag=request.getParameter("toolingDevFlag");
		}
		report = this.request.getParameter("report");
	}
	
	
	public String save() {
		boolean isNew = this.subscribe.isNew();
		
		if (!StringUtils.isEmpty(request.getParameter("sourceType"))) {   //获取费用来源
			subscribe.setFeeSource(this.codeValueManager.loadCodeTypeByRealCode(request.getParameter("sourceType").trim()));
		}else{
			subscribe.setFeeSource(null);
		}
		
		if (!StringUtils.isEmpty(request.getParameter("detailKind.id"))) {//获得单据类型
			subscribe.setDetailKind(this.codeValueManager.loadCodeValue(this
					.getId("detailKind.id")));
		}
		if(!StringUtils.isEmpty(request.getParameter("status"))){		 //获得申购单的状态
			if (request.getParameter("status").equals("NEWPURCHASE")) {
				subscribe.setStatus(SubscribeStatus.NEWPURCHASE);
			}
			if(request.getParameter("status").equals("PURCHASING")){
				subscribe.setStatus(SubscribeStatus.PURCHASING);
			}
			if(request.getParameter("status").equals("PURCHASEED")){
				subscribe.setStatus(SubscribeStatus.PURCHASEED);
			}
		}
		
		if(!StringUtils.isEmpty(request.getParameter("typeStatus"))){    //获得申购单的类型
			if(request.getParameter("typeStatus").equals("LOWLOSS")){	 //低耗
				subscribe.setTypeStatus(SubscribeTypeStatus.LOWLOSS);	 
			}
			if(request.getParameter("typeStatus").equals("SPARE")){		 //备件
				subscribe.setTypeStatus(SubscribeTypeStatus.SPARE);
			}
			if(request.getParameter("typeStatus").equals("TOOLING")){	 //工装
				subscribe.setTypeStatus(SubscribeTypeStatus.TOOLING);
			}
		}
		//移除该预算编号关联的申购总费用
		if (null != subscribe.getBudgetNo()) {
			this.budgetDetailManager.removeParchaseFeeFromBudgetDetail(subscribe.getBudgetNo(), subscribe.getTotalPrice());
		}
		//设置关联的预算明细
		if (!StringUtils.isEmpty(request.getParameter("budgetDetail.id"))) {        //如果为预算内，则置关联的预算明细核预算编号
			BudgetDetail budgetDetail = this.budgetDetailManager.loadBudgetDetail(this.getId("budgetDetail.id"));
			subscribe.setBudgetDetail(budgetDetail);                    //设置关联的预算明细
			subscribe.setBudgetNo(budgetDetail.getBudgetNo());          //设置预算编号
		} else {                                                                   //如果为预算内，则置空预算明细和预算编号
			subscribe.setBudgetDetail(null);
			subscribe.setBudgetNo(null);
		}
		if (!StringUtils.isEmpty(request.getParameter("department.id"))) {//获得部门 
			subscribe.setDepartment(this.departmentManager.loadDepartment(this.getId("department.id")));
		}
		if (!StringUtils.isEmpty(request.getParameter("buyingPerson.id"))) { //获得采购人
			this.buyPerople = userManager.loadUser(this.getId("buyingPerson.id"));
			subscribe.setBuyingPerson(buyPerople);
		}
		if(toolingDevFlag.equals(SysModel.DEVICE.toString())){
			subscribe.setToolingDevFlag(SysModel.DEVICE);
		}else{
			subscribe.setToolingDevFlag(SysModel.TOOLING);
		}
	    
		if (isNew) {
			subscribe.setBillNo(subscribeManager.generateSubscribeBillNo());
			subscribeManager.storeSubscribe(subscribe);
			 if(toolingDevFlag.equals(SysModel.DEVICE.toString())){//如果是设备  提示申购单已成功添加
				 addActionMessage(getText("subscribe.add.success", Arrays
							.asList(new Object[] { subscribe.getBillNo() })));
					return NEW;
			 }else{
				 addActionMessage(getText("toolingPurhaseBill.add.success", Arrays//如果是工装  提示采购单已成功添加
							.asList(new Object[] { subscribe.getBillNo() })));
					return NEW;
			 }
			
		} else {
			subscribeManager.storeSubscribe(subscribe);
			if(toolingDevFlag.equals(SysModel.DEVICE.toString())){//如果是设备  提示申购单已成功修改
				addActionMessage(getText("subscribe.edit.success", Arrays
						.asList(new Object[] { subscribe.getBillNo() })));
				return SUCCESS;
			}else{
				addActionMessage(getText("toolingPurhaseBill.edit.success", Arrays   //如果是工装  提示采购单已成功修改
						.asList(new Object[] { subscribe.getBillNo() })));
				return SUCCESS;
			}
			
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
			//显示附属部门
			Set<Department> depts =userManager.getUser().getDepartments();
			if(depts!=null){
				list.addAll(depts);
			}
			return list;
		}
		return this.departmentManager.loadAllDepartments();
	}
	
	/**
	 * 类型选择
	 * @return
	 */
	public List<LabelValue> getResults() {  
		LabelValue[] arrays = this.wrapEnum(SubscribeTypeStatus.class);
		List<LabelValue> tmp = new ArrayList<LabelValue>();
		for (int i = 0; i < arrays.length; i++) {
			tmp.add(arrays[i]);
		}
		tmp.remove(arrays[3]);
//		if(toolingDevFlag.equals("DEVICE")){
//			tmp.remove(arrays[1]);
//		}else{
//			tmp.remove(arrays[2]);
//		}
		return tmp;
	}
	/**
	 * 
	 * 获取当前所有登陆用户
	 * @return
	 */
    public User getLoginUser(){
    	return userManager.getUser();
    }
    /**
	 * 获得费用来源
	 * @return
	 */
	
	public List getFeeSourceTypes() {
		return this.codeValueManager.LoadAllValuesByCode(CodeConstants.FEE_SOURCE_TYPE);
		
	}
	
	 /**
	 * 获得单据类型
	 * @return
	 */
	
	public List getAllDetailKind() {
		return this.codeValueManager.LoadAllValuesByCode(CodeConstants.DETAIL_KIND);
		
	}
	
	/**
	 * 类型选择
	 * @return
	 */
	public List<LabelValue> getStatus() {  
		LabelValue[] arrays = this.wrapEnum(SubscribeStatus.class);
		List<LabelValue> tmp = new ArrayList<LabelValue>();
		for (int i = 0; i < arrays.length; i++) {
			tmp.add(arrays[i]);
		}
		return tmp;
	}
	
	/**
	 * 获取费用来源类型
	 * @return
	 */
	public String getSourceType() {
		if (null!=this.subscribe.getFeeSource()){
			return this.subscribe.getFeeSource().getRealCode();
		} else {
			return null;
		}
		
	}
	public Subscribe getSubscribe() {
		return this.subscribe;
	}
	
	public void setSubscribe(Subscribe subscribe) {
		this.subscribe = subscribe;
	}


	public String getToolingDevFlag() {
		return toolingDevFlag;
	}

	public void setToolingDevFlag(String toolingDevFlag) {
		this.toolingDevFlag = toolingDevFlag;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}
	public void setFeeSource(CodeValue feeSource) {
		this.feeSource = feeSource;
	}
	public CodeValue getFeeSource() {
		return feeSource;
	}
	public BudgetDetail getBudgetDetail() {
		return budgetDetail;
	}
	public void setBudgetDetail(BudgetDetail budgetDetail) {
		this.budgetDetail = budgetDetail;
	}


	public String getReport() {
		return report;
	}


	public void setReport(String report) {
		this.report = report;
	}
}
