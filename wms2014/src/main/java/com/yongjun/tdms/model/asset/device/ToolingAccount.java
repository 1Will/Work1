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
import com.yongjun.tdms.model.BaseInfoEntity;
import com.yongjun.tdms.model.prophase.business.AcceptBill;

/**
 * @author wzou
 * @version $Id: ToolingAccount.java 27787 2010-10-14 02:47:51Z zbzhang $
 */
public class ToolingAccount extends BaseInfoEntity{

	private static final long serialVersionUID = 5473492911399883250L;
	
	private Long toolingID;			//工装ID
	private String deviceNo;
	private String productString;
	private Double usedQuota;		//	 使用定额
	private String toolingTypeDetailString;	// 工装类型详细
	private Date acceptanceTime;	// 验收日期[工装]
	private String toolingName;		// 工装名称
	private String maker;			// 制造者[工装]
	private String checkerString;	//	 检验员[工装]
	private Date madeTime;			// 制造日期[工装]
	private Date checkTime; 		// 检验日期[工装]
	private String toolingDesignerString;	// [工装]设计人
	private Date completeTime; 		// 完工日期[工装]
	private Date designTime; 		// 设计日期[工装]
	private String putNo;			// 放架编号[工装]
	private Date disabledTime;		// 报废日期[工装]
	private String suggestion;		// 检验员意见[工装]
	private String calUnitString;	// 计量单位[工装]
	private Long totalOutput; 		// 累计产量
	private Date usedStartedTime;	// 工装启用日期
	private Date borrowReturnDateTm; //领用日期
	private String borrowerName;	 //领用人
	private Long produceNum;
	private AcceptBill  acceptBill;//工装台帐所关联的验收单
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

	public Date getAcceptanceTime() {
		return acceptanceTime;
	}

	public void setAcceptanceTime(Date acceptanceTime) {
		this.acceptanceTime = acceptanceTime;
	}

	public String getBorrowerName() {
		return borrowerName;
	}

	public void setBorrowerName(String borrowerName) {
		this.borrowerName = borrowerName;
	}

	public Date getBorrowReturnDateTm() {
		return borrowReturnDateTm;
	}

	public void setBorrowReturnDateTm(Date borrowReturnDateTm) {
		this.borrowReturnDateTm = borrowReturnDateTm;
	}

	public String getCalUnitString() {
		return calUnitString;
	}

	public void setCalUnitString(String calUnitString) {
		this.calUnitString = calUnitString;
	}

	public String getCheckerString() {
		return checkerString;
	}

	public void setCheckerString(String checkerString) {
		this.checkerString = checkerString;
	}

	public Date getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}

	public Date getCompleteTime() {
		return completeTime;
	}

	public void setCompleteTime(Date completeTime) {
		this.completeTime = completeTime;
	}

	public Date getDesignTime() {
		return designTime;
	}

	public void setDesignTime(Date designTime) {
		this.designTime = designTime;
	}

	public String getDeviceNo() {
		return deviceNo;
	}

	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}

	public Date getDisabledTime() {
		return disabledTime;
	}

	public void setDisabledTime(Date disabledTime) {
		this.disabledTime = disabledTime;
	}

	public Date getMadeTime() {
		return madeTime;
	}

	public void setMadeTime(Date madeTime) {
		this.madeTime = madeTime;
	}

	public String getMaker() {
		return maker;
	}

	public void setMaker(String maker) {
		this.maker = maker;
	}

	public Long getProduceNum() {
		return produceNum;
	}

	public void setProduceNum(Long produceNum) {
		this.produceNum = produceNum;
	}

	public String getProductString() {
		return productString;
	}

	public void setProductString(String productString) {
		this.productString = productString;
	}

	public String getPutNo() {
		return putNo;
	}

	public void setPutNo(String putNo) {
		this.putNo = putNo;
	}

	public String getSuggestion() {
		return suggestion;
	}

	public void setSuggestion(String suggestion) {
		this.suggestion = suggestion;
	}

	public String getToolingDesignerString() {
		return toolingDesignerString;
	}

	public void setToolingDesignerString(String toolingDesignerString) {
		this.toolingDesignerString = toolingDesignerString;
	}

	public Long getToolingID() {
		return toolingID;
	}

	public void setToolingID(Long toolingID) {
		this.toolingID = toolingID;
	}

	public String getToolingName() {
		return toolingName;
	}

	public void setToolingName(String toolingName) {
		this.toolingName = toolingName;
	}

	public String getToolingTypeDetailString() {
		return toolingTypeDetailString;
	}

	public void setToolingTypeDetailString(String toolingTypeDetailString) {
		this.toolingTypeDetailString = toolingTypeDetailString;
	}

	public Long getTotalOutput() {
		return totalOutput;
	}

	public void setTotalOutput(Long totalOutput) {
		this.totalOutput = totalOutput;
	}

	public Double getUsedQuota() {
		return usedQuota;
	}

	public void setUsedQuota(Double usedQuota) {
		this.usedQuota = usedQuota;
	}

	public Date getUsedStartedTime() {
		return usedStartedTime;
	}

	public void setUsedStartedTime(Date usedStartedTime) {
		this.usedStartedTime = usedStartedTime;
	}


	
}
