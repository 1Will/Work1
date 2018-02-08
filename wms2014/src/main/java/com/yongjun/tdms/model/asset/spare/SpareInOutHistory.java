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
package com.yongjun.tdms.model.asset.spare;

import java.util.Date;

import com.yongjun.pluto.model.tracking.CreatedTimeTracking;
import com.yongjun.pluto.model.tracking.CreatorTracking;
import com.yongjun.pluto.model.tracking.LastModifiedTimeTracking;
import com.yongjun.pluto.model.tracking.LastOperatorTracking;
import com.yongjun.tdms.model.BaseInfoEntity;

/**
 * @author qs
 * @version $Id: SpareInOutHistory.java 27787 2010-10-14 02:47:51Z zbzhang $
 */
public class SpareInOutHistory extends BaseInfoEntity implements
		CreatorTracking, CreatedTimeTracking, LastOperatorTracking,
		LastModifiedTimeTracking {
	private static final long serialVersionUID = -5104706200491334487L;
    //数量
	private long number = 0L;
    // 出库人
	private String receiver;
    //出库日期
	private Date receiveDateTm;
    //"默认为当前系统登陆人"
	private String sender;
    //入库日期
	private Date sendDateTm;
    //入库人或者出库人
	private String auditor;
    //确认日期
	private Date auditDateTm;
    //默认为入库
	private boolean inFlag = true; 
    //出入库历史所关联的备件
	private Spare spare;
    //出入库历史所关联的出库/入库单
	private SpareInOutBill bill;
    //暂且没有用到
	private boolean readOnly = false;

	public boolean isReadOnly() {
		return readOnly;
	}

	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
	}

	public SpareInOutHistory() {

	}

	public SpareInOutBill getBill() {
		return bill;
	}

	public void setBill(SpareInOutBill bill) {
		this.bill = bill;
	}

	public Date getAuditDateTm() {
		return auditDateTm;
	}

	public void setAuditDateTm(Date auditDateTm) {
		this.auditDateTm = auditDateTm;
	}

	public String getAuditor() {
		return auditor;
	}

	public void setAuditor(String auditor) {
		this.auditor = auditor;
	}

	public long getNumber() {
		return number;
	}

	public void setNumber(long number) {
		this.number = number;
	}

	public boolean isInFlag() {
		return inFlag;
	}

	public void setInFlag(boolean inFlag) {
		this.inFlag = inFlag;
	}

	public Date getReceiveDateTm() {
		return receiveDateTm;
	}

	public void setReceiveDateTm(Date receiveDateTm) {
		this.receiveDateTm = receiveDateTm;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public Date getSendDateTm() {
		return sendDateTm;
	}

	public void setSendDateTm(Date sendDateTm) {
		this.sendDateTm = sendDateTm;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public Spare getSpare() {
		return spare;
	}

	public void setSpare(Spare spare) {
		this.spare = spare;
	}

	@Override
	public int hashCode() {
		return 0;
	}

	@Override
	public boolean equals(Object arg0) {
		return false;
	}

}
