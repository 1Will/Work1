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
import com.yongjun.tdms.model.base.workType.WorkType;
import com.yongjun.tdms.model.runmaintenance.fault.FaultRepair;
import com.yongjun.tdms.model.year.repair.RepairPlanAndProcDetail;

/**
 * <p>Title: RepairManHour
 * <p>Description: 维修工时类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id: RepairManHour.java 27787 2010-10-14 02:47:51Z zbzhang $
 */
public class RepairManHour extends BaseInfoEntity {
	private static final long serialVersionUID = -5402648389512869012L;

	// 工种
	private WorkType workType;
	
	//工时单价
	private Double unitPrice;
	
	// 计划工时
	private Double manHourNum;
	
	//实际工时
	private Double procManHourNum;
	
	// 备注
	private String comment;
	
	// 维修计划明细
	private PreRepairPlanDetail preRepairDetail;
	
    //大项修明细
	private RepairPlanAndProcDetail repairPlanAndProcDetail;
	
	 //故障维修关联的技术资料 
	private FaultRepair faultRepair;  
	
	public RepairManHour() {
		
	}

	public String getComment() {
		return comment;
	}

	public RepairPlanAndProcDetail getRepairPlanAndProcDetail() {
		return repairPlanAndProcDetail;
	}

	public void setRepairPlanAndProcDetail(
			RepairPlanAndProcDetail repairPlanAndProcDetail) {
		this.repairPlanAndProcDetail = repairPlanAndProcDetail;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Double getManHourNum() {
		return manHourNum;
	}

	public void setManHourNum(Double manHourNum) {
		this.manHourNum = manHourNum;
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

	public Double getProcManHourNum() {
		return procManHourNum;
	}

	public void setProcManHourNum(Double procManHourNum) {
		this.procManHourNum = procManHourNum;
	}

	public WorkType getWorkType() {
		return workType;
	}

	public void setWorkType(WorkType workType) {
		this.workType = workType;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public FaultRepair getFaultRepair() {
		return faultRepair;
	}

	public void setFaultRepair(FaultRepair faultRepair) {
		this.faultRepair = faultRepair;
	}



}
