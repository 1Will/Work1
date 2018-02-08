package com.yongjun.tdms.model.runmaintenance.calibration;

import java.util.Date;

import com.yongjun.tdms.model.BaseInfoEntity;


public class CalibrationDetailView extends BaseInfoEntity{

	/**
	 * 
	 */
	//月份
	private String month;
	//部门名称
	private String deptName;
	//标定计划编号
	private String calibrationPlanno;
	//标定计划名称
	private String calibrationPlanname;
	//工装编号
	private String deviceNo;
	//工装名称
	private String deviceName;
	//工装图号
	private String graphNo;
	//标定周期
	private String demarcateCycle;
	//责任人
	private String dutypeople;

	//计划标定日期
	private Date planDate;

    //实际标定日期
    private Date actualDate;
    //实施结果
	private String result;
	//标定结果
	private String calibrationResult;
	//标定ID
	private Long calibrationId;
	private static final long serialVersionUID = 1L;

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

	public String getGraphNo() {
		return graphNo;
	}

	public void setGraphNo(String graphNo) {
		this.graphNo = graphNo;
	}
	
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Date getActualDate() {
		return actualDate;
	}

	public void setActualDate(Date actualDate) {
		this.actualDate = actualDate;
	}

	public Long getCalibrationId() {
		return calibrationId;
	}

	public void setCalibrationId(Long calibrationId) {
		this.calibrationId = calibrationId;
	}

	public String getCalibrationPlanname() {
		return calibrationPlanname;
	}

	public void setCalibrationPlanname(String calibrationPlanname) {
		this.calibrationPlanname = calibrationPlanname;
	}

	public String getCalibrationPlanno() {
		return calibrationPlanno;
	}

	public void setCalibrationPlanno(String calibrationPlanno) {
		this.calibrationPlanno = calibrationPlanno;
	}

	public String getDemarcateCycle() {
		return demarcateCycle;
	}

	public void setDemarcateCycle(String demarcateCycle) {
		this.demarcateCycle = demarcateCycle;
	}

	public Date getPlanDate() {
		return planDate;
	}

	public void setPlanDate(Date planDate) {
		this.planDate = planDate;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getCalibrationResult() {
		return calibrationResult;
	}

	public void setCalibrationResult(String calibrationResult) {
		this.calibrationResult = calibrationResult;
	}

	
}
