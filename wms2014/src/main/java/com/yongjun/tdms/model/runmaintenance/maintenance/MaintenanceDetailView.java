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

import com.yongjun.tdms.model.BaseInfoEntity;
import com.yongjun.tdms.model.base.codevalue.CodeValue;

/**
 * <p>Title: CheckPointReportView
 * <p>Description: 点检报告类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author lchen@yj-technology.com
 * @version $Id: MaintenanceDetailView.java 27787 2010-10-14 02:47:51Z zbzhang $
 */
public class MaintenanceDetailView extends BaseInfoEntity{
	
	private static final long serialVersionUID = 1301157406686458287L;
	//月份
	private String month;
	//部门名称
	private String deptName;
	//保养编号
    private String maintenancePlanno;
    //保养名称
    private String maintenancePlanname;
    //设备编号
    private String deviceNo;
    //设备名称
    private String deviceName;
    //型号
    private String model;
    //规格
    private String specification;
    //计划日期
    private Date plandate;
    //实施日期
    private Date actualDate;
    //保养结果，默认为未完成
	private MaintenanceResultType result = MaintenanceResultType.UNFINISHED;
    //责任人
    private String dutypeople;
    //保养类型
    private CodeValue resultType;
    //备注
    private String comment;
    //保养ID
    private Long maintenanceId;
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

	public String getDutypeople() {
		return dutypeople;
	}

	public void setDutypeople(String dutypeople) {
		this.dutypeople = dutypeople;
	}

	public String getMaintenancePlanname() {
		return maintenancePlanname;
	}

	public void setMaintenancePlanname(String maintenancePlanname) {
		this.maintenancePlanname = maintenancePlanname;
	}

	public String getMaintenancePlanno() {
		return maintenancePlanno;
	}

	public void setMaintenancePlanno(String maintenancePlanno) {
		this.maintenancePlanno = maintenancePlanno;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public Date getPlandate() {
		return plandate;
	}

	public void setPlandate(Date plandate) {
		this.plandate = plandate;
	}

	public CodeValue getResultType() {
		return resultType;
	}

	public void setResultType(CodeValue resultType) {
		this.resultType = resultType;
	}

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	public Long getMaintenanceId() {
		return maintenanceId;
	}

	public void setMaintenanceId(Long maintenanceId) {
		this.maintenanceId = maintenanceId;
	}

	public Date getActualDate() {
		return actualDate;
	}

	public void setActualDate(Date actualDate) {
		this.actualDate = actualDate;
	}

	public MaintenanceResultType getResult() {
		return result;
	}

	public void setResult(MaintenanceResultType result) {
		this.result = result;
	}
	public String getmaintenanceResultTxt() {
		return this.result == MaintenanceResultType.UNFINISHED ? "UNFINISHED" : "FINISHED";
	}

}
