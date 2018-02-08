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
package com.yongjun.tdms.model.runmaintenance.calibration;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.yongjun.pluto.model.security.User;
import com.yongjun.tdms.model.BaseInfoEntity;
import com.yongjun.tdms.model.asset.device.DeviceCard;
import com.yongjun.tdms.model.base.document.ApplicationDoc;


/**
 * @author qs
 * @version $Id: CalibrationDetail.java 27787 2010-10-14 02:47:51Z zbzhang $
 */
public class CalibrationDetail extends BaseInfoEntity {
	private static final long serialVersionUID = -6291039778452899994L;
	// 计划标定日期
	private Date planDate;
	// 实际标定日期
	private Date actualDate;
	// 实施结果，默认为未完成
	private CalibrationResultType result = CalibrationResultType.UNFINISHED;
	//标定结果，默认为合格
	private CalibrationPassType calibrationResult  = CalibrationPassType.PASS;
	// 备注
	private String comment;
	// 标定计划
	private Calibration plan;
	// 标定实施
	private Calibration proc;
	// 工装
	private DeviceCard tooling;
	//	责任人
	private User dutyPeople;
	//	设备文档集合
 	private Set<ApplicationDoc> calibrationDoc = new HashSet<ApplicationDoc>();
	
	public CalibrationDetail() {
		
	}
	
	public String getResultTxt() {
		return this.result == CalibrationResultType.FINISHED ? "FINISHED" : "UNFINISHED";
	}
//	public String getCalibrationResult(){
//		return this.calibrationResult == CalibrationPassType.PASS ? "PASS" : "NOPASS";
//	}
//	
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

	public Date getPlanDate() {
		return planDate;
	}

	public void setPlanDate(Date planDate) {
		this.planDate = planDate;
	}

	public CalibrationResultType getResult() {
		return result;
	}

	public void setResult(CalibrationResultType result) {
		this.result = result;
	}

	public DeviceCard getTooling() {
		return tooling;
	}

	public void setTooling(DeviceCard tooling) {
		this.tooling = tooling;
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

	public User getDutyPeople() {
		return dutyPeople;
	}

	public void setDutyPeople(User dutyPeople) {
		this.dutyPeople = dutyPeople;
	}

	public Calibration getPlan() {
		return plan;
	}

	public void setPlan(Calibration plan) {
		this.plan = plan;
	}

	public Calibration getProc() {
		return proc;
	}

	public void setProc(Calibration proc) {
		this.proc = proc;
	}

	public String getCalibrationResultTxt() {
		return this.calibrationResult == CalibrationPassType.PASS ? "PASS" : "NOPASS";
	}
//
//	public void setCalibrationResult(CalibrationPassType calibrationResult) {
//		this.calibrationResult = calibrationResult;
//	}

	public Set<ApplicationDoc> getCalibrationDoc() {
		return calibrationDoc;
	}

	public void setCalibrationDoc(Set<ApplicationDoc> calibrationDoc) {
		this.calibrationDoc = calibrationDoc;
	}

	public CalibrationPassType getCalibrationResult() {
		return calibrationResult;
	}

	public void setCalibrationResult(CalibrationPassType calibrationResult) {
		this.calibrationResult = calibrationResult;
	}
}
