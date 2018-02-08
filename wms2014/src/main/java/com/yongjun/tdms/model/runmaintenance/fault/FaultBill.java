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
package com.yongjun.tdms.model.runmaintenance.fault;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.model.tracking.CreatedTimeTracking;
import com.yongjun.pluto.model.tracking.CreatorTracking;
import com.yongjun.pluto.model.tracking.LastModifiedTimeTracking;
import com.yongjun.pluto.model.tracking.LastOperatorTracking;
import com.yongjun.tdms.model.BaseInfoEntity;
import com.yongjun.tdms.model.SysModel;
import com.yongjun.tdms.model.asset.device.DeviceCard;
import com.yongjun.tdms.model.base.codevalue.CodeValue;

/**
 * <p>Title: FaultBill
 * <p>Description: 故障单类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id: FaultBill.java 27787 2010-10-14 02:47:51Z zbzhang $
 */

public class FaultBill extends BaseInfoEntity implements CreatorTracking,
CreatedTimeTracking, LastOperatorTracking, LastModifiedTimeTracking {
	private static final long serialVersionUID = -4604074913275481943L;
	private String billNo;               //故障单编号
	private String billName;             //故障单名称
	private Date faultOccurDateTm;       //故障发生日期
	private String faultDetailContent;   //故障详细
	private String faultCause;			 //故障原因
	private String faultSolution;        //解决方案
	private boolean faultFlag = false;  //故障状态,默认为待解决
	private DeviceCard toolingDev;      //工装或设备
	private User manager;               //负责人
	private SysModel toolingDevFlag;    // 标示为设备或工装
	private Date stopTimeBegin;		    // 停机时间起
	private CodeValue faultCatorgy;		//故障类别
	private String devicePosition;		//设备部位
	private String deviceCatorgy;		//设备类别（做显示用）
	@Override
	public String getDomainModelProperty() {
		// TODO Auto-generated method stub
		return this.getProperties().getProperty("fault_bill");
	}

	private Date stopTimeEnd;		    // 停机时间止
	private Date repairTimeBegin;		// 维修时间开始
	private Date repairTimeEnd;         // 维修时间结束
	private String repairPeople;		// 维修人
	private Long faultLossTime;         //故障耗时
	private Long faultRepairTime;       //维修耗时
	private FaultType result;			//故障类型
	private boolean disabled; 
	//关联的维修详细报告
	private Set<FaultRepair> faultRepair = new HashSet<FaultRepair>();
   /*----------------------------新增加的字段------------------------------*/
	//当工装名称没有编号时所加入的工装名称字段  
	
	private String toolingName;
	private Department department;   //部门
	
	
	
	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	public FaultType getResult() {
		return result;
	}

	public void setResult(FaultType result) {
		this.result = result;
	}

	public FaultBill() {
		
	}
	
	public String getRepairPeople() {
		return repairPeople;
	}

	public void setRepairPeople(String repairPeople) {
		this.repairPeople = repairPeople;
	}

	public Date getStopTimeBegin() {
		return stopTimeBegin;
	}

	public void setStopTimeBegin(Date stopTimeBegin) {
		this.stopTimeBegin = stopTimeBegin;
	}

	public Date getStopTimeEnd() {
		return stopTimeEnd;
	}

	public void setStopTimeEnd(Date stopTimeEnd) {
		this.stopTimeEnd = stopTimeEnd;
	}
	
	public String getBillName() {
		return billName;
	}
	
	public void setBillName(String billName) {
		this.billName = billName;
	}

	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public String getFaultDetailContent() {
		return faultDetailContent;
	}

	public void setFaultDetailContent(String faultDetailContent) {
		this.faultDetailContent = faultDetailContent;
	}

	public boolean isFaultFlag() {
		return faultFlag;
	}

	public void setFaultFlag(boolean faultFlag) {
		this.faultFlag = faultFlag;
	}

	public Date getFaultOccurDateTm() {
		return faultOccurDateTm;
	}

	public void setFaultOccurDateTm(Date faultOccurDateTm) {
		this.faultOccurDateTm = faultOccurDateTm;
	}

	public String getFaultSolution() {
		return faultSolution;
	}

	public void setFaultSolution(String faultSolution) {
		this.faultSolution = faultSolution;
	}

	public User getManager() {
		return manager;
	}

	public void setManager(User manager) {
		this.manager = manager;
	}

	public DeviceCard getToolingDev() {
		return toolingDev;
	}

	public void setToolingDev(DeviceCard toolingDev) {
		this.toolingDev = toolingDev;
	}
	
	@Override
	public int hashCode() {
		return billNo.hashCode();
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof FaultBill)) {return false;}
		FaultBill faultBill = (FaultBill)o;
		if (this.billNo.equals(faultBill.getBillNo())) {return true;}
		return false;
	}

	public SysModel getToolingDevFlag() {
		return toolingDevFlag;
	}

	public void setToolingDevFlag(SysModel toolingDevFlag) {
		this.toolingDevFlag = toolingDevFlag;
	}

	public Date getRepairTimeBegin() {
		return repairTimeBegin;
	}

	public void setRepairTimeBegin(Date repairTimeBegin) {
		this.repairTimeBegin = repairTimeBegin;
	}

	public Date getRepairTimeEnd() {
		return repairTimeEnd;
	}

	public void setRepairTimeEnd(Date repairTimeEnd) {
		this.repairTimeEnd = repairTimeEnd;
	}

	public Long getFaultLossTime() {
		return faultLossTime;
	}

	public void setFaultLossTime(Long faultLossTime) {
		this.faultLossTime = faultLossTime;
	}

	public String getFaultCause() {
		return faultCause;
	}

	public void setFaultCause(String faultCause) {
		this.faultCause = faultCause;
	}

	public Set<FaultRepair> getFaultRepair() {
		return faultRepair;
	}

	public void setFaultRepair(Set<FaultRepair> faultRepair) {
		this.faultRepair = faultRepair;
	}

	public Long getFaultRepairTime() {
		return faultRepairTime;
	}

	public void setFaultRepairTime(Long faultRepairTime) {
		this.faultRepairTime = faultRepairTime;
	}

	public String getToolingName() {
		return toolingName;
	}

	public void setToolingName(String toolingName) {
		this.toolingName = toolingName;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}
	
	/**
	 * 仅用于维修申请报表
	 * @return
	 */
	public String getAssetName() {
		return (toolingDev != null) ? toolingDev.getName() : toolingName;
	}

	public CodeValue getFaultCatorgy() {
		return faultCatorgy;
	}

	public void setFaultCatorgy(CodeValue faultCatorgy) {
		this.faultCatorgy = faultCatorgy;
	}

	public String getDevicePosition() {
		return devicePosition;
	}

	public void setDevicePosition(String devicePosition) {
		this.devicePosition = devicePosition;
	}

	public String getDeviceCatorgy() {
		return deviceCatorgy;
	}

	public void setDeviceCatorgy(String deviceCatorgy) {
		this.deviceCatorgy = deviceCatorgy;
	}

}
