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
import java.util.HashSet;
import java.util.Set;

import com.yongjun.tdms.model.BaseInfoEntity;
import com.yongjun.tdms.model.prophase.supplier.Supplier;

/**
 * @author qs
 * @version $Id: PurchaseBill.java 27787 2010-10-14 02:47:51Z zbzhang $
 */
public class PurchaseBill extends BaseInfoEntity {
	private static final long serialVersionUID = 6343266730364781762L;
	private String billNo;
	private String name;
	private Date purchaseDateTm;
	private Supplier supplier;
	private Set<PurchaseBillDetail> purchaseBillDetail =new HashSet<PurchaseBillDetail>();
	
	public PurchaseBill() {
		
	}
	
	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public Date getPurchaseDateTm() {
		return purchaseDateTm;
	}

	public void setPurchaseDateTm(Date purchaseDateTm) {
		this.purchaseDateTm = purchaseDateTm;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<PurchaseBillDetail> getPurchaseBillDetail() {
		return purchaseBillDetail;
	}

	public void setPurchaseBillDetail(Set<PurchaseBillDetail> purchaseBillDetail) {
		this.purchaseBillDetail = purchaseBillDetail;
	}
	
	@Override
	public int hashCode() {
		return billNo.hashCode();
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof PurchaseBill)) {
			return false;
		}
		PurchaseBill pb = (PurchaseBill)o;
		if (this.getBillNo().equals(pb.getBillNo())) {
			return true;
		}
		return false;
	}
}
