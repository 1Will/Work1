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
package com.yongjun.tdms.model.report;

import java.util.Date;

import com.yongjun.tdms.model.BaseInfoEntity;
import com.yongjun.tdms.model.base.codevalue.CodeValue;

/**
 * <p>Title: DeviceUseStatus
 * <p>Description: 设备使用状况类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id: MainProductDeviceCheck.java 27787 2010-10-14 02:47:51Z zbzhang $
 */
public class MainProductDeviceCheck extends BaseInfoEntity{
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//部门名称
	private String department;  
	//分公司
	private String filiale;
	 //设备总数
	private Integer deviceTotal; 
	 //完好设备数
    private Integer fullDeviceAmount;  
    //带病设备数
    private Integer unFullDeviceAmount;
    //停机代修台数
    private Integer stopUseAmount;    
    // 设备完好率 
    private Double  deviceFullModulus;    
    // 实动台数
    private Integer useAmount;  
    //封存台数
    private Integer  keepAmount;          
    //使用台时(时)
    private Integer useDaisTime;      
    //制度台时(时)
    private Integer systemDaisTime;   
    //维修工时(时)
    private Integer maintainDate;  
    // 故障停机台时(时)
    private Integer faultStopDate; 
    //设备利用率
    private Double deviceUseModulus;  
    //设备有效利用率
    private Double deviceEffectModulus; 
    //故障停机率
    private Double faultStopModulus; 
    //金切实际开动台时（小时）
    private Integer JQuseAmount;	
     //金切制度台时(时)
	private Integer JQsystemDaisTime ;	
    //金切利用率
    private Double JQusing;	
    //计划保养台数
    private Integer planMaintainAmount; 
   //实际保养台数
    private Integer practiceMaintainAmount;
    //核算期
    private Date figureDate;
     //年份
    private String year;	
     //月份
	private String month;
    //报表日期
	private Date reportDate;	
    //单位责任人
	private String manager;		
	//制表人
	//	分公司ID
	private CodeValue filaileId;  
	private String marker;	
	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public Integer getDeviceTotal() {
		return deviceTotal;
	}

	public void setDeviceTotal(Integer deviceTotal) {
		this.deviceTotal = deviceTotal;
	}

	public Integer getFullDeviceAmount() {
		return fullDeviceAmount;
	}

	public void setFullDeviceAmount(Integer fullDeviceAmount) {
		this.fullDeviceAmount = fullDeviceAmount;
	}

	public Integer getUnFullDeviceAmount() {
		return unFullDeviceAmount;
	}

	public void setUnFullDeviceAmount(Integer unFullDeviceAmount) {
		this.unFullDeviceAmount = unFullDeviceAmount;
	}

	public Integer getStopUseAmount() {
		return stopUseAmount;
	}

	public void setStopUseAmount(Integer stopUseAmount) {
		this.stopUseAmount = stopUseAmount;
	}

	public Double getDeviceUseModulus() {
		return deviceUseModulus;
	}

	public void setDeviceUseModulus(Double deviceUseModulus) {
		this.deviceUseModulus = deviceUseModulus;
	}

	public Integer getUseAmount() {
		return useAmount;
	}

	public void setUseAmount(Integer useAmount) {
		this.useAmount = useAmount;
	}

	public Integer getKeepAmount() {
		return keepAmount;
	}

	public void setKeepAmount(Integer keepAmount) {
		this.keepAmount = keepAmount;
	}

	public Integer getUseDaisTime() {
		return useDaisTime;
	}

	public void setUseDaisTime(Integer useDaisTime) {
		this.useDaisTime = useDaisTime;
	}

	public Integer getSystemDaisTime() {
		return systemDaisTime;
	}

	public void setSystemDaisTime(Integer systemDaisTime) {
		this.systemDaisTime = systemDaisTime;
	}

	public Integer getMaintainDate() {
		return maintainDate;
	}

	public void setMaintainDate(Integer maintainDate) {
		this.maintainDate = maintainDate;
	}

	public Integer getFaultStopDate() {
		return faultStopDate;
	}

	public void setFaultStopDate(Integer faultStopDate) {
		this.faultStopDate = faultStopDate;
	}

	public Double getDeviceFullModulus() {
		return deviceFullModulus;
	}

	public void setDeviceFullModulus(Double deviceFullModulus) {
		this.deviceFullModulus = deviceFullModulus;
	}

	public Double getDeviceEffectModulus() {
		return deviceEffectModulus;
	}

	public void setDeviceEffectModulus(Double deviceEffectModulus) {
		this.deviceEffectModulus = deviceEffectModulus;
	}

	public Double getFaultStopModulus() {
		return faultStopModulus;
	}

	public void setFaultStopModulus(Double faultStopModulus) {
		this.faultStopModulus = faultStopModulus;
	}

	public Integer getPlanMaintainAmount() {
		return planMaintainAmount;
	}

	public void setPlanMaintainAmount(Integer planMaintainAmount) {
		this.planMaintainAmount = planMaintainAmount;
	}

	public Integer getPracticeMaintainAmount() {
		return practiceMaintainAmount;
	}

	public void setPracticeMaintainAmount(Integer practiceMaintainAmount) {
		this.practiceMaintainAmount = practiceMaintainAmount;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}
	
	public String getStandardMonth() {
		String [] splitedMonth = month.split("-");
		return splitedMonth[0] + "年" + splitedMonth[1] + "月";
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getMarker() {
		return marker;
	}

	public void setMarker(String marker) {
		this.marker = marker;
	}

	public Date getReportDate() {
		return reportDate;
	}

	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}

	public Date getFigureDate() {
		return figureDate;
	}

	public void setFigureDate(Date figureDate) {
		this.figureDate = figureDate;
	}

	public Integer getJQuseAmount() {
		return JQuseAmount;
	}

	public void setJQuseAmount(Integer quseAmount) {
		JQuseAmount = quseAmount;
	}

	public Integer getJQsystemDaisTime() {
		return JQsystemDaisTime;
	}

	public void setJQsystemDaisTime(Integer qsystemDaisTime) {
		JQsystemDaisTime = qsystemDaisTime;
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

	public Double getJQusing() {
		return JQusing;
	}

	public void setJQusing(Double qusing) {
		JQusing = qusing;
	}

	public String getFiliale() {
		return filiale;
	}

	public void setFiliale(String filiale) {
		this.filiale = filiale;
	}

	public CodeValue getFilaileId() {
		return filaileId;
	}

	public void setFilaileId(CodeValue filaileId) {
		this.filaileId = filaileId;
	}
	
	public String getFilaileName(){
		if(getFilaileId()!=null){
			return getFilaileId().getValue();
		}
		return "";
	}
}
