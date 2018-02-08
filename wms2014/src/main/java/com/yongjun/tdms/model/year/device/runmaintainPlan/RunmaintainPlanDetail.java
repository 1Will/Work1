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
package com.yongjun.tdms.model.year.device.runmaintainPlan;

import java.util.HashSet;
import java.util.Set;

import com.yongjun.tdms.model.BaseInfoEntity;
import com.yongjun.tdms.model.asset.device.DeviceCard;

/**
 * 
 * <p>Title: RunmaintainPlanDetail
 * <p>Description: 设备运维计划明细类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id: RunmaintainPlanDetail.java 27787 2010-10-14 02:47:51Z zbzhang $
 */
public class RunmaintainPlanDetail extends BaseInfoEntity {
	private static final long serialVersionUID = 5667602756786750191L;
	
	//日常维修费用
	private Double dailyRepairFee = 0.0;
	//精度检查费用
	private Double pricisionCheckFee = 0.0;
	//改造费用
	private Double changeFee = 0.0;
	//关键备件费用
	private Double pivotalSpareFee = 0.0;
	//总费用
	private Double allFee = 0.0;
	//备注
	private String comment;
	//关联的设备
	private DeviceCard device;
	//关联的设备运维计划
	private RunmaintainPlan runmaintainPlan;
	//关联的日常维修
	private Set<DailyRepair> dailyRepairs = new HashSet<DailyRepair>();
	//关联的精度检查
	private Set<PricisionCheck> pricisionChecks = new HashSet<PricisionCheck>();
	//关联的设备改造
	private Set<DeviceChange> deviceChanges = new HashSet<DeviceChange>();
	//关联的关键备件
	private Set<PivotalSpare> pivotalSpares = new HashSet<PivotalSpare>();
	
	@Override
	public int hashCode() {
		return this.getId().hashCode();
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) {return true;}
		if (!(o instanceof RunmaintainPlanDetail)) {
			return false;
		}
		return false;
	}

	public Double getAllFee() {
		return allFee;
	}

	public void setAllFee(Double allFee) {
		this.allFee = allFee;
	}

	public Double getChangeFee() {
		return changeFee;
	}

	public void setChangeFee(Double changeFee) {
		this.changeFee = changeFee;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Double getDailyRepairFee() {
		return dailyRepairFee;
	}

	public void setDailyRepairFee(Double dailyRepairFee) {
		this.dailyRepairFee = dailyRepairFee;
	}

	public Double getPivotalSpareFee() {
		return pivotalSpareFee;
	}

	public void setPivotalSpareFee(Double pivotalSpareFee) {
		this.pivotalSpareFee = pivotalSpareFee;
	}

	public Double getPricisionCheckFee() {
		return pricisionCheckFee;
	}

	public void setPricisionCheckFee(Double pricisionCheckFee) {
		this.pricisionCheckFee = pricisionCheckFee;
	}

	public RunmaintainPlan getRunmaintainPlan() {
		return runmaintainPlan;
	}

	public void setRunmaintainPlan(RunmaintainPlan runmaintainPlan) {
		this.runmaintainPlan = runmaintainPlan;
	}

	public DeviceCard getDevice() {
		return device;
	}

	public void setDevice(DeviceCard device) {
		this.device = device;
	}

	public Set<DailyRepair> getDailyRepairs() {
		return dailyRepairs;
	}

	public void setDailyRepairs(Set<DailyRepair> dailyRepairs) {
		this.dailyRepairs = dailyRepairs;
	}

	public Set<PricisionCheck> getPricisionChecks() {
		return pricisionChecks;
	}

	public void setPricisionChecks(Set<PricisionCheck> pricisionChecks) {
		this.pricisionChecks = pricisionChecks;
	}

	public Set<DeviceChange> getDeviceChanges() {
		return deviceChanges;
	}

	public void setDeviceChanges(Set<DeviceChange> deviceChanges) {
		this.deviceChanges = deviceChanges;
	}

	public Set<PivotalSpare> getPivotalSpares() {
		return pivotalSpares;
	}

	public void setPivotalSpares(Set<PivotalSpare> pivotalSpares) {
		this.pivotalSpares = pivotalSpares;
	}

}
