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
package com.yongjun.tdms.model.runmaintenance.repair;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.yongjun.pluto.model.security.User;
import com.yongjun.tdms.model.BaseInfoEntity;
import com.yongjun.tdms.model.SysModel;
import com.yongjun.tdms.model.asset.device.DeviceCard;
import com.yongjun.tdms.model.asset.device.DeviceSpare;
import com.yongjun.tdms.model.base.codevalue.CodeValue;
import com.yongjun.tdms.model.base.financeType.FeeSourceType;
import com.yongjun.tdms.model.base.document.ApplicationDoc;
import com.yongjun.tdms.model.runmaintenance.usualcheck.Check;
import com.yongjun.tdms.model.year.budget.BudgetDetail;
import com.yongjun.pluto.model.security.Department;

/**
 * <p>Title: PreRepairPlanDetail
 * <p>Description: 预防性维修计划明细类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id: PreRepairPlanDetail.java 27787 2010-10-14 02:47:51Z zbzhang $
 */
public class PreRepairPlanDetail extends BaseInfoEntity {
	private static final long serialVersionUID = 2560086570587189636L;

	// 维修部位
	private String position;
	
	// 工作内容
	private String content;
	
	//目标要求
	private String targetDemand;
	
	// 预计完成日期
	private Date planEstimateFinishDate;
	
	//实际完成日期[实施]
	private Date procEstimateFinishDate;
	
	//执行情况[实施]
	private String execSituation;
	
	//是否外协,默认为[不是外协]
	private boolean externalHelpFlag = false;
	
	//计划总费用
	private Double planAllFee;
	
	//实际总费用
	private Double procAllFee;
	
	//维修等级
	private CodeValue repairLevel;
	
	//费用来源类型
	private FeeSourceType sourceType;
	
	//费用来源
	private CodeValue feeSource;
	
	//预算编号
	private String budgetNo;
	
	// 责任单位
	private Department department;
	
	//责任单位名称
	private String deptName;
	
	// 资产、设备或者工装
	private DeviceCard asset;
	
	// 责任人
	private User dutyPeople;
	
	// 计划执行人
	private User execPeople;
	
	//实际执行人[实施]
	private User procExecPeople;
	
	//验收人[实施]
	private User procCheckPeople;
	
	//执行结果[实施]
	private PreRepairDetailResult procResult = PreRepairDetailResult.UNFINISHED;
	//维修明细中所关联的日常检查
	private Check check;
	//维修明细中所关良的故障率
	private BrockenRate  brockenRate;

	////维修明细中所关连的易损备件
	private DeviceSpare devieSpare;
	//维修明细中所关联的预维标准
	private PreRepairRule preRepairRule;

	//工装设备标识[工装|设备]
	private SysModel toolingDevFlag;	
	
	// 预防性维修计划
	private PreRepairPlan plan;
	
	//预防性维修实施
	private PreRepairPlan proc;
	
	//关联的预算详细
	private BudgetDetail budgetDetail;
	
	// 维修明细中的备件信息集合
	private Set<RepairSpare> spares = new HashSet<RepairSpare>();
	
	// 维修明细中的维修项目集合
	private Set<RepairItem> repairItems = new HashSet<RepairItem>();
	
	// 维修明细中的费用集合
	private Set<RepairFee> repairFees = new HashSet<RepairFee>();
	
	// 维修明细中的工具集合
	private Set<RepairTool> repairTools = new HashSet<RepairTool>();
	
	// 维修明细中的工时集合
	private Set<RepairManHour> manHours = new HashSet<RepairManHour>();
	
	// 设备文档集合
 	private Set<ApplicationDoc> repairDoc = new HashSet<ApplicationDoc>();
	//--------------------------------新增加的字段--------------------------//
 	//责任人
 	private String dutypeople;
 	
 	//计划执行人
 	private String planExecPeople;
 	
 	//实际执行人
 	private String  practiceExecPeople;
 	
 	//工装名称
 	private String toolingName;

	public PreRepairPlanDetail() {
		
	}
	
	public String getProcResultTxt() {
		return this.procResult == PreRepairDetailResult.FINISHED ? "FINISHED" : "UNFINISHED";
	}
	
	public Set<RepairManHour> getManHours() {
		return this.manHours;
	}
	
	public void setManHours(Set<RepairManHour> manHours) {
		this.manHours = manHours;
	}
	
	public Set<RepairTool> getRepairTools() {
		return this.repairTools;
	}
	
	public void setRepairTools(Set<RepairTool> repairTools) {
		this.repairTools = repairTools;
	}
	
	public Set<RepairFee> getRepairFees() {
		return this.repairFees;
	}
	
	public void setRepairFees(Set<RepairFee> repairFees) {
		this.repairFees = repairFees;
	}
	
	public Set<RepairItem> getRepairItems() {
		return this.repairItems;
	}
	
	public void setRepairItems(Set<RepairItem> repairItems) {
		this.repairItems = repairItems;
	}
	
	public Set<RepairSpare> getSpares() {
		return this.spares;
	}
	
	public void setSpares(Set<RepairSpare> spares) {
		this.spares = spares;
	}
	
	public User getDutyPeople() {
		return dutyPeople;
	}

	public void setDutyPeople(User dutyPeople) {
		this.dutyPeople = dutyPeople;
	}

	public User getExecPeople() {
		return execPeople;
	}

	public void setExecPeople(User execPeople) {
		this.execPeople = execPeople;
	}

	public DeviceCard getAsset() {
		return asset;
	}

	public void setAsset(DeviceCard asset) {
		this.asset = asset;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	@Override
	public int hashCode() {
		return this.getId().hashCode();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!( o instanceof PreRepairPlanDetail)) {
			return false;
		}
		PreRepairPlanDetail detail = (PreRepairPlanDetail)o;
		if (this.getId().equals(detail.getId())) {
			return true;
		} else {
			return false;
		}
	}

	public PreRepairPlan getPlan() {
		return plan;
	}

	public void setPlan(PreRepairPlan plan) {
		this.plan = plan;
	}

	public User getProcExecPeople() {
		return procExecPeople;
	}

	public void setProcExecPeople(User procExecPeople) {
		this.procExecPeople = procExecPeople;
	}

	public PreRepairDetailResult getProcResult() {
		return procResult;
	}

	public void setProcResult(PreRepairDetailResult procResult) {
		this.procResult = procResult;
	}

	public Double getPlanAllFee() {
		return planAllFee;
	}

	public void setPlanAllFee(Double planAllFee) {
		this.planAllFee = planAllFee;
	}

	public Double getProcAllFee() {
		return procAllFee;
	}

	public void setProcAllFee(Double procAllFee) {
		this.procAllFee = procAllFee;
	}

	public SysModel getToolingDevFlag() {
		return toolingDevFlag;
	}

	public void setToolingDevFlag(SysModel toolingDevFlag) {
		this.toolingDevFlag = toolingDevFlag;
	}

	public String getExecSituation() {
		return execSituation;
	}

	public void setExecSituation(String execSituation) {
		this.execSituation = execSituation;
	}

	public User getProcCheckPeople() {
		return procCheckPeople;
	}

	public void setProcCheckPeople(User procCheckPeople) {
		this.procCheckPeople = procCheckPeople;
	}

	public Date getProcEstimateFinishDate() {
		return procEstimateFinishDate;
	}

	public void setProcEstimateFinishDate(Date procEstimateFinishDate) {
		this.procEstimateFinishDate = procEstimateFinishDate;
	}

	public String getTargetDemand() {
		return targetDemand;
	}

	public void setTargetDemand(String targetDemand) {
		this.targetDemand = targetDemand;
	}

	public boolean isExternalHelpFlag() {
		return externalHelpFlag;
	}

	public void setExternalHelpFlag(boolean externalHelpFlag) {
		this.externalHelpFlag = externalHelpFlag;
	}

	public String getBudgetNo() {
		return budgetNo;
	}

	public void setBudgetNo(String budgetNo) {
		this.budgetNo = budgetNo;
	}

	public FeeSourceType getSourceType() {
		return sourceType;
	}

	public void setSourceType(FeeSourceType sourceType) {
		this.sourceType = sourceType;
	}

	public Date getPlanEstimateFinishDate() {
		return planEstimateFinishDate;
	}

	public void setPlanEstimateFinishDate(Date planEstimateFinishDate) {
		this.planEstimateFinishDate = planEstimateFinishDate;
	}

	public CodeValue getRepairLevel() {
		return repairLevel;
	}

	public void setRepairLevel(CodeValue repairLevel) {
		this.repairLevel = repairLevel;
	}

	public Set<ApplicationDoc> getRepairDoc() {
		return repairDoc;
	}

	public void setRepairDoc(Set<ApplicationDoc> repairDoc) {
		this.repairDoc = repairDoc;
	}

	public PreRepairPlan getProc() {
		return proc;
	}

	public void setProc(PreRepairPlan proc) {
		this.proc = proc;
	}

	public Check getCheck() {
		return check;
	}

	public void setCheck(Check check) {
		this.check = check;
	}

	public BrockenRate getBrockenRate() {
		return brockenRate;
	}

	public void setBrockenRate(BrockenRate brockenRate) {
		this.brockenRate = brockenRate;
	}

	public DeviceSpare getDevieSpare() {
		return devieSpare;
	}

	public void setDevieSpare(DeviceSpare devieSpare) {
		this.devieSpare = devieSpare;
	}

	public BudgetDetail getBudgetDetail() {
		return budgetDetail;
	}

	public void setBudgetDetail(BudgetDetail budgetDetail) {
		this.budgetDetail = budgetDetail;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public CodeValue getFeeSource() {
		return feeSource;
	}

	public void setFeeSource(CodeValue feeSource) {
		this.feeSource = feeSource;
	}

	public String getDutypeople() {
		return dutypeople;
	}

	public void setDutypeople(String dutypeople) {
		this.dutypeople = dutypeople;
	}

	public String getPlanExecPeople() {
		return planExecPeople;
	}

	public void setPlanExecPeople(String planExecPeople) {
		this.planExecPeople = planExecPeople;
	}

	public String getPracticeExecPeople() {
		return practiceExecPeople;
	}

	public void setPracticeExecPeople(String practiceExecPeople) {
		this.practiceExecPeople = practiceExecPeople;
	}

	public String getToolingName() {
		return toolingName;
	}

	public void setToolingName(String toolingName) {
		this.toolingName = toolingName;
	}
	
	/**
	 * 获取资产名称，用于预防性维修明细报表
	 * @return
	 */
	public String getAssetName() {
		return (asset != null) ? asset.getName() : toolingName;
	}
}
