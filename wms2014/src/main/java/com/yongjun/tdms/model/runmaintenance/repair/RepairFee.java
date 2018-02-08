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
import com.yongjun.tdms.model.runmaintenance.fault.FaultRepair;
import com.yongjun.tdms.model.year.repair.RepairPlanAndProcDetail;

/**
 * <p>Title: RepairFee
 * <p>Description: 维修费用类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id: RepairFee.java 27787 2010-10-14 02:47:51Z zbzhang $
 */
public class RepairFee extends BaseInfoEntity {
	private static final long serialVersionUID = 6902475556372264752L;

	// 费用项目
	private String feeItem;
	
	//计划费用额
	private Double planFee;
	
	//实际费用额
	private Double procFee;
	
	// 备注
	private String comment;
	
	// 预防性维修明细
	private PreRepairPlanDetail preRepairDetail;
	
	// 大项修明细
	private RepairPlanAndProcDetail repairPlanAndProcDetail;
	
	 //故障维修关联的技术资料 
	private FaultRepair faultRepair;  
	
	public RepairFee() {
		
	}
	
	public Double getPlanFee() {
		return planFee;
	}
	
	public void setPlanFee(Double planFee) {
		this.planFee = planFee;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getFeeItem() {
		return feeItem;
	}

	public void setFeeItem(String feeItem) {
		this.feeItem = feeItem;
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

	public Double getProcFee() {
		return procFee;
	}

	public void setProcFee(Double procFee) {
		this.procFee = procFee;
	}

	public RepairPlanAndProcDetail getRepairPlanAndProcDetail() {
		return repairPlanAndProcDetail;
	}

	public void setRepairPlanAndProcDetail(
			RepairPlanAndProcDetail repairPlanAndProcDetail) {
		this.repairPlanAndProcDetail = repairPlanAndProcDetail;
	}

	public FaultRepair getFaultRepair() {
		return faultRepair;
	}

	public void setFaultRepair(FaultRepair faultRepair) {
		this.faultRepair = faultRepair;
	}

}
