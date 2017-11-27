package com.yongjun.tdms.model.CustomerRelationship.finance;

import java.util.Date;

import com.yongjun.pluto.model.BaseInfoEntity;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.tdms.model.CustomerRelationship.customerProfiles.CustomerInfo;

public class FinanceHistory extends BaseInfoEntity{
	private static final long serialVersionUID = 1L;
	
	private CodeValue financeWay;//融资方式
	private String financeCount;//融资金额
	private Date financeTime;//融资时间
	private CustomerInfo customerInfo;
	
	public CodeValue getFinanceWay() {
		return financeWay;
	}
	public void setFinanceWay(CodeValue financeWay) {
		this.financeWay = financeWay;
	}
	public String getFinanceCount() {
		return financeCount;
	}
	public void setFinanceCount(String financeCount) {
		this.financeCount = financeCount;
	}
	public Date getFinanceTime() {
		return financeTime;
	}
	public void setFinanceTime(Date financeTime) {
		this.financeTime = financeTime;
	}
	public CustomerInfo getCustomerInfo() {
		return customerInfo;
	}
	public void setCustomerInfo(CustomerInfo customerInfo) {
		this.customerInfo = customerInfo;
	}
	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
}
