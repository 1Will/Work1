package com.yongjun.tdms.presentation.webwork.action.prophase.business;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.prophase.business.InspectStandard;
import com.yongjun.tdms.model.prophase.business.PurchaseBill;
import com.yongjun.tdms.service.prophase.business.PurchaseBillInspectStandardManager;
import com.yongjun.tdms.service.prophase.business.PurchaseBillManager;
public class EditPurchaseBillInspectStandardAction extends PrepareAction{

	/**
	 * 
	 */
	 private final   PurchaseBillInspectStandardManager purchaseBillInspectStandardManager;
	 private final PurchaseBillManager purchaseBillManager;
	 private InspectStandard inspectStandard;//验收标对象准
	 private PurchaseBill purchaseBill;//采购单对象
	
	public  EditPurchaseBillInspectStandardAction(PurchaseBillManager purchaseBillManager,PurchaseBillInspectStandardManager purchaseBillInspectStandardManager){
		this.purchaseBillManager=purchaseBillManager;
		this.purchaseBillInspectStandardManager=purchaseBillInspectStandardManager;
	}
	private static final long serialVersionUID = 1L;

	public void prepare() throws Exception {
		
			if (null == this.purchaseBill) {//获得采购单对象
				if (this.hasId("purchaseBill.id")) {
					this.purchaseBill = this.purchaseBillManager.loadPurchaseBill(this.getId("purchaseBill.id"));
					
				} 
			}
			if (null == inspectStandard) {//获验收单对象
				if (hasId("inspectStandard.id")) {
					inspectStandard = purchaseBillInspectStandardManager.loadInspectStandard(this.getId("inspectStandard.id"));
				} else {
					inspectStandard = new InspectStandard();
				}
			} 
		
	}
	public String save() {
		boolean isNew = inspectStandard.isNew();
		inspectStandard.setPurchaseBill(purchaseBill);
		purchaseBillInspectStandardManager.storeInspectStandard(inspectStandard);
		if (isNew) {
			addActionMessage(getText("inspectStandard.add.success"
					));
			return NEW;
		} else {
			addActionMessage(getText("inspectStandard.edit.success"
				));
			return SUCCESS;
		}
		 
	}
	public InspectStandard getInspectStandard() {
		return inspectStandard;
	}

	public void setInspectStandard(InspectStandard inspectStandard) {
		this.inspectStandard = inspectStandard;
	}

	public PurchaseBill getPurchaseBill() {
		return purchaseBill;
	}

	public void setPurchaseBill(PurchaseBill purchaseBill) {
		this.purchaseBill = purchaseBill;
	}

}
