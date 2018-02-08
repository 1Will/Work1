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
package com.yongjun.tdms.model.prophase.purchase;

import java.util.Date;

import com.yongjun.tdms.model.BaseInfoEntity;

/**
 * @author qs
 * @version $Id: ReceiptBill.java 27787 2010-10-14 02:47:51Z zbzhang $
 */
public class ReceiptBill extends BaseInfoEntity {
	private static final long serialVersionUID = 6639223823693220464L;
	private String billNo;
	private Date receiptDateTm;
	private String comment;
	private PurchaseBill purchaseBill;
	
	public ReceiptBill() {
		
	}
	
	public String getComment() {
		return comment;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}
	
	public PurchaseBill getPurchaseBill() {
		return purchaseBill;
	}

	public void setPurchaseBill(PurchaseBill purchaseBill) {
		this.purchaseBill = purchaseBill;
	}

	public Date getReceiptDateTm() {
		return receiptDateTm;
	}

	public void setReceiptDateTm(Date receiptDateTm) {
		this.receiptDateTm = receiptDateTm;
	}

	@Override
	public int hashCode() {
		return billNo.hashCode();
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof ReceiptBill)) {
			return false;
		}
		ReceiptBill rb = (ReceiptBill)o;
		if (rb.getBillNo().equals(this.getBillNo())) {
			return true;
		}
		return false;
	}

}
