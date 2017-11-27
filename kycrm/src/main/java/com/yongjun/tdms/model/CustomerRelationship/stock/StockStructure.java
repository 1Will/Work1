package com.yongjun.tdms.model.CustomerRelationship.stock;

import com.yongjun.pluto.model.BaseInfoEntity;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.tdms.model.CustomerRelationship.customerProfiles.CustomerInfo;



public class StockStructure extends BaseInfoEntity{
	private static final long serialVersionUID = 1L;
	
	private String stockholderName;//股东名称
	private Double mshare;         //持股比例
	private CodeValue ownership;  //所有权
	private CodeValue contributive;//出资方式
	private CustomerInfo customerName;

	public CustomerInfo getCustomerName() {
		return customerName;
	}

	public void setCustomerName(CustomerInfo customerName) {
		this.customerName = customerName;
	}

	@Override
	public boolean equals(Object arg0) {
		if (arg0 == this) {
			return true;
		}
		if (!(arg0 instanceof StockStructure)) {
			return false;
		}

		StockStructure stock = (StockStructure) arg0;

		if (!stock.getId().equals(getId())) {
			return false;
		}
		return true;
		
	}

	@Override
	public int hashCode() {//地址类
		
		return 0;
	}

	public String getStockholderName() {
		return stockholderName;
	}

	public void setStockholderName(String stockholderName) {
		this.stockholderName = stockholderName;
	}

	public Double getMshare() {
		return mshare;
	}

	public void setMshare(Double mshare) {
		this.mshare = mshare;
	}

	public CodeValue getOwnership() {
		return ownership;
	}

	public void setOwnership(CodeValue ownership) {
		this.ownership = ownership;
	}

	public CodeValue getContributive() {
		return contributive;
	}

	public void setContributive(CodeValue contributive) {
		this.contributive = contributive;
	}



	


	

}
