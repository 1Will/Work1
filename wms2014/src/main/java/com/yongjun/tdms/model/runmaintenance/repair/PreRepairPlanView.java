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

import com.yongjun.tdms.model.BaseInfoEntity;
import com.yongjun.tdms.model.base.codevalue.CodeValue;

/**
 * <p>Title: PreRepairPlanView
 * <p>Description: 预防性维修视图类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author smzhu@yj-technology.com
 * @version $Id: PreRepairPlanView.java 27787 2010-10-14 02:47:51Z zbzhang $
 */
public class PreRepairPlanView extends BaseInfoEntity{
	private static final long serialVersionUID = -108186300940738578L;
	//月份
	private Date beginDate;
	//部门名称
	private String deptName;
	//设备编号,工装图号
	private String deviceNo;
	//设备名称，工装名称
	private String deviceName;
	//产品图号
	private String graphNo;
	//工作内容
	private String content;
	//责任单位
	private String dutyDeptName;
    //是否外协,默认为[不是外协]
	private boolean externalHelpFlag = false; 
	//维修等级
	private String repairLevel;
	//责任人
	private String dutyPeople;
	//计划执行人
	private String execPeople;
	//实际执行人
	private String procExecPeople;
	//计划完成日期
	private Date planEstimateFinishDate;
	//实际完成日期
	private Date procEstimateFinishDate;
	//计划费用
	private Double planAllFee;
	//实际费用
	private Double procAllFee;
	//执行结果
	private String procResult;
	//计划总费用
	private Double planAllListFee;
	//实际总费用
	private Double procAllListFee;
	//工装，设备
	private String tooling_dev_flag;
	
	private Long prerepairPlanId;

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public String getDutyDeptName() {
		return dutyDeptName;
	}

	public void setDutyDeptName(String dutyDeptName) {
		this.dutyDeptName = dutyDeptName;
	}

	public boolean isExternalHelpFlag() {
		return externalHelpFlag;
	}

	public void setExternalHelpFlag(boolean externalHelpFlag) {
		this.externalHelpFlag = externalHelpFlag;
	}

	public Double getPlanAllFee() {
		return planAllFee;
	}

	public void setPlanAllFee(Double planAllFee) {
		this.planAllFee = planAllFee;
	}

	public Double getPlanAllListFee() {
		return planAllListFee;
	}

	public void setPlanAllListFee(Double planAllListFee) {
		this.planAllListFee = planAllListFee;
	}

	public Date getPlanEstimateFinishDate() {
		return planEstimateFinishDate;
	}

	public void setPlanEstimateFinishDate(Date planEstimateFinishDate) {
		this.planEstimateFinishDate = planEstimateFinishDate;
	}

	public Double getProcAllFee() {
		return procAllFee;
	}

	public void setProcAllFee(Double procAllFee) {
		this.procAllFee = procAllFee;
	}

	public Double getProcAllListFee() {
		return procAllListFee;
	}

	public void setProcAllListFee(Double procAllListFee) {
		this.procAllListFee = procAllListFee;
	}

	public Date getProcEstimateFinishDate() {
		return procEstimateFinishDate;
	}

	public void setProcEstimateFinishDate(Date procEstimateFinishDate) {
		this.procEstimateFinishDate = procEstimateFinishDate;
	}

	public String getProcResult() {
		return procResult;
	}

	public void setProcResult(String procResult) {
		this.procResult = procResult;
	}


	public String getDutyPeople() {
		return dutyPeople;
	}

	public void setDutyPeople(String dutyPeople) {
		this.dutyPeople = dutyPeople;
	}

	public String getExecPeople() {
		return execPeople;
	}

	public void setExecPeople(String execPeople) {
		this.execPeople = execPeople;
	}

	public Long getPrerepairPlanId() {
		return prerepairPlanId;
	}

	public void setPrerepairPlanId(Long prerepairPlanId) {
		this.prerepairPlanId = prerepairPlanId;
	}

	public String getProcExecPeople() {
		return procExecPeople;
	}

	public void setProcExecPeople(String procExecPeople) {
		this.procExecPeople = procExecPeople;
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

	public String getTooling_dev_flag() {
		return tooling_dev_flag;
	}

	public void setTooling_dev_flag(String tooling_dev_flag) {
		this.tooling_dev_flag = tooling_dev_flag;
	}

	public String getGraphNo() {
		return graphNo;
	}

	public void setGraphNo(String graphNo) {
		this.graphNo = graphNo;
	}
	
	public String getExecResult() {
		if (this.procResult.equals("UNFINISHED")) {
			return "未完成";
		} else if (this.procResult.equals("FINISHED")) {
			return "已完成";
		} else if (this.procResult.equals("CANCEL")) {
			return "取消";
		} else {
			return "转移";
		}
	}

	public String getRepairLevel() {
		return repairLevel;
	}

	public void setRepairLevel(String repairLevel) {
		this.repairLevel = repairLevel;
	}
}
