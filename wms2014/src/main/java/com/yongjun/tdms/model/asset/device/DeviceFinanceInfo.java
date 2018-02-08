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
package com.yongjun.tdms.model.asset.device;

import java.util.Date;

import com.yongjun.pluto.model.tracking.CreatedTimeTracking;
import com.yongjun.pluto.model.tracking.CreatorTracking;
import com.yongjun.pluto.model.tracking.LastModifiedTimeTracking;
import com.yongjun.pluto.model.tracking.LastOperatorTracking;
import com.yongjun.tdms.model.BaseInfoEntity;
import com.yongjun.tdms.model.base.codevalue.CodeValue;
import com.yongjun.tdms.model.base.financeType.FinanceType;

/**
 * @author qs
 * @version $Id: DeviceFinanceInfo.java 27787 2010-10-14 02:47:51Z zbzhang $
 */
public class DeviceFinanceInfo extends BaseInfoEntity implements
		CreatorTracking, CreatedTimeTracking, LastOperatorTracking,
		LastModifiedTimeTracking {
	private static final long serialVersionUID = -1109220056526140858L;
	private Date usedDate;                         //启用时间
	private Double unitPrice;                      //单价
	private Double miscExpenses;                   //安装杂费
	private Double origPrice;		               //设备原值
	private Integer yearLimit;		               //使用年限
	private Double netSalvageScale;                //净残值比率
	private Double yearDeprecitionScale;           //年折旧率
	private Date initDeprecitionDate;              //初始截止折旧时间
	private Double initDeprecitionSum;             //初始截止累计折旧
	private Date curDeprecitionDate;               //当前折旧时间
	private Double curDeprecitionSum;                //当前累计折旧
	private CodeValue foreignCurrencyName;           //外币名称
	private Double foreignCurrencyPrice;           //外币价格
	private Double netValue;                       //净值
	private DeviceCard device;
	private FinanceType financeType;
	
	public DeviceFinanceInfo() {
		
	}
	
	public DeviceCard getDevice() {
		return device;
	}

	public void setDevice(DeviceCard device) {
		this.device = device;
	}

	public Date getCurDeprecitionDate() {
		return curDeprecitionDate;
	}

	public void setCurDeprecitionDate(Date curDeprecitionDate) {
		this.curDeprecitionDate = curDeprecitionDate;
	}

	public FinanceType getFinanceType() {
		return financeType;
	}

	public void setFinanceType(FinanceType financeType) {
		this.financeType = financeType;
	}

	public Date getInitDeprecitionDate() {
		return initDeprecitionDate;
	}

	public void setInitDeprecitionDate(Date initDeprecitionDate) {
		this.initDeprecitionDate = initDeprecitionDate;
	}

	public Double getInitDeprecitionSum() {
		return initDeprecitionSum;
	}

	public void setInitDeprecitionSum(Double initDeprecitionSum) {
		this.initDeprecitionSum = initDeprecitionSum;
	}

	public Double getMiscExpenses() {
		return miscExpenses;
	}

	public void setMiscExpenses(Double miscExpenses) {
		this.miscExpenses = miscExpenses;
	}

	public Double getNetSalvageScale() {
		return netSalvageScale;
	}

	public void setNetSalvageScale(Double netSalvageScale) {
		this.netSalvageScale = netSalvageScale;
	}

	public Double getOrigPrice() {
		return origPrice;
	}

	public void setOrigPrice(Double origPrice) {
		this.origPrice = origPrice;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Date getUsedDate() {
		return usedDate;
	}

	public void setUsedDate(Date usedDate) {
		this.usedDate = usedDate;
	}

	public Double getYearDeprecitionScale() {
		return yearDeprecitionScale;
	}

	public void setYearDeprecitionScale(Double yearDeprecitionScale) {
		this.yearDeprecitionScale = yearDeprecitionScale;
	}

	public Integer getYearLimit() {
		return yearLimit;
	}

	public void setYearLimit(Integer yearLimit) {
		this.yearLimit = yearLimit;
	}
	
	@Override
	public int hashCode() {
		return 0;
	}

	@Override
	public boolean equals(Object o) {
		return false;
	}

	public Double getForeignCurrencyPrice() {
		return foreignCurrencyPrice;
	}

	public void setForeignCurrencyPrice(Double foreignCurrencyPrice) {
		this.foreignCurrencyPrice = foreignCurrencyPrice;
	}

	public Double getNetValue() {
		return netValue;
	}

	public void setNetValue(Double netValue) {
		this.netValue = netValue;
	}

	public CodeValue getForeignCurrencyName() {
		return foreignCurrencyName;
	}

	public void setForeignCurrencyName(CodeValue foreignCurrencyName) {
		this.foreignCurrencyName = foreignCurrencyName;
	}

	public Double getCurDeprecitionSum() {
		return curDeprecitionSum;
	}

	public void setCurDeprecitionSum(Double curDeprecitionSum) {
		this.curDeprecitionSum = curDeprecitionSum;
	}
}
