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
package com.yongjun.tdms.model.year.repair;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.yongjun.pluto.model.security.User;
import com.yongjun.tdms.model.BaseInfoEntity;
import com.yongjun.tdms.model.asset.device.DeviceCard;
import com.yongjun.tdms.model.base.codevalue.CodeValue;
import com.yongjun.tdms.model.base.document.ApplicationDoc;
import com.yongjun.pluto.model.security.Department;
import com.yongjun.tdms.model.runmaintenance.repair.PreRepairDetailResult;
import com.yongjun.tdms.model.runmaintenance.repair.RepairFee;
import com.yongjun.tdms.model.runmaintenance.repair.RepairItem;
import com.yongjun.tdms.model.runmaintenance.repair.RepairManHour;
import com.yongjun.tdms.model.runmaintenance.repair.RepairSpare;
import com.yongjun.tdms.model.runmaintenance.repair.RepairTool;

/**
 * @author qs
 * @version $Id: RepairPlanAndProcDetail.java 27787 2010-10-14 02:47:51Z zbzhang $
 */
public class RepairPlanAndProcDetail extends BaseInfoEntity {
	private static final long serialVersionUID = -1769302358627679291L;

	//工作令号
	private String workNo;
	
	//维修内容
	private String content;
	
	//目标要求
	private String targetDemand;
	
	//计划修理日期
	private Date planRepairDate;
	
	//实际修理日期[实施]
	private Date procRepairDate;
	
	//计划开工日期
	private Date planBeginDate;
	
	//实际开工日期[实施]
	private Date procBeginDate;
	
	//计划完工日期
	private Date planEndDate;
	
	//实际完工日期[实施]
	private Date procEndDate;
	
	//执行情况[实施]
	private String execSituation;
	
	//计划修理费用
	private Double planDetailAllFee;
	
	//实际修理费用[实施]
	private Double procDetailAllFee;
	
	//承修单位
	private Department department;
	
	//是否外协
	private boolean externalHelpFlag;
	
	//执行结果[实施]
	private PreRepairDetailResult procResult = PreRepairDetailResult.UNFINISHED;
	
	private String procResu;
	//执行人[实施]
	private User procExecPeople;
	
	//技术等级
	//private CodeValue technicalLevel;
	//复杂系数的机械标识    true表示选中机械
	private boolean machineFlag = false; 
	
	//复杂系数的电器标识  true表示选中电器
	private boolean electricalFlag = false;
	
	//种类
	private CodeValue category;
	
	//资产[工装][设备]
	private DeviceCard asset;
	
	// 大项修计划
	private RepairPlanAndProc plan;
	
	//大项修实施
	private RepairPlanAndProc proc;
	
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
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	public DeviceCard getAsset() {
		return asset;
	}

	public void setAsset(DeviceCard asset) {
		this.asset = asset;
	}

	public CodeValue getCategory() {
		return category;
	}

	public void setCategory(CodeValue category) {
		this.category = category;
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

	public String getExecSituation() {
		return execSituation;
	}

	public void setExecSituation(String execSituation) {
		this.execSituation = execSituation;
	}

	public boolean isExternalHelpFlag() {
		return externalHelpFlag;
	}

	public void setExternalHelpFlag(boolean externalHelpFlag) {
		this.externalHelpFlag = externalHelpFlag;
	}

	public Date getPlanBeginDate() {
		return planBeginDate;
	}

	public void setPlanBeginDate(Date planBeginDate) {
		this.planBeginDate = planBeginDate;
	}

	public Double getPlanDetailAllFee() {
		return planDetailAllFee;
	}

	public void setPlanDetailAllFee(Double planDetailAllFee) {
		this.planDetailAllFee = planDetailAllFee;
	}

	public Date getPlanEndDate() {
		return planEndDate;
	}

	public void setPlanEndDate(Date planEndDate) {
		this.planEndDate = planEndDate;
	}

	public Date getPlanRepairDate() {
		return planRepairDate;
	}

	public void setPlanRepairDate(Date planRepairDate) {
		this.planRepairDate = planRepairDate;
	}

	public Date getProcBeginDate() {
		return procBeginDate;
	}

	public void setProcBeginDate(Date procBeginDate) {
		this.procBeginDate = procBeginDate;
	}

	public Double getProcDetailAllFee() {
		return procDetailAllFee;
	}

	public void setProcDetailAllFee(Double procDetailAllFee) {
		this.procDetailAllFee = procDetailAllFee;
	}

	public Date getProcEndDate() {
		return procEndDate;
	}

	public void setProcEndDate(Date procEndDate) {
		this.procEndDate = procEndDate;
	}

	public Date getProcRepairDate() {
		return procRepairDate;
	}

	public void setProcRepairDate(Date procRepairDate) {
		this.procRepairDate = procRepairDate;
	}

	public PreRepairDetailResult getProcResult() {
		return procResult;
	}

	public void setProcResult(PreRepairDetailResult procResult) {
		this.procResult = procResult;
	}

	public String getTargetDemand() {
		return targetDemand;
	}

	public void setTargetDemand(String targetDemand) {
		this.targetDemand = targetDemand;
	}

	public String getWorkNo() {
		return workNo;
	}

	public void setWorkNo(String workNo) {
		this.workNo = workNo;
	}

	public Set<RepairManHour> getManHours() {
		return manHours;
	}

	public void setManHours(Set<RepairManHour> manHours) {
		this.manHours = manHours;
	}

	public Set<ApplicationDoc> getRepairDoc() {
		return repairDoc;
	}

	public void setRepairDoc(Set<ApplicationDoc> repairDoc) {
		this.repairDoc = repairDoc;
	}

	public Set<RepairFee> getRepairFees() {
		return repairFees;
	}

	public void setRepairFees(Set<RepairFee> repairFees) {
		this.repairFees = repairFees;
	}

	public Set<RepairItem> getRepairItems() {
		return repairItems;
	}

	public void setRepairItems(Set<RepairItem> repairItems) {
		this.repairItems = repairItems;
	}

	public Set<RepairTool> getRepairTools() {
		return repairTools;
	}

	public void setRepairTools(Set<RepairTool> repairTools) {
		this.repairTools = repairTools;
	}

	public Set<RepairSpare> getSpares() {
		return spares;
	}

	public void setSpares(Set<RepairSpare> spares) {
		this.spares = spares;
	}

	public RepairPlanAndProc getPlan() {
		return plan;
	}

	public void setPlan(RepairPlanAndProc plan) {
		this.plan = plan;
	}

	public RepairPlanAndProc getProc() {
		return proc;
	}

	public void setProc(RepairPlanAndProc proc) {
		this.proc = proc;
	}

	public User getProcExecPeople() {
		return procExecPeople;
	}

	public void setProcExecPeople(User procExecPeople) {
		this.procExecPeople = procExecPeople;
	}

	public boolean isMachineFlag() {
		return machineFlag;
	}

	public void setMachineFlag(boolean machineFlag) {
		this.machineFlag = machineFlag;
	}

	public boolean isElectricalFlag() {
		return electricalFlag;
	}

	public void setElectricalFlag(boolean electricalFlag) {
		this.electricalFlag = electricalFlag;
	}

	public String getExecResult() {
		if (this.procResult.equals("UNFINISHED")) {
			return "未完成";
		} else if (this.procResult.equals("FINISHED")) {
			return "已完成";
		} else if (this.procResult.equals("CANCEL")) {
			return "取消";
		} else {
			return "转移";
		}
	}

	public String getProcResu() {
		return procResu;
	}

	public void setProcResu(String procResu) {
		this.procResu = procResu;
	}
}