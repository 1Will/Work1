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
package com.yongjun.tdms.presentation.webwork.action.prophase.business;

import java.util.Arrays;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.prophase.business.PayDetail;
import com.yongjun.tdms.model.prophase.business.PurchaseBill;

import com.yongjun.tdms.service.prophase.business.PurchaseBillManager;
import com.yongjun.tdms.service.prophase.business.PurchaseBillPayDetailManager;

/**
 * @author qs
 * @version $Id: EditPurchaseBillPayDetailAction.java 11328 2008-03-15 09:39:30Z mwei $
 */
@SuppressWarnings("serial")
public class EditPurchaseBillPayDetailAction extends PrepareAction {
	private final Log log = LogFactory.getLog(this.getClass());
	private final PurchaseBillManager purchaseBillManager;
	private final PurchaseBillPayDetailManager purchaseBillPayDetailManager;
	private String toolingDevFlag;
	private PurchaseBill purchaseBill;           //获得采购单对象
	private PayDetail payDetail;                 //获得付款明细对象
	
	public EditPurchaseBillPayDetailAction(  PurchaseBillManager purchaseBillManager,PurchaseBillPayDetailManager purchaseBillPayDetailManager) {
		 this.purchaseBillManager=purchaseBillManager;
		this.purchaseBillPayDetailManager=purchaseBillPayDetailManager;
		   
	}
	
	public void prepare() throws Exception {
		if (null == this.purchaseBill) {//获得采购单对象,因为保存明细付款对象的时,同时要保存付款明细所关联的采购单对象
			if (this.hasId("purchaseBill.id")) {
				this.purchaseBill = this.purchaseBillManager.loadPurchaseBill(this.getId("purchaseBill.id"));
				
			} 
		}
		if (null == payDetail) {//获得采购付款明细对象
			if (hasId("payDetail.id")) {
				payDetail = purchaseBillPayDetailManager.loadPayDetail(this.getId("payDetail.id"));
			} else {
				payDetail = new PayDetail();
			}
		} 
		
	}
	
	public String save() {
		boolean isNew = payDetail.isNew();
		payDetail.setPurchaseBill(purchaseBill);
		purchaseBillPayDetailManager.storePayDetail(payDetail);
		if (isNew) {
			addActionMessage(getText("payDetail.add.success"));
					
			return NEW;
		} else {
			addActionMessage(getText("payDetail.edit.success"));
					
			return SUCCESS;
		}
		 
	}

	public PayDetail getPayDetail() {
		return payDetail;
	}

	public void setPayDetail(PayDetail payDetail) {
		this.payDetail = payDetail;
	}

	public PurchaseBill getPurchaseBill() {
		return purchaseBill;
	}

	public void setPurchaseBill(PurchaseBill purchaseBill) {
		this.purchaseBill = purchaseBill;
	}

	public String getToolingDevFlag() {
		return toolingDevFlag;
	}

	public void setToolingDevFlag(String toolingDevFlag) {
		this.toolingDevFlag = toolingDevFlag;
	}
}
