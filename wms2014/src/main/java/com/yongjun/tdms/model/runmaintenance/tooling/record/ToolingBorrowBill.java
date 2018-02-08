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
package com.yongjun.tdms.model.runmaintenance.tooling.record;

import java.util.Date;

import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.model.tracking.CreatedTimeTracking;
import com.yongjun.pluto.model.tracking.CreatorTracking;
import com.yongjun.pluto.model.tracking.LastModifiedTimeTracking;
import com.yongjun.pluto.model.tracking.LastOperatorTracking;
import com.yongjun.tdms.model.BaseInfoEntity;
import com.yongjun.tdms.model.SysModel;
import com.yongjun.tdms.model.asset.device.DeviceCard;

/**
 * @author qs
 * @version $Id: ToolingBorrowBill.java 27787 2010-10-14 02:47:51Z zbzhang $
 */
public class ToolingBorrowBill extends BaseInfoEntity implements CreatorTracking,
			CreatedTimeTracking, LastOperatorTracking, LastModifiedTimeTracking{
	private static final long serialVersionUID = -485036008129398770L;
	private String billNo;
	private String billName;
	private Date borrowReturnDateTm;		//领用日期
	private Date checkDateTm;	//验收日期
	private Date inDateTm;					//入库日期
	private Long produceNum;				//生产数量
	private String productTailState;		//【工装】产品尾件状况 【设备】 归还设备状况
	private String repairMaintenance;		//【工装】维修保养内容 【设备】 备注
	private boolean returnFlag = false; 	//默认为领用
	private DeviceCard tooling;				//所领用的工装
	private DeviceCard device;				//使用设备
	private Long totalOutput;				//每一次的累计常量
	private User checker;					//验收人
	private User borrower;					//领用人
	private User storeman;					//保管员
	private String repayPeople;				//归还人
	private SysModel toolingDevFlag;    	// 标示为设备或工装
	
	public ToolingBorrowBill() {
		
	}
	
	public User getBorrower() {
		return borrower;
	}

	public void setBorrower(User borrower) {
		this.borrower = borrower;
	}

	public boolean isReturnFlag() {
		return returnFlag;
	}
	
	public void setReturnFlag(boolean returnFlag) {
		this.returnFlag = returnFlag;
	}
	
	public String getBillName() {
		return billName;
	}

	public void setBillName(String billName) {
		this.billName = billName;
	}

	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public Date getBorrowReturnDateTm() {
		return borrowReturnDateTm;
	}

	public void setBorrowReturnDateTm(Date borrowReturnDateTm) {
		this.borrowReturnDateTm = borrowReturnDateTm;
	}

	public Date getCheckDateTm() {
		return checkDateTm;
	}

	public void setCheckDateTm(Date checkDateTm) {
		this.checkDateTm = checkDateTm;
	}

	public User getChecker() {
		return checker;
	}

	public void setChecker(User checker) {
		this.checker = checker;
	}

	public DeviceCard getDevice() {
		return device;
	}

	public void setDevice(DeviceCard device) {
		this.device = device;
	}

	public Date getInDateTm() {
		return inDateTm;
	}

	public void setInDateTm(Date inDateTm) {
		this.inDateTm = inDateTm;
	}

	public Long getProduceNum() {
		return produceNum;
	}

	public void setProduceNum(Long produceNum) {
		this.produceNum = produceNum;
	}

	public String getProductTailState() {
		return productTailState;
	}

	public void setProductTailState(String productTailState) {
		this.productTailState = productTailState;
	}

	public String getRepairMaintenance() {
		return repairMaintenance;
	}

	public void setRepairMaintenance(String repairMaintenance) {
		this.repairMaintenance = repairMaintenance;
	}

	public User getStoreman() {
		return storeman;
	}

	public void setStoreman(User storeman) {
		this.storeman = storeman;
	}

	public DeviceCard getTooling() {
		return tooling;
	}

	public void setTooling(DeviceCard tooling) {
		this.tooling = tooling;
	}

	public String getRepayPeople() {
		return repayPeople;
	}

	public void setRepayPeople(String repayPeople) {
		this.repayPeople = repayPeople;
	}

	@Override
	public int hashCode() {
		return billNo.hashCode();
	}
	
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof ToolingBorrowBill)) { return false; }
		ToolingBorrowBill t = (ToolingBorrowBill)o;
		if (this.billNo.equals(t.getBillNo())) { return true; }
		return false;
	}

	public Long getTotalOutput() {
		return totalOutput;
	}

	public void setTotalOutput(Long totalOutput) {
		this.totalOutput = totalOutput;
	}

	public SysModel getToolingDevFlag() {
		return toolingDevFlag;
	}

	public void setToolingDevFlag(SysModel toolingDevFlag) {
		this.toolingDevFlag = toolingDevFlag;
	}

}
