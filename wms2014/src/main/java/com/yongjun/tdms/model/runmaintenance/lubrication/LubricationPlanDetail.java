package com.yongjun.tdms.model.runmaintenance.lubrication;

import java.util.Date;

import com.yongjun.tdms.model.BaseInfoEntity;
import com.yongjun.tdms.model.asset.device.DeviceCard;
import com.yongjun.tdms.model.base.codevalue.CodeValue;
import com.yongjun.tdms.model.base.lubricationOil.LubricationOil;
import com.yongjun.tdms.model.runmaintenance.repair.PreRepairDetailResult;

public class LubricationPlanDetail extends BaseLubrication{
	private static final long serialVersionUID = 7266723699185160409L;

	// 计划执行日期[计划]
	private Date estimateExecDate;

	// 润滑标准
	private LubricationRule rule;

	//计划润滑计量[计划]
	private Double planLubricatingOilQty;
	
	//实际润滑计量[实施]
	private Double actualLubricatingOilQty;
	
	//润滑油计量单位
	private String lubricatingOilMeasureUnit;

	// 润滑计划执行状态[实施]
	private LubricationPlanStatus planStatus = LubricationPlanStatus.UNFINISH;

	// 实际执行日期[实施]
	private Date actualExecDate;

	private String dutyPeople;
	
	// 计划执行人[计划]
	private String planExectPeople;
	
	// 润滑计划中的负责人
	private String planExePeople;
	
	// 润滑实施中的执行人
	private String actualExePeople;
	
	// 备注[备注]
	private String comment;
	
	//关联的润滑计划单
	private LubricationPlan plan;
	
	//关联的润滑实施单
	private LubricationPlan proc;

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getDutyPeople() {
		return dutyPeople;
	}

	public void setDutyPeople(String dutyPeople) {
		this.dutyPeople = dutyPeople;
	}

	public Date getEstimateExecDate() {
		return estimateExecDate;
	}

	public void setEstimateExecDate(Date estimateExecDate) {
		this.estimateExecDate = estimateExecDate;
	}

	public LubricationPlanStatus getPlanStatus() {
		return planStatus;
	}

	public void setPlanStatus(LubricationPlanStatus planStatus) {
		this.planStatus = planStatus;
	}

	public LubricationRule getRule() {
		return rule;
	}

	public void setRule(LubricationRule rule) {
		this.rule = rule;
	}
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

	public String getLubricatingOilMeasureUnit() {
		return lubricatingOilMeasureUnit;
	}

	public void setLubricatingOilMeasureUnit(String lubricatingOilMeasureUnit) {
		this.lubricatingOilMeasureUnit = lubricatingOilMeasureUnit;
	}

	public Date getActualExecDate() {
		return actualExecDate;
	}

	public void setActualExecDate(Date actualExecDate) {
		this.actualExecDate = actualExecDate;
	}

	public String getPlanExePeople() {
		return planExePeople;
	}

	public void setPlanExePeople(String planExePeople) {
		this.planExePeople = planExePeople;
	}

	public LubricationPlan getPlan() {
		return plan;
	}

	public void setPlan(LubricationPlan plan) {
		this.plan = plan;
	}

	public String getActualExePeople() {
		return actualExePeople;
	}

	public void setActualExePeople(String actualExePeople) {
		this.actualExePeople = actualExePeople;
	}

	public LubricationPlan getProc() {
		return proc;
	}

	public void setProc(LubricationPlan proc) {
		this.proc = proc;
	}

	public Double getPlanLubricatingOilQty() {
		return planLubricatingOilQty;
	}

	public void setPlanLubricatingOilQty(Double planLubricatingOilQty) {
		this.planLubricatingOilQty = planLubricatingOilQty;
	}

	public Double getActualLubricatingOilQty() {
		return actualLubricatingOilQty;
	}

	public void setActualLubricatingOilQty(Double actualLubricatingOilQty) {
		this.actualLubricatingOilQty = actualLubricatingOilQty;
	}
	
	public String getPlanStatusTxt() {
		return this.planStatus == LubricationPlanStatus.UNFINISH ? "UNFINISH" : "FINISHED";
	}

	public String getPlanExectPeople() {
		return planExectPeople;
	}

	public void setPlanExectPeople(String planExectPeople) {
		this.planExectPeople = planExectPeople;
	}
}
