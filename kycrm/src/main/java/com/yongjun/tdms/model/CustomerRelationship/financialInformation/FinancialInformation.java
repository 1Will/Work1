package com.yongjun.tdms.model.CustomerRelationship.financialInformation;


import java.util.Date;

import com.yongjun.pluto.model.BaseInfoEntity;
import com.yongjun.tdms.model.CustomerRelationship.customerProfiles.CustomerInfo;

public class FinancialInformation extends BaseInfoEntity {
	private static final long serialVersionUID = 1L;
	private Date year;
	private Double totalAssets;//资产总额
	private Double totalLiabilities;//负债总额
	private Double nearProfit;//净利润
	private Double outputValue ;//产值/主营业收入
	private Double totalTax;//纳税总额
	private CustomerInfo customerName;
	private String isSaved;// 存在并且等于0，，方可提交
	@Override

	public boolean equals(Object arg0) {
		if (arg0 == this) {
			return true;
		}
		if (!(arg0 instanceof FinancialInformation)) {
			return false;
		}

		FinancialInformation contact = (FinancialInformation) arg0;

		if (!contact.getId().equals(getId())) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		return 0;
	}
	public Date getYear() {
		return year;
	}

	public void setYear(Date year) {
		this.year = year;
	}

	public Double getTotalAssets() {
		return totalAssets;
	}

	public void setTotalAssets(Double totalAssets) {
		this.totalAssets = totalAssets;
	}

	public Double getTotalLiabilities() {
		return totalLiabilities;
	}

	public void setTotalLiabilities(Double totalLiabilities) {
		this.totalLiabilities = totalLiabilities;
	}

	public Double getNearProfit() {
		return nearProfit;
	}

	public void setNearProfit(Double nearProfit) {
		this.nearProfit = nearProfit;
	}

	public Double getOutputValue() {
		return outputValue;
	}

	public void setOutputValue(Double outputValue) {
		this.outputValue = outputValue;
	}

	public Double getTotalTax() {
		return totalTax;
	}

	public void setTotalTax(Double totalTax) {
		this.totalTax = totalTax;
	}

	public CustomerInfo getCustomerName() {
		return customerName;
	}

	public void setCustomerName(CustomerInfo customerName) {
		this.customerName = customerName;
	}

	public String getIsSaved() {
		return isSaved;
	}

	public void setIsSaved(String isSaved) {
		this.isSaved = isSaved;
	}

	
	

}
