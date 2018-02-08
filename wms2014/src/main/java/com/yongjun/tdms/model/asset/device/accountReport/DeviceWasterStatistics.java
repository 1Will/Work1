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

import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.Filiale;
import com.yongjun.tdms.model.BaseInfoEntity;
import com.yongjun.tdms.model.asset.device.DeviceCard;
import com.yongjun.tdms.model.asset.device.DeviceType;
import com.yongjun.tdms.model.prophase.supplier.Supplier;

/**
 * <p>Title: DeviceScrapStatistics
 * <p>Description: 设备报废统计类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wliu@yj-technology.com
 * @version $Id: DeviceWasterStatistics.java 27787 2010-10-14 02:47:51Z zbzhang $
 */
public class DeviceWasterStatistics extends BaseInfoEntity {

	private static final long serialVersionUID = 1L;
	
	private String leader;				//负责人
	private Date scrappedDate;			//报废日期
	private String scrappedReasons;		//报废原因
	private Double originalValue;		//原值
	private Double netValue;			//净值
	private Double curDeprecitionSum;	//累计折旧
	private String month;				//统计月份
	private DeviceCard deviceCard;		//关联设备(显示 设备编号、设备名称、型号、规格)
	
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
	/**
	 * @return the curDeprecitionSum
	 */
	public Double getCurDeprecitionSum() {
		return curDeprecitionSum;
	}
	/**
	 * @param curDeprecitionSum the curDeprecitionSum to set
	 */
	public void setCurDeprecitionSum(Double curDeprecitionSum) {
		this.curDeprecitionSum = curDeprecitionSum;
	}
	/**
	 * @return the deviceCard
	 */
	public DeviceCard getDeviceCard() {
		return deviceCard;
	}
	/**
	 * @param deviceCard the deviceCard to set
	 */
	public void setDeviceCard(DeviceCard deviceCard) {
		this.deviceCard = deviceCard;
	}
	/**
	 * @return the leader
	 */
	public String getLeader() {
		return leader;
	}
	/**
	 * @param leader the leader to set
	 */
	public void setLeader(String leader) {
		this.leader = leader;
	}
	/**
	 * @return the month
	 */
	public String getMonth() {
		return month;
	}
	/**
	 * @param month the month to set
	 */
	public void setMonth(String month) {
		this.month = month;
	}
	/**
	 * @return the netValue
	 */
	public Double getNetValue() {
		return netValue;
	}
	/**
	 * @param netValue the netValue to set
	 */
	public void setNetValue(Double netValue) {
		this.netValue = netValue;
	}
	/**
	 * @return the originalValue
	 */
	public Double getOriginalValue() {
		return originalValue;
	}
	/**
	 * @param originalValue the originalValue to set
	 */
	public void setOriginalValue(Double originalValue) {
		this.originalValue = originalValue;
	}
	/**
	 * @return the scrappedDate
	 */
	public Date getScrappedDate() {
		return scrappedDate;
	}
	/**
	 * @param scrappedDate the scrappedDate to set
	 */
	public void setScrappedDate(Date scrappedDate) {
		this.scrappedDate = scrappedDate;
	}
	/**
	 * @return the scrappedReasons
	 */
	public String getScrappedReasons() {
		return scrappedReasons;
	}
	/**
	 * @param scrappedReasons the scrappedReasons to set
	 */
	public void setScrappedReasons(String scrappedReasons) {
		this.scrappedReasons = scrappedReasons;
	}
	
}
