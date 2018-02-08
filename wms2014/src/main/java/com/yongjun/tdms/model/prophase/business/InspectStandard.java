package com.yongjun.tdms.model.prophase.business;

import com.yongjun.tdms.model.BaseInfoEntity;
@SuppressWarnings("serial")
public class InspectStandard extends BaseInfoEntity {
	
	 //验收内容
	 private String inspectContent;
	 //验收标准
	 private String standard;
	 
	 //验收标准所关联的采购单
	  private PurchaseBill purchaseBill;
	
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

	

	public String getInspectContent() {
		return inspectContent;
	}

	public void setInspectContent(String inspectContent) {
		this.inspectContent = inspectContent;
	}

	public PurchaseBill getPurchaseBill() {
		return purchaseBill;
	}

	public void setPurchaseBill(PurchaseBill purchaseBill) {
		this.purchaseBill = purchaseBill;
	}

	public String getStandard() {
		return standard;
	}

	public void setStandard(String standard) {
		this.standard = standard;
	}

	

}
