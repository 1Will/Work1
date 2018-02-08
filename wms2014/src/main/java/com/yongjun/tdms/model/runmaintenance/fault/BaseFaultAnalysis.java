/**
 * 
 */
package com.yongjun.tdms.model.runmaintenance.fault;

import com.yongjun.tdms.model.BaseInfoEntity;

/**
 * @author Administrator
 *
 */
public abstract class BaseFaultAnalysis extends BaseInfoEntity {
	private Integer totalFaultCount;//故障总次数
	private Double totalStopTime;	//停机总时间
	private Double faultFrequency;	//故障频率
	private Double faultStopFrequency;//故障停机率
	private String month;			  //月份
	private UpOrDown upOrDown;
	
	public Double getFaultFrequency() {
		return faultFrequency;
	}
	public void setFaultFrequency(Double faultFrequency) {
		this.faultFrequency = faultFrequency;
	}
	public Double getFaultStopFrequency() {
		return faultStopFrequency;
	}
	public void setFaultStopFrequency(Double faultStopFrequency) {
		this.faultStopFrequency = faultStopFrequency;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public Integer getTotalFaultCount() {
		return totalFaultCount;
	}
	public void setTotalFaultCount(Integer totalFaultCount) {
		this.totalFaultCount = totalFaultCount;
	}
	public Double getTotalStopTime() {
		return totalStopTime;
	}
	public void setTotalStopTime(Double totalStopTime) {
		this.totalStopTime = totalStopTime;
	}
	public UpOrDown getUpOrDown() {
		return upOrDown;
	}
	public void setUpOrDown(UpOrDown upOrDown) {
		this.upOrDown = upOrDown;
	}
}
