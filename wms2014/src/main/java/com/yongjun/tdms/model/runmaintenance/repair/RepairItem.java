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


import java.util.Date;

import com.yongjun.pluto.model.security.User;
import com.yongjun.tdms.model.BaseInfoEntity;
import com.yongjun.tdms.model.runmaintenance.fault.FaultRepair;
import com.yongjun.tdms.model.year.repair.RepairPlanAndProcDetail;

/**
 * <p>Title: RepairItem
 * <p>Description: 维修明细内容标准类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id: RepairItem.java 27787 2010-10-14 02:47:51Z zbzhang $
 */
public class RepairItem extends BaseInfoEntity {
	private static final long serialVersionUID = -1914503272809091209L;

	// 维修部位
	private String position;
	
	// 维修内容
	private String content;
	
	// 目标要求
	private String aimRequire;
	
	// 计划完成日期
	private Date planCompleteDate;
	
	// 计划执行人,该字段已经不再使用
	private User execPeople;
	
	// 计划执行人 
	private String execPeopleString;
	
	//实际完成日期
	private Date procCompleteDate;
	
	//实际执行人,该字段已经不再使用
	private User procExecPeople;
	
	//实际执行人
	private String procExecPeopleString;
	
	//备注[实施]
	private String comment;
	
	//维修时间
	private Double repairTime;
	
	// 预防性维修明细
	private PreRepairPlanDetail preRepairDetail;
	
	// 大项修明细
	private RepairPlanAndProcDetail repairPlanAndProcDetail;
	
	 //故障维修关联的技术资料 
	private FaultRepair faultRepair;  
	
	//维修明细中所关联的预维标准
	private PreRepairRule preRepairRule;
	
	public RepairItem() {
		
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
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

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getAimRequire() {
		return aimRequire;
	}

	public void setAimRequire(String aimRequire) {
		this.aimRequire = aimRequire;
	}

	public Date getPlanCompleteDate() {
		return planCompleteDate;
	}

	public void setPlanCompleteDate(Date planCompleteDate) {
		this.planCompleteDate = planCompleteDate;
	}

	public User getExecPeople() {
		return execPeople;
	}

	public void setExecPeople(User execPeople) {
		this.execPeople = execPeople;
	}

	public Date getProcCompleteDate() {
		return procCompleteDate;
	}

	public void setProcCompleteDate(Date procCompleteDate) {
		this.procCompleteDate = procCompleteDate;
	}

	public User getProcExecPeople() {
		return procExecPeople;
	}

	public void setProcExecPeople(User procExecPeople) {
		this.procExecPeople = procExecPeople;
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

	public PreRepairRule getPreRepairRule() {
		return preRepairRule;
	}

	public void setPreRepairRule(PreRepairRule preRepairRule) {
		this.preRepairRule = preRepairRule;
	}

	public String getExecPeopleString() {
		return execPeopleString;
	}

	public void setExecPeopleString(String execPeopleString) {
		this.execPeopleString = execPeopleString;
	}

	public String getProcExecPeopleString() {
		return procExecPeopleString;
	}

	public void setProcExecPeopleString(String procExecPeopleString) {
		this.procExecPeopleString = procExecPeopleString;
	}


}
