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

import com.yongjun.tdms.model.BaseInfoEntity;

/**
 * @author qs
 * @version $Id: QuerySpareHistory.java 27787 2010-10-14 02:47:51Z zbzhang $
 */
public class QuerySpareHistory extends BaseInfoEntity {
	private static final long serialVersionUID = -5039478929338582055L;

	private long number = 0L;

	private String receiver;

	private Date receiveDateTm;

	private String sender;

	private Date sendDateTm;

	private String auditor;

	private Date auditDateTm;

	private boolean inFlag; // 默认为入库

	private Spare spare;

	private SpareInOutBill bill;

	private long maxSpareInOutBill;

	public long getMaxSpareInOutBill() {
		return maxSpareInOutBill;
	}

	public void setMaxSpareInOutBill(long maxSpareInOutBill) {
		this.maxSpareInOutBill = maxSpareInOutBill;
	}

	public QuerySpareHistory(long number, String receiver, Date receiveDateTm,
			String sender, Date sendDateTm, String auditor, Date auditDateTm,
			boolean inFlag, Spare spare, SpareInOutBill bill,
			long maxSpareInOutBill) {
		this.number = number;
		this.receiver = receiver;
		this.receiveDateTm = receiveDateTm;
		this.sender = sender;
		this.sendDateTm = sendDateTm;
		this.auditor = auditor;
		this.auditDateTm = auditDateTm;
		this.inFlag = inFlag;
		this.spare = spare;
		this.bill = bill;
		this.maxSpareInOutBill = maxSpareInOutBill;
	}

	@Override
	public int hashCode() {
		return 0;
	}

	@Override
	public boolean equals(Object arg0) {
		return false;
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

	public SpareInOutBill getBill() {
		return bill;
	}

	public void setBill(SpareInOutBill bill) {
		this.bill = bill;
	}

	public boolean isInFlag() {
		return inFlag;
	}

	public void setInFlag(boolean inFlag) {
		this.inFlag = inFlag;
	}

	public long getNumber() {
		return number;
	}

	public void setNumber(long number) {
		this.number = number;
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
}
