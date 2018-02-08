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
package com.yongjun.tdms.presentation.webwork.action.runmaintenance.repair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.model.LabelValue;
import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.SysModel;
import com.yongjun.tdms.model.asset.device.DeviceCard;
import com.yongjun.tdms.model.base.codevalue.CodeConstants;
import com.yongjun.tdms.model.base.financeType.FeeSourceType;
import com.yongjun.tdms.model.runmaintenance.repair.PreRepairDetailResult;
import com.yongjun.tdms.model.runmaintenance.repair.PreRepairModel;
import com.yongjun.tdms.model.runmaintenance.repair.PreRepairPlan;
import com.yongjun.tdms.model.runmaintenance.repair.PreRepairPlanDetail;
import com.yongjun.tdms.model.year.budget.BudgetDetail;
import com.yongjun.tdms.service.asset.device.DeviceCardManager;
import com.yongjun.tdms.service.base.codevalue.CodeValueManager;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.runmaintenance.repair.PreRepairPlanDetailManager;
import com.yongjun.tdms.service.runmaintenance.repair.PreRepairPlanManager;
import com.yongjun.tdms.service.year.budget.BudgetDetailManager;
/**
 * <p>Title: EditPreRepairPlanDetailAction
 * <p>Description: 预防性维修计划明细页面访问控制类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id: EditPreRepairPlanDetailAction.java 10286 2008-01-09 08:21:35Z zbzhang $
 */
public class EditPreRepairPlanDetailAction extends PrepareAction {
	private static final long serialVersionUID = -6714661013891174791L;
	private final PreRepairPlanDetailManager preRepairPlanDetailManager;
	private final PreRepairPlanManager preRepairPlanManager;
	private final DeviceCardManager deviceCardManager;
	private final DepartmentManager departmentManager;
	private final UserManager userManager;
	private final CodeValueManager codeValueManager;
	private final BudgetDetailManager budgetDetailManager;
	
	private PreRepairPlanDetail preRepairPlanDetail;
	private PreRepairPlan preRepairPlan;             //预防性维修计划
	private DeviceCard asset;                        //设备|工装
	private BudgetDetail budgetDetail;              //预算明细
	private String planProcFlag;                     //标识为计划，还是实施
	private String toolingDevFlag;                   //标识为工装，还是设备
	
	public EditPreRepairPlanDetailAction(PreRepairPlanDetailManager preRepairPlanDetailManager,
			PreRepairPlanManager preRepairPlanManager,
			DeviceCardManager deviceCardManager,
			DepartmentManager departmentManager,
			UserManager userManager,
			CodeValueManager codeValueManager,
			BudgetDetailManager budgetDetailManager) {
		this.preRepairPlanDetailManager = preRepairPlanDetailManager;
		this.preRepairPlanManager = preRepairPlanManager;
		this.deviceCardManager = deviceCardManager;
		this.departmentManager = departmentManager;
		this.userManager = userManager;
		this.codeValueManager = codeValueManager;
		this.budgetDetailManager = budgetDetailManager;
	}
	
	/**
	 * 根据页面传入的预防性维修计划明细ID,获取预防性维修计划明细;
	 * 根据页面传入的设备ID,获取设备
	 * 根据页面传入的预防性维修计划ID,获取预防性维修计划
	 */
	public void prepare() throws Exception {
		if (null == preRepairPlanDetail) {
			if (this.hasId("preRepairPlanDetail.id")) {
				this.preRepairPlanDetail = this.preRepairPlanDetailManager.loadPreRepairPlanDetail(this.getId("preRepairPlanDetail.id"));
				if (!this.hasId("device.id") || !this.hasId("tooling.id")) {
			    	this.asset = this.preRepairPlanDetail.getAsset();
			    }
				if (request.getParameter("budgetDetail.id")==null) {
					this.budgetDetail = this.preRepairPlanDetail.getBudgetDetail();
				}
				this.preRepairPlan = this.preRepairPlanDetail.getPlan();
			} else {
				this.preRepairPlanDetail = new PreRepairPlanDetail();
			}
		}
		if (this.hasId("preRepairPlan.id")) {
			this.preRepairPlan = this.preRepairPlanManager.loadPreRepairPlan(this.getId("preRepairPlan.id"));
		}
		if (this.hasId("planProcFlag")) {
			this.planProcFlag = request.getParameter("planProcFlag");
		}
		if (this.hasId("toolingDevFlag")) {
			this.toolingDevFlag = request.getParameter("toolingDevFlag");
		}
	}
	
	/**
	 *  保存预防性维修计划明细 
	 * @return SUCCESS或NEW
	 */
	public String save() {
		//保存预防性维修实施
		if (this.planProcFlag.equals(PreRepairModel.PROC.toString())) {
			if (!StringUtils.isEmpty(request.getParameter("procExecPeople.id"))) {
				preRepairPlanDetail.setProcExecPeople(this.userManager.loadUser(this.getId("procExecPeople.id")));
			}
			if (!StringUtils.isEmpty(request.getParameter("procResult"))) {
				if (request.getParameter("procResult").equals(PreRepairDetailResult.UNFINISHED.toString())) {
					preRepairPlanDetail.setProcResult(PreRepairDetailResult.UNFINISHED);
				} else if (request.getParameter("procResult").equals(PreRepairDetailResult.FINISHED.toString())) {
					preRepairPlanDetail.setProcResult(PreRepairDetailResult.FINISHED);
				} else if (request.getParameter("procResult").equals(PreRepairDetailResult.CANCEL.toString())) {
					preRepairPlanDetail.setProcResult(PreRepairDetailResult.CANCEL);
				} else {
					preRepairPlanDetail.setProcResult(PreRepairDetailResult.SHIFT);
				}
			}
			if (!StringUtils.isEmpty(request.getParameter("procCheckPeople.id"))) {
				preRepairPlanDetail.setProcCheckPeople(this.userManager.loadUser(this.getId("procCheckPeople.id")));
			}
			//移除该预算编号关联的年度预算已发生费用
			if (null != preRepairPlanDetail.getBudgetNo()) {
				this.budgetDetailManager.removeOccurFeeFromBudgetDetailByBudgetNo(preRepairPlanDetail.getBudgetNo(), preRepairPlanDetail.getProcAllFee());
			}
			
		    this.preRepairPlanDetailManager.storePreRepairProcDetail(preRepairPlanDetail);
			
		    if (preRepairPlanDetail.getAsset()!=null) {
				this.addActionMessage(this.getText("preRepairProcDetail.edit.success", Arrays
						.asList(new Object[] { preRepairPlanDetail.getAsset().getName() })));
			}else {
				this.addActionMessage(this.getText("preRepairProcDetail.edit.success", Arrays
						.asList(new Object[] { preRepairPlanDetail.getToolingName()})));
			}
			return SUCCESS;
		}
		//设置关联的预防维修计划
		preRepairPlanDetail.setPlan(preRepairPlan);
		if (this.toolingDevFlag.equals(SysModel.DEVICE.toString())) {
			if (!StringUtils.isEmpty(request.getParameter("device.id"))) {
				preRepairPlanDetail.setAsset(this.deviceCardManager.loadDevice(this.getId("device.id")));
			}
		} else {
			if (!StringUtils.isEmpty(request.getParameter("tooling.id"))) {
				preRepairPlanDetail.setAsset(this.deviceCardManager.loadDevice(this.getId("tooling.id")));
			}
		}
		if (!StringUtils.isEmpty(request.getParameter("department.id"))) {
			Department dept = this.departmentManager.loadDepartment(this.getId("department.id"));
			preRepairPlanDetail.setDepartment(dept);
			preRepairPlanDetail.setDeptName(dept.getName());
		}
		if (!StringUtils.isEmpty(request.getParameter("externalHelpFlag"))) {
			preRepairPlanDetail.setExternalHelpFlag(Boolean.valueOf(request.getParameter("externalHelpFlag")).booleanValue());
		} else {
			preRepairPlanDetail.setExternalHelpFlag(false);
		}
		if (!StringUtils.isEmpty(request.getParameter("dutyPeople.id"))) {
			preRepairPlanDetail.setDutyPeople(this.userManager.loadUser(this.getId("dutyPeople.id")));
		}
		if (!StringUtils.isEmpty(request.getParameter("repairLevel.id"))) {
			preRepairPlanDetail.setRepairLevel(this.codeValueManager.loadCodeValue(this.getId("repairLevel.id")));
		} else {
			preRepairPlanDetail.setRepairLevel(null);
		}
		//设置预防性维修计划明细的费用来源
//		if (request.getParameter("sourceType").equals("IN_BUDGET")) {
//			this.preRepairPlanDetail.setSourceType(FeeSourceType.IN_BUDGET);      //设置为预算内
//		} else {
//			this.preRepairPlanDetail.setSourceType(FeeSourceType.OUT_BUDGET);     //设置为预算外
//		}
		if (!StringUtils.isEmpty(request.getParameter("sourceType"))) {   //获取费用来源
			preRepairPlanDetail.setFeeSource(this.codeValueManager.loadCodeTypeByRealCode(request.getParameter("sourceType").trim()));
		}else{
			preRepairPlanDetail.setFeeSource(null);
		}
		//移除该预算编号关联的年度预算总维修费用
		if (null != preRepairPlanDetail.getBudgetNo()) {
			this.budgetDetailManager.removeRepairFeeFromBudgetDetail(preRepairPlanDetail.getBudgetNo(), preRepairPlanDetail.getPlanAllFee());
		}
		//设置预防性维修计划明细关联的预算明细
		if (!StringUtils.isEmpty(request.getParameter("budgetDetail.id"))) {        //如果为预算内，则置关联的预算明细核预算编号
			BudgetDetail budgetDetail = this.budgetDetailManager.loadBudgetDetail(this.getId("budgetDetail.id"));
			preRepairPlanDetail.setBudgetDetail(budgetDetail);                    //设置关联的预算明细
			preRepairPlanDetail.setBudgetNo(budgetDetail.getBudgetNo());          //设置预算编号
		} else {                                                                   //如果为预算内，则置空预算明细和预算编号
			preRepairPlanDetail.setBudgetDetail(null);
			preRepairPlanDetail.setBudgetNo(null);
		}
		if (!StringUtils.isEmpty(request.getParameter("execPeople.id"))) {
			preRepairPlanDetail.setExecPeople(this.userManager.loadUser(this.getId("execPeople.id")));
		}
		boolean isNew = this.preRepairPlanDetail.isNew();
		try {
			this.preRepairPlanDetailManager.storePreRepairPlanDetail(preRepairPlanDetail);
		} catch (Exception e) {
			preRepairPlanDetail.setId(null);
			if (preRepairPlanDetail.getAsset()!=null) {
				this.addActionMessage(this.getText("preRepairProcDetail.edit.failure", Arrays
						.asList(new Object[] { preRepairPlanDetail.getAsset().getName(), preRepairPlanDetail.getAsset().getDeviceNo()})));
			}
			return ERROR;
		}	
		
		if (isNew) {
			
					this.addActionMessage(this.getText("preRepairPlanDetail.add.success"));
					return NEW;
			}
		 else {  
				this.addActionMessage(this.getText("preRepairPlanDetail.edit.success"));
				return SUCCESS;
			}
	}
	
	public PreRepairPlanDetail getPreRepairPlanDetail() {
		return preRepairPlanDetail;
	}
	
	public void setPreRepairPlanDetail(PreRepairPlanDetail preRepairPlanDetail) {
		this.preRepairPlanDetail = preRepairPlanDetail;
	}
	
	public PreRepairPlan getPreRepairPlan() {
		return preRepairPlan;
	}
	
	public void setPreRepairPlan(PreRepairPlan preRepairPlan) {
		this.preRepairPlan = preRepairPlan;
	}
	
	public DeviceCard getDevice() {
		return asset;
	}
	
    public DeviceCard getTooling() {
    	return asset;
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
	 * 获取所有维修级别的集合
	 * @return List 维修级别集合
	 */
	public List getRepairLevels() {
		return this.codeValueManager.LoadAllValuesByCode(CodeConstants.REPAIR_LEVEL);
	}
	
//	/**
//	 * 获取所有费用来源的类型集合
//	 * @return List 费用来源的类型集合
//	 */
//	public List<LabelValue> getSourceTypes() {
//		LabelValue[] arrays = this.wrapEnum(FeeSourceType.class);
//		List<LabelValue> tmp = new ArrayList<LabelValue>();
//		for (int i = 0; i < arrays.length; i++) {
//			tmp.add(arrays[i]);
//		}
//		return tmp;
//	}
	/**
	 * 获取所有费用来源的类型集合
	 * @return List 费用来源的类型集合
	 */
	public List getFeeSourceTypes() {
		return this.codeValueManager.LoadAllValuesByCode(CodeConstants.FEE_SOURCE_TYPE);
		
	}
	
	/**
	 * 获取费用来源
	 * @return
	 */
	public String getSourceType() {
		if (null != this.preRepairPlanDetail.getFeeSource()) {
			return this.preRepairPlanDetail.getFeeSource().getRealCode();
		}
		return null;
	}
//	/**
//	 * 获取费用来源
//	 * @return FeeSourceType
//	 */
//	public FeeSourceType getSourceType() {
//		return this.preRepairPlanDetail.getSourceType();
//	}
	
	/**
	 * 获取实施列表中执行结果集合
	 * @return List 执行结果集合
	 */
	public List<LabelValue> getProcResults() {
		LabelValue[] arrays = this.wrapEnum(PreRepairDetailResult.class);
		List<LabelValue> tmp = new ArrayList<LabelValue>();
		for (int i = 0; i < arrays.length; i++) {
			tmp.add(arrays[i]);
		}
		return tmp;
	}
	
	public String getPlanProcFlag() {
		return planProcFlag;
	}

	public void setPlanProcFlag(String planProcFlag) {
		this.planProcFlag = planProcFlag;
	}

	public String getToolingDevFlag() {
		return toolingDevFlag;
	}

	public void setToolingDevFlag(String toolingDevFlag) {
		this.toolingDevFlag = toolingDevFlag;
	}

	public BudgetDetail getBudgetDetail() {
//		if (null != this.preRepairPlanDetail.getFeeSource()) {
//			if (this.preRepairPlanDetail.getFeeSource().getRealCode().equals("IN_BUDGET")) {
//				return this.preRepairPlanDetail.getBudgetDetail();
//			} else {
//				return null;
//			}
//		}
//		return null;
		return budgetDetail;
	}	
	
}
