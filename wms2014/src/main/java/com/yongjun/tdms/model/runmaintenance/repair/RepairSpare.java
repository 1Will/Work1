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


import com.yongjun.tdms.model.BaseInfoEntity;
import com.yongjun.tdms.model.asset.device.DeviceSpare;
import com.yongjun.tdms.model.asset.spare.Spare;
import com.yongjun.tdms.model.runmaintenance.fault.FaultRepair;
import com.yongjun.tdms.model.year.repair.RepairPlanAndProcDetail;

/**
 * <p>Title: RepairSpare
 * <p>Description: 维修备件类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id: RepairSpare.java 27787 2010-10-14 02:47:51Z zbzhang $
 */
public class RepairSpare extends BaseInfoEntity {
	private static final long serialVersionUID = -6204194743317309675L;

	// 计划使用备件数量
	private Integer planUsedNum;
	
	//实际使用备件数量
	private Integer procUsedNum;
	// 备件
	private Spare spare;
   //备件明细中所关联的易损件
	private DeviceSpare  deviceSpare;
	
	// 预防性维修明细
	private PreRepairPlanDetail preRepairDetail;
	
//	 大项修明细
	private RepairPlanAndProcDetail repairPlanAndProcDetail;
	
	 //故障维修关联的技术资料 
	private FaultRepair faultRepair;  
	
	public RepairSpare() {
		
	}
	
	public RepairPlanAndProcDetail getRepairPlanAndProcDetail() {
		return repairPlanAndProcDetail;
	}

	public void setRepairPlanAndProcDetail(
			RepairPlanAndProcDetail repairPlanAndProcDetail) {
		this.repairPlanAndProcDetail = repairPlanAndProcDetail;
	}

	public Integer getPlanUsedNum() {
		return planUsedNum;
	}

	public void setPlanUsedNum(Integer planUsedNum) {
		this.planUsedNum = planUsedNum;
	}

	public Spare getSpare() {
		return spare;
	}

	public void setSpare(Spare spare) {
		this.spare = spare;
	}

	public PreRepairPlanDetail getPreRepairDetail() {
		return preRepairDetail;
	}

	public void setPreRepairDetail(PreRepairPlanDetail preRepairDetail) {
		this.preRepairDetail = preRepairDetail;
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

	public Integer getProcUsedNum() {
		return procUsedNum;
	}

	public void setProcUsedNum(Integer procUsedNum) {
		this.procUsedNum = procUsedNum;
	}

	public FaultRepair getFaultRepair() {
		return faultRepair;
	}

	public void setFaultRepair(FaultRepair faultRepair) {
		this.faultRepair = faultRepair;
	}

	public DeviceSpare getDeviceSpare() {
		return deviceSpare;
	}

	public void setDeviceSpare(DeviceSpare deviceSpare) {
		this.deviceSpare = deviceSpare;
	}
}
