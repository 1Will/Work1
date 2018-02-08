package com.yongjun.tdms.model.runmaintenance.fault;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.User;
import com.yongjun.tdms.model.BaseInfoEntity;
import com.yongjun.tdms.model.base.codevalue.CodeValue;
import com.yongjun.tdms.model.base.document.ApplicationDoc;
import com.yongjun.tdms.model.runmaintenance.repair.RepairFee;
import com.yongjun.tdms.model.runmaintenance.repair.RepairItem;
import com.yongjun.tdms.model.runmaintenance.repair.RepairManHour;
import com.yongjun.tdms.model.runmaintenance.repair.RepairSpare;
import com.yongjun.tdms.model.runmaintenance.repair.RepairTool;
import com.yongjun.tdms.model.year.budget.BudgetDetail;

public class FaultRepair extends BaseInfoEntity {
	private static final long serialVersionUID = 7263858982086685814L;
	
	//维修级别
	private CodeValue repairLevel;
	
	//完成时间
	private Date finishedDate;
	
	//费用类型
	private CodeValue sourceType;
	
	//费用预算编号
	private String budgetNo;
	
	//总费用
	private Double allFee;
	
	//外协单位
	private String externalHelpDepartment;
	
	//外协标识 默认不是外协
	private boolean externalHelpFlag = false;
	
	//责任人
	private User dutyPeople;
	
	//验收人
	private User receiver;
	
	//责任单位
	private Department department;
	
	//关联的故障单
	private FaultBill faultBill;
	
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
 	
	@Override
	public int hashCode() {
		return getId().hashCode();
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof FaultRepair)) {return false;}
		FaultRepair faultRepair = (FaultRepair)o;
		if (this.getId().equals(faultRepair.getId())) {return true;}
		return false;
	}

	public Double getAllFee() {
		return allFee;
	}

	public void setAllFee(Double allFee) {
		this.allFee = allFee;
	}

	public String getBudgetNo() {
		return budgetNo;
	}

	public void setBudgetNo(String budgetNo) {
		this.budgetNo = budgetNo;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public User getDutyPeople() {
		return dutyPeople;
	}

	public void setDutyPeople(User dutyPeople) {
		this.dutyPeople = dutyPeople;
	}

	public String getExternalHelpDepartment() {
		return externalHelpDepartment;
	}

	public void setExternalHelpDepartment(String externalHelpDepartment) {
		this.externalHelpDepartment = externalHelpDepartment;
	}

	public boolean isExternalHelpFlag() {
		return externalHelpFlag;
	}

	public void setExternalHelpFlag(boolean externalHelpFlag) {
		this.externalHelpFlag = externalHelpFlag;
	}

	public FaultBill getFaultBill() {
		return faultBill;
	}

	public void setFaultBill(FaultBill faultBill) {
		this.faultBill = faultBill;
	}

	public Date getFinishedDate() {
		return finishedDate;
	}

	public void setFinishedDate(Date finishedDate) {
		this.finishedDate = finishedDate;
	}

	public Set<RepairManHour> getManHours() {
		return manHours;
	}

	public void setManHours(Set<RepairManHour> manHours) {
		this.manHours = manHours;
	}

	public User getReceiver() {
		return receiver;
	}

	public void setReceiver(User receiver) {
		this.receiver = receiver;
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

	public CodeValue getRepairLevel() {
		return repairLevel;
	}

	public void setRepairLevel(CodeValue repairLevel) {
		this.repairLevel = repairLevel;
	}

	public Set<RepairTool> getRepairTools() {
		return repairTools;
	}

	public void setRepairTools(Set<RepairTool> repairTools) {
		this.repairTools = repairTools;
	}

	public CodeValue getSourceType() {
		return sourceType;
	}

	public void setSourceType(CodeValue sourceType) {
		this.sourceType = sourceType;
	}

	public Set<RepairSpare> getSpares() {
		return spares;
	}

	public void setSpares(Set<RepairSpare> spares) {
		this.spares = spares;
	}

	public BudgetDetail getBudgetDetail() {
		return budgetDetail;
	}

	public void setBudgetDetail(BudgetDetail budgetDetail) {
		this.budgetDetail = budgetDetail;
	}

}
