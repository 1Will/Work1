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
package com.yongjun.tdms.model.asset.device.accountReport;

import java.util.Date;

import com.yongjun.tdms.model.BaseInfoEntity;
import com.yongjun.tdms.model.asset.device.DeviceType;

/**
 * <p>Title: DeviceOriginalNetAccount
 * <p>Description: 设备原值净值统计类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author yli@yj-technology.com
 * @version $Id: DeviceOriginalNetStatistics.java 27787 2010-10-14 02:47:51Z zbzhang $
 */
public class DeviceOriginalNetStatistics extends BaseInfoEntity {
	
	private static final long serialVersionUID = 1L;

	private Integer deviceNum;			//设备数
	private Double originaValue;		//总原值
	private Double netValue;			//总净值
	private Double curDeprecitionSum;	//累计折旧
	private Double originaOccupancy;	//原值占有率
	private Double netOccupancy;		//净值占有率
	private Date accountDate;			//统计日期
	private String month;				//统计月份
	private DeviceType deviceType;		//关联设备类别
	
	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	public Date getAccountDate() {
		return accountDate;
	}

	public void setAccountDate(Date accountDate) {
		this.accountDate = accountDate;
	}

	public Double getCurDeprecitionSum() {
		return curDeprecitionSum;
	}

	public void setCurDeprecitionSum(Double curDeprecitionSum) {
		this.curDeprecitionSum = curDeprecitionSum;
	}

	public Integer getDeviceNum() {
		return deviceNum;
	}

	public void setDeviceNum(Integer deviceNum) {
		this.deviceNum = deviceNum;
	}

	public DeviceType getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(DeviceType deviceType) {
		this.deviceType = deviceType;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public Double getNetOccupancy() {
		return netOccupancy;
	}

	public void setNetOccupancy(Double netOccupancy) {
		this.netOccupancy = netOccupancy;
	}

	public Double getNetValue() {
		return netValue;
	}

	public void setNetValue(Double netValue) {
		this.netValue = netValue;
	}

	public Double getOriginaOccupancy() {
		return originaOccupancy;
	}

	public void setOriginaOccupancy(Double originaOccupancy) {
		this.originaOccupancy = originaOccupancy;
	}

	public Double getOriginaValue() {
		return originaValue;
	}

	public void setOriginaValue(Double originaValue) {
		this.originaValue = originaValue;
	}

}
