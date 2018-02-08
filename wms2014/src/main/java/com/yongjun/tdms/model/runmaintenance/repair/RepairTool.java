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
 * <p>Title: RepairTool
 * <p>Description: 维修工具类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id: RepairTool.java 27787 2010-10-14 02:47:51Z zbzhang $
 */
public class RepairTool extends BaseInfoEntity {
	private static final long serialVersionUID = 8803169585960764772L;

	// 工具名称
	private String name;
	
	// 规格
	private String specification;
	
	// 型号
	private String model;
	
	// 计量单位
	private String calcUnit;
	
	// 计划使用工具数量
	private Integer planUseNum;
	
	//实际使用工具数量
	private Integer procUseNum;
	
	// 备注
	private String comment;
	
	// 预防性维修明细
	private PreRepairPlanDetail preRepairDetail;
	
	// 大项修明细
	private RepairPlanAndProcDetail repairPlanAndProcDetail;
	
	 //故障维修关联的技术资料 
	private FaultRepair faultRepair;                      
	
	public RepairTool() {
		
	}
	
	public String getCalcUnit() {
		return calcUnit;
	}

	public void setCalcUnit(String calcUnit) {
		this.calcUnit = calcUnit;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPlanUseNum() {
		return planUseNum;
	}

	public void setPlanUseNum(Integer planUseNum) {
		this.planUseNum = planUseNum;
	}

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
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

	public Integer getProcUseNum() {
		return procUseNum;
	}

	public void setProcUseNum(Integer procUseNum) {
		this.procUseNum = procUseNum;
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
