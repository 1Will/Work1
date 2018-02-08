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

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.SysModel;
import com.yongjun.tdms.model.base.codevalue.CodeConstants;
import com.yongjun.tdms.model.base.codevalue.CodeValue;
import com.yongjun.tdms.model.runmaintenance.fault.FaultBill;
import com.yongjun.tdms.model.runmaintenance.fault.FaultRepair;
import com.yongjun.tdms.model.year.budget.BudgetDetail;
import com.yongjun.tdms.service.base.codevalue.CodeValueManager;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.runmaintenance.fault.FaultBillManager;
import com.yongjun.tdms.service.runmaintenance.fault.FaultRepairManager;
import com.yongjun.tdms.service.year.budget.BudgetDetailManager;
/**
 * <p>Title: EditFaultRepaitAction
 * <p>Description: 故障维修页面访问控制类</p>
 * <p>Copyright: Copyright (c) 2001 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id: $
 */
public class EditFaultRepairAction extends PrepareAction {
	private static final long serialVersionUID = -4664815192803798940L;
	
	private final FaultRepairManager faultRepairManager;
	private final DepartmentManager departmentManager;
	private final UserManager userManager;
	private final CodeValueManager codeValueManager;
	private final FaultBillManager faultBillManager;
	
	private BudgetDetailManager budgetDetailManager;
	
	private FaultRepair faultRepair;
	private FaultBill faultBill;
	private String planProcFlag; 
	private String toolingDevFlag;
	private BudgetDetail budgetDetail;              //预算明细
	
	public EditFaultRepairAction(FaultRepairManager faultRepairManager,
			DepartmentManager departmentManager,
			UserManager userManager,
			CodeValueManager codeValueManager,
			FaultBillManager faultBillManager) {
		this.faultRepairManager = faultRepairManager;
		this.departmentManager = departmentManager;
		this.userManager = userManager;
		this.codeValueManager = codeValueManager;
		this.faultBillManager = faultBillManager;
	}
	
	/**
	 * 准备页面或save()方法所需的数据
	 */
	public void prepare() throws Exception {
		if (this.hasId("faultBill.id")) {             //判断请求中是否有"faultBill.id",如有,则根据此ID,获取故障单对象
			this.faultBill = this.faultBillManager.loadFaultBill(this.getId("faultBill.id"));
			for (FaultRepair repair : faultBill.getFaultRepair()) {      //如果该故障单已有维修报告,则获取该维修报告
				this.faultRepair = repair;
			}
		}
		
		//判断请求中是否传有"faultRepair.id",有,则根据改值从数据库查出故障维修对象,否则创建新故障维修对象
		if (null == faultRepair) {
			if (this.hasId("faultRepair.id")) { 
				this.faultRepair = this.faultRepairManager.loadFaultRepair(this.getId("faultRepair.id"));
			} else {
				this.faultRepair = new FaultRepair();
			}
		}
		if (!this.faultRepair.isNew()) {
			if (request.getParameter("budgetDetail.id")==null) {
				this.budgetDetail = this.faultRepair.getBudgetDetail();
			}
		}
		if (this.hasId("planProcFlag")) {
			this.planProcFlag = request.getParameter("planProcFlag");
		}
		if (this.hasId("toolingDevFlag")) {
			this.toolingDevFlag = request.getParameter("toolingDevFlag");
		}
		System.out.println("=========toolDevFlag"+toolingDevFlag);
	}
	
	public String save() {
		boolean isNew = this.faultRepair.isNew();
		
		if (!StringUtils.isEmpty(request.getParameter("department.id"))) {     //获取责任单位
			faultRepair.setDepartment(this.departmentManager.loadDepartment(this.getId("department.id")));
		}
		if (!StringUtils.isEmpty(request.getParameter("externalHelpFlag"))) {  //获取外协
			faultRepair.setExternalHelpFlag(true);
		} else {
			faultRepair.setExternalHelpFlag(false);
		}
		if (!StringUtils.isEmpty(request.getParameter("dutyPeople.id"))) {     //获取责任人
			faultRepair.setDutyPeople(this.userManager.loadUser(this.getId("dutyPeople.id")));
		}
		if (!StringUtils.isEmpty(request.getParameter("receiver.id"))) {      //获取验收人
			faultRepair.setReceiver(this.userManager.loadUser(this.getId("receiver.id")));
		}
		if (!StringUtils.isEmpty(request.getParameter("repairLevel.id"))) {   //获取维修等级
			faultRepair.setRepairLevel(this.codeValueManager.loadCodeValue(this.getId("repairLevel.id")));
		}
		if (!StringUtils.isEmpty(request.getParameter("sourceType"))) {   //获取费用来源
			faultRepair.setSourceType(this.codeValueManager.loadCodeTypeByRealCode(request.getParameter("sourceType").trim()));
		}else{
			faultRepair.setSourceType(null);
		}
		//移除该预算编号关联的已发生费用
		if (null != faultRepair.getBudgetNo()) {
			this.budgetDetailManager.removeOccurFeeFromBudgetDetailByBudgetNo(faultRepair.getBudgetNo(), faultRepair.getAllFee());
		}
		//设置关联的预算明细
		if (!StringUtils.isEmpty(request.getParameter("budgetDetail.id"))) {        //如果为预算内，则置关联的预算明细核预算编号
			BudgetDetail budgetDetail = this.budgetDetailManager.loadBudgetDetail(this.getId("budgetDetail.id"));
			faultRepair.setBudgetDetail(budgetDetail);                    //设置关联的预算明细
			faultRepair.setBudgetNo(budgetDetail.getBudgetNo());          //设置预算编号
		} else {                                                                   //如果为预算内，则置空预算明细和预算编号
			faultRepair.setBudgetDetail(null);
			faultRepair.setBudgetNo(null);
		}
		faultRepair.setFaultBill(faultBill);
		this.faultRepairManager.storeFaultRepair(faultRepair);
		
		if (isNew) {
			if(toolingDevFlag.equals(SysModel.DEVICE)){
				this.addActionMessage(this.getText("faultRepair.add.success", Arrays
						.asList(new Object[] { faultBill.getToolingDev().getName() })));
				return NEW;
			}else{
				if(faultBill.getToolingDev()!=null){
					if(faultBill.getToolingDev().getName()!=null){
						this.addActionMessage(this.getText("faultRepair.add.success", Arrays
								.asList(new Object[] { faultBill.getToolingDev().getName() })));
						return NEW;	
					}else{
						this.addActionMessage(this.getText("faultRepair.faultBill.getToolingDev.is.null.add.success"
								));
						return NEW;	
					}
				}else{
					this.addActionMessage(this.getText("faultRepair.faultBill.getToolingDev.getName.is.null.add.success"
					));
			      return NEW;	
				}
			
			}
			
		} else {
			if(toolingDevFlag.equals(SysModel.DEVICE)){
				this.addActionMessage(this.getText("faultRepair.edit.success", Arrays
						.asList(new Object[] { faultBill.getToolingDev().getName() })));
				return SUCCESS;
			}else{
				if(faultBill.getToolingDev()!=null){
					if(faultBill.getToolingDev().getName()!=null){
						this.addActionMessage(this.getText("faultRepair.edit.success", Arrays
								.asList(new Object[] { faultBill.getToolingDev().getName() })));
						return NEW;	
					}else{
					  this.addActionMessage(this.getText("faultRepair.faultBill.getToolingDev.is.null.edit.success"
							));
					return NEW;	
					}
				}else{
					  this.addActionMessage(this.getText("faultRepair.faultBill.getToolingDev.getName.is.null.edit.success"
						));
				return NEW;	
				}
				
			}
		}
		
	}
	
    /**
     * 获取所有部门集合
     * @return List  部门集合
     */
	public List getDepartments() {
		return departmentManager.createSelectDepartments(this
				.getText(""));
	}
	
    /**
	 * 获得费用来源
	 * @return
	 */
	
	public List getFeeSourceTypes() {
		return this.codeValueManager.LoadAllValuesByCode(CodeConstants.FEE_SOURCE_TYPE);
		
	}
	
	/**
	 * 获取费用来源类型
	 * @return
	 */
	public String getSourceType() {
		return this.faultRepair.getSourceType().getRealCode();
	}
	
	/**
	 * 获取所有维修级别的集合
	 * @return List 维修级别集合
	 */
	public List getRepairLevels() {
		return this.codeValueManager.LoadAllValuesByCode(CodeConstants.REPAIR_LEVEL);
	}
	
	public FaultRepair getFaultRepair() {
		return faultRepair;
	}
	
	public void setFaultRepair(FaultRepair faultRepair) {
		this.faultRepair = faultRepair;
	}

	public String getPlanProcFlag() {
		return planProcFlag;
	}

	public void setPlanProcFlag(String planProcFlag) {
		this.planProcFlag = planProcFlag;
	}

	public FaultBill getFaultBill() {
		return faultBill;
	}

	public void setFaultBill(FaultBill faultBill) {
		this.faultBill = faultBill;
	}

	public String getToolingDevFlag() {
		return toolingDevFlag;
	}

	public void setToolingDevFlag(String toolingDevFlag) {
		this.toolingDevFlag = toolingDevFlag;
	}

	public BudgetDetailManager getBudgetDetailManager() {
		return budgetDetailManager;
	}

	public void setBudgetDetailManager(BudgetDetailManager budgetDetailManager) {
		this.budgetDetailManager = budgetDetailManager;
	}

	public BudgetDetail getBudgetDetail() {
		return budgetDetail;
	}

	public void setBudgetDetail(BudgetDetail budgetDetail) {
		this.budgetDetail = budgetDetail;
	}

}
