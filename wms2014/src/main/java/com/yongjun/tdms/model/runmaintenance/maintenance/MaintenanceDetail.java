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
package com.yongjun.tdms.model.runmaintenance.maintenance;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.yongjun.pluto.model.security.User;
import com.yongjun.tdms.model.BaseInfoEntity;
import com.yongjun.tdms.model.asset.device.DeviceCard;
import com.yongjun.tdms.model.base.codevalue.CodeValue;

/**
 * @author wzou
 * @version $Id: MaintenanceDetail.java 27787 2010-10-14 02:47:51Z zbzhang $
 */
public class MaintenanceDetail extends BaseInfoEntity {
	private static final long serialVersionUID = 633323056347187397L;
	
	// 计划完成日期
	private Date planDate;
	// 实际完成日期
	private Date actualDate;
	// 保养部位
	private String part;
	// 保养方法
	private String method;
	// 保养要求
	private String appeal;
	// 责任人
	private User dutyPeople;
	// 保养结果，默认为未完成
	private MaintenanceResultType result = MaintenanceResultType.UNFINISHED;
	// 保养类型
	private CodeValue resultType;
	// 备注
	private String comment;
	// 保养计划
	private Maintenance plan;
	// 保养实施
	private Maintenance proc;
	// 设备
	private DeviceCard device;
	// 保养设备明细 
	private Set<MaintenanceDeviceDetail> maintenanceDeviceDetails = new HashSet<MaintenanceDeviceDetail>();
	
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

	public Date getActualDate() {
		return actualDate;
	}

	public void setActualDate(Date actualDate) {
		this.actualDate = actualDate;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public User getDutyPeople() {
		return dutyPeople;
	}

	public void setDutyPeople(User dutyPeople) {
		this.dutyPeople = dutyPeople;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getPart() {
		return part;
	}

	public void setPart(String part) {
		this.part = part;
	}

	public Maintenance getPlan() {
		return plan;
	}

	public void setPlan(Maintenance plan) {
		this.plan = plan;
	}

	public Date getPlanDate() {
		return planDate;
	}

	public void setPlanDate(Date planDate) {
		this.planDate = planDate;
	}

	public Maintenance getProc() {
		return proc;
	}

	public void setProc(Maintenance proc) {
		this.proc = proc;
	}

	public String getAppeal() {
		return appeal;
	}

	public void setAppeal(String appeal) {
		this.appeal = appeal;
	}

	public MaintenanceResultType getResult() {
		return result;
	}

	public void setResult(MaintenanceResultType result) {
		this.result = result;
	}

	public DeviceCard getDevice() {
		return device;
	}

	public void setDevice(DeviceCard device) {
		this.device = device;
	}

	public CodeValue getResultType() {
		return resultType;
	}

	public void setResultType(CodeValue resultType) {
		this.resultType = resultType;
	}

	public Set<MaintenanceDeviceDetail> getMaintenanceDeviceDetails() {
		return maintenanceDeviceDetails;
	}

	public void setMaintenanceDeviceDetails(
			Set<MaintenanceDeviceDetail> maintenanceDeviceDetails) {
		this.maintenanceDeviceDetails = maintenanceDeviceDetails;
	}
	public String getResultTxt() {
		return this.resultType.getValue();
	}
	public String getmaintenanceResultTxt() {
		return this.result == MaintenanceResultType.UNFINISHED ? "UNFINISHED" : "FINISHED";
	}
	
}
