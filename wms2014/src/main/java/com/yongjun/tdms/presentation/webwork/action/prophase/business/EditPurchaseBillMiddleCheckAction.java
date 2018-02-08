package com.yongjun.tdms.presentation.webwork.action.prophase.business;

import java.util.Arrays;

import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.prophase.business.MiddleCheck;

import com.yongjun.tdms.model.prophase.business.PurchaseBill;
import com.yongjun.tdms.service.prophase.business.PurchaseBillManager;
import com.yongjun.tdms.service.prophase.business.PurchaseBillMiddleCheckManager;

public class EditPurchaseBillMiddleCheckAction extends PrepareAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private MiddleCheck middleCheck; 
	private PurchaseBill  purchaseBill ;
    private final PurchaseBillMiddleCheckManager purchaseBillMiddleCheckManager;
    private final PurchaseBillManager purchaseBillManager;
    public EditPurchaseBillMiddleCheckAction(PurchaseBillMiddleCheckManager purchaseBillMiddleCheckManager,PurchaseBillManager purchaseBillManager){
    	this.purchaseBillMiddleCheckManager=purchaseBillMiddleCheckManager;
    	this.purchaseBillManager=purchaseBillManager;
    }
	public void prepare() throws Exception {
		if (null == this.purchaseBill) {
			if (this.hasId("purchaseBill.id")) {
				this.purchaseBill = this.purchaseBillManager.loadPurchaseBill(getId("purchaseBill.id"));
				
			} 
		}
		if (null == middleCheck) {
			if (hasId("middleCheck.id")) {
				middleCheck = purchaseBillMiddleCheckManager.loadMiddleCheck(this.getId("middleCheck.id"));
			} else {
				middleCheck = new MiddleCheck();
			}
		} 
		
	}
	public String save() {
		boolean isNew = middleCheck.isNew();
		middleCheck.setPurchaseBill(purchaseBill);
		purchaseBillMiddleCheckManager.storeMiddleCheck(middleCheck);
		if (isNew) {
			addActionMessage(getText("middleCheck.add.success", Arrays
					.asList(new Object[] { purchaseBill.getBillNo() })));
			return NEW;
		} else {
			addActionMessage(getText("middleCheck.edit.success", Arrays
					.asList(new Object[] { purchaseBill.getBillNo() })));
			return SUCCESS;
		}
		 
	}
	public MiddleCheck getMiddleCheck() {
		return middleCheck;
	}
	public void setMiddleCheck(MiddleCheck middleCheck) {
		this.middleCheck = middleCheck;
	}
	public PurchaseBill getPurchaseBill() {
		return purchaseBill;
	}
	public void setPurchaseBill(PurchaseBill purchaseBill) {
		this.purchaseBill = purchaseBill;
	}

}
