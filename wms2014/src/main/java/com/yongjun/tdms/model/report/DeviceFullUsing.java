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

/**
 * <p>Title: DeviceUseStatus
 * <p>Description: 设备完好利用状况类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id: DeviceFullUsing.java 27787 2010-10-14 02:47:51Z zbzhang $
 */
public class DeviceFullUsing extends BaseInfoEntity{
	private static final long serialVersionUID = 2267049243633050188L;
	
	private Integer itemNo;					//序号
	private String description;				//指标说明
	private String unitIndex;				//计量单位-指标
	private String unitChildItem;			//计量单位-子项
	private String unitMontherItem;			//计量单位-母项
	private Double factIndex;				//本月实际-指标
	private Integer factChildItem;			//本月实际-子项
	private Integer factMontherItem;		//本月实际-母项
	private Double yearIndex;				//本年之累计-指标
	private Integer yearChildItem;			//本年之累计-子项
	private Integer yearMontherItem;		//本年之累计-母项
	private String month;
	private Date date;					//报表生成日期
	

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getFactChildItem() {
		return factChildItem;
	}

	public void setFactChildItem(Integer factChildItem) {
		this.factChildItem = factChildItem;
	}

	public Double getFactIndex() {
		return factIndex;
	}

	public void setFactIndex(Double factIndex) {
		this.factIndex = factIndex;
	}

	public Integer getFactMontherItem() {
		return factMontherItem;
	}

	public void setFactMontherItem(Integer factMontherItem) {
		this.factMontherItem = factMontherItem;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getUnitChildItem() {
		return unitChildItem;
	}

	public void setUnitChildItem(String unitChildItem) {
		this.unitChildItem = unitChildItem;
	}

	public String getUnitIndex() {
		return unitIndex;
	}

	public void setUnitIndex(String unitIndex) {
		this.unitIndex = unitIndex;
	}

	public String getUnitMontherItem() {
		return unitMontherItem;
	}

	public void setUnitMontherItem(String unitMontherItem) {
		this.unitMontherItem = unitMontherItem;
	}

	public Integer getYearChildItem() {
		return yearChildItem;
	}

	public void setYearChildItem(Integer yearChildItem) {
		this.yearChildItem = yearChildItem;
	}

	public Double getYearIndex() {
		return yearIndex;
	}

	public void setYearIndex(Double yearIndex) {
		this.yearIndex = yearIndex;
	}

	public Integer getYearMontherItem() {
		return yearMontherItem;
	}

	public void setYearMontherItem(Integer yearMontherItem) {
		this.yearMontherItem = yearMontherItem;
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

	public Integer getItemNo() {
		return itemNo;
	}

	public void setItemNo(Integer itemNo) {
		this.itemNo = itemNo;
	}

}
