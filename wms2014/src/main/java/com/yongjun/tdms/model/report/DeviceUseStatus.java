/*
 * Copyright (c) 2001-2007 YongJun Technology Pte.,Ltd. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of YongJun
 * Technology Pte.,Ltd. ("Confidential Information"). You shall not disclose
 * such Confidential Information and shall use it only in accordance with the
 * terms of the license agreement you entered Integero with YongJun.
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
 * <p>Description: 设备使用状况月报表类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id: DeviceUseStatus.java 27787 2010-10-14 02:47:51Z zbzhang $
 */
public class DeviceUseStatus extends BaseInfoEntity{
	private static final long serialVersionUID = 2582729595723111631L;
	private String department;			//部门名称
	private String filiale;				//分公司名称
	private CodeValue filaileId;          //分公司ID
	private Double full;				//设备完好率
	private Integer fullAmount;			//完好设备数（台）
	private Integer allAmount;			//总设备数（台）
	private Double using;				//设备利用率
	private Integer useAmount;			//实际开动台时（小时）
	private Integer systemDaisTime ;	//制度台时(时)
	private Double JQusing;				//金切利用率
	private Integer JQuseAmount;		//金切实际开动台时（小时）
	private Integer JQsystemDaisTime ;	//金切制度台时(时)
	
	private String month;				//月份
	private Date date;					//报表生成日期
	public Integer getAllAmount() {
		return allAmount;
	}
	public void setAllAmount(Integer allAmount) {
		this.allAmount = allAmount;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public Double getFull() {
		return full;
	}
	public void setFull(Double full) {
		this.full = full;
	}
	public Integer getFullAmount() {
		return fullAmount;
	}
	public void setFullAmount(Integer fullAmount) {
		this.fullAmount = fullAmount;
	}
	public Integer getJQsystemDaisTime() {
		return JQsystemDaisTime;
	}
	public void setJQsystemDaisTime(Integer qsystemDaisTime) {
		JQsystemDaisTime = qsystemDaisTime;
	}
	public Integer getJQuseAmount() {
		return JQuseAmount;
	}
	public void setJQuseAmount(Integer quseAmount) {
		JQuseAmount = quseAmount;
	}
	public Double getJQusing() {
		return JQusing;
	}
	public void setJQusing(Double qusing) {
		JQusing = qusing;
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
	
	public Integer getSystemDaisTime() {
		return systemDaisTime;
	}
	public void setSystemDaisTime(Integer systemDaisTime) {
		this.systemDaisTime = systemDaisTime;
	}
	public Integer getUseAmount() {
		return useAmount;
	}
	public void setUseAmount(Integer useAmount) {
		this.useAmount = useAmount;
	}
	public Double getUsing() {
		return using;
	}
	public void setUsing(Double using) {
		this.using = using;
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

}
