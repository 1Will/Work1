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
package com.yongjun.tdms.model.runmaintenance.lubrication;

import java.util.Date;

import com.yongjun.tdms.model.BaseInfoEntity;
import com.yongjun.tdms.model.base.lubricationOil.LubricationOil;

/**
 * <p>Title: CheckPointReportView
 * <p>Description: 润滑计划汇总类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author smzhu@yj-technology.com
 * @version $Id: LubricationPlanView.java 27787 2010-10-14 02:47:51Z zbzhang $
 */
public class LubricationPlanView extends BaseInfoEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//月份
	private String month;
	//部门名称
	private String deptName;
	//润滑计划编号
	private String lubricationPlanNo;
	//润滑计划名称
	private String lubricationPlanName;
	//设备编号
	private String deviceNo;
	//设备名称
	private String deviceName;
	//润滑部位
	private String position;
	//润滑标准
	private String ruleDsp;
	//润滑方法
	private String methodDsp;
	//润滑材质
	private String lubricatingOil;
	//润滑计量单位
	private String lubricatingOilMeasureUnit;
	//计划润滑计量
	private Double planLubricatingOilQty;
	//实际润滑计量
	private Double actualLubricatingOilQty;
	//计划负责人
	private String planExePeople;
	// 计划执行人[计划]
	private String planExectPeople;
	//实施执行人[实施]
	private String actualExePeople;
	
	//计划执行时间
	private Date estimateExecDate;
	//实际执行时间
	private Date actualExecDate;
	//润滑实施状态
	private String planStatus;
	//备注
	private String comment;
	
	private Long lubricationPlanId;

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getDeviceNo() {
		return deviceNo;
	}

	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}

	public Date getEstimateExecDate() {
		return estimateExecDate;
	}

	public void setEstimateExecDate(Date estimateExecDate) {
		this.estimateExecDate = estimateExecDate;
	}

	public String getLubricatingOilMeasureUnit() {
		return lubricatingOilMeasureUnit;
	}

	public void setLubricatingOilMeasureUnit(String lubricatingOilMeasureUnit) {
		this.lubricatingOilMeasureUnit = lubricatingOilMeasureUnit;
	}

	public Long getLubricationPlanId() {
		return lubricationPlanId;
	}

	public void setLubricationPlanId(Long lubricationPlanId) {
		this.lubricationPlanId = lubricationPlanId;
	}

	public String getLubricationPlanName() {
		return lubricationPlanName;
	}

	public void setLubricationPlanName(String lubricationPlanName) {
		this.lubricationPlanName = lubricationPlanName;
	}

	public String getLubricationPlanNo() {
		return lubricationPlanNo;
	}

	public void setLubricationPlanNo(String lubricationPlanNo) {
		this.lubricationPlanNo = lubricationPlanNo;
	}

	public String getMethodDsp() {
		return methodDsp;
	}

	public void setMethodDsp(String methodDsp) {
		this.methodDsp = methodDsp;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getPlanExePeople() {
		return planExePeople;
	}

	public void setPlanExePeople(String planExePeople) {
		this.planExePeople = planExePeople;
	}

	public Double getPlanLubricatingOilQty() {
		return planLubricatingOilQty;
	}

	public void setPlanLubricatingOilQty(Double planLubricatingOilQty) {
		this.planLubricatingOilQty = planLubricatingOilQty;
	}
	
	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getRuleDsp() {
		return ruleDsp;
	}

	public void setRuleDsp(String ruleDsp) {
		this.ruleDsp = ruleDsp;
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

	public Date getActualExecDate() {
		return actualExecDate;
	}

	public void setActualExecDate(Date actualExecDate) {
		this.actualExecDate = actualExecDate;
	}

	public String getActualExePeople() {
		return actualExePeople;
	}

	public void setActualExePeople(String actualExePeople) {
		this.actualExePeople = actualExePeople;
	}

	public Double getActualLubricatingOilQty() {
		return actualLubricatingOilQty;
	}

	public void setActualLubricatingOilQty(Double actualLubricatingOilQty) {
		this.actualLubricatingOilQty = actualLubricatingOilQty;
	}

	public String getPlanStatus() {
		return planStatus;
	}

	public void setPlanStatus(String planStatus) {
		this.planStatus = planStatus;
	}

	public String getLubricatingOil() {
		return lubricatingOil;
	}

	public void setLubricatingOil(String lubricatingOil) {
		this.lubricatingOil = lubricatingOil;
	}

	public String getPlanExectPeople() {
		return planExectPeople;
	}

	public void setPlanExectPeople(String planExectPeople) {
		this.planExectPeople = planExectPeople;
	}
}
