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
package com.yongjun.tdms.model.runmaintenance.lubrication;

import java.util.Date;

import com.yongjun.tdms.model.BaseInfoEntity;
import com.yongjun.tdms.model.asset.device.DeviceCard;
import com.yongjun.tdms.model.base.codevalue.CodeValue;
import com.yongjun.tdms.model.base.lubricationOil.LubricationOil;
import com.yongjun.tdms.model.year.device.purchasePlan.PurchasePlanDetail;

/**
 * <p>Title: LubricationRule
 * <p>Description: 润滑标准类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id: BaseLubrication.java 27787 2010-10-14 02:47:51Z zbzhang $
 */
public abstract class BaseLubrication extends BaseInfoEntity {

	private String position;	// 润滑部位
	///private String content;	// 润滑内容
	private String ruleDsp;	// 润滑标准
	private String methodDsp;	// 润滑方法
	private Integer cycle;		// 润滑周期(单位:天)
	private Date lastLubricationDate;	// 上次润滑日期
	private Double lubricatingOilQty;		// 润滑计量
	private LubricationOil lubricatingOil;	// 润滑油
	private String dutyPeople;	// 责任人
	private DeviceCard device;	// 需润滑的设备
	
	public Integer getCycle() {
		return cycle;
	}

	public void setCycle(Integer cycle) {
		this.cycle = cycle;
	}

	public DeviceCard getDevice() {
		return device;
	}

	public void setDevice(DeviceCard device) {
		this.device = device;
	}

	public String getDutyPeople() {
		return dutyPeople;
	}

	public void setDutyPeople(String dutyPeople) {
		this.dutyPeople = dutyPeople;
	}

	public Date getLastLubricationDate() {
		return lastLubricationDate;
	}

	public void setLastLubricationDate(Date lastLubricationDate) {
		this.lastLubricationDate = lastLubricationDate;
	}

	public LubricationOil getLubricatingOil() {
		return lubricatingOil;
	}

	public void setLubricatingOil(LubricationOil lubricatingOil) {
		this.lubricatingOil = lubricatingOil;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getRuleDsp() {
		return ruleDsp;
	}

	public void setRuleDsp(String ruleDsp) {
		this.ruleDsp = ruleDsp;
	}

	@Override
	public int hashCode() {
		return getId().hashCode();
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) {return true;}
		if (!(o instanceof BaseLubrication)) {
			return false;
		}
		return false;
	}

	public Double getLubricatingOilQty() {
		return lubricatingOilQty;
	}

	public void setLubricatingOilQty(Double lubricatingOilQty) {
		this.lubricatingOilQty = lubricatingOilQty;
	}

	public String getMethodDsp() {
		return methodDsp;
	}

	public void setMethodDsp(String methodDsp) {
		this.methodDsp = methodDsp;
	}
}
