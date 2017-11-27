package com.yongjun.tdms.model.expensemanagement.expense;

import java.util.Date;

import com.yongjun.pluto.model.BaseInfoEntity;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.tdms.model.CustomerRelationship.customerProfiles.CustomerInfo;

public class Rent extends BaseInfoEntity {

	/**
	 * 房租信息
	 */
	private static final long serialVersionUID = 6468930744244087900L;
	private String code;
	private Date startTime;
	private Date endTime;
	private CustomerInfo customerInfo;//客户
	private Double hasSum;//已交金额
	private Double sum;//总金额
	private Double sumSquare;//总面积
	private int sumHouse;//房间数
	private int sumCustomer;//企业数
	private CodeValue state;//状态
	private Rent parentRent;//父类
	
	@Override
	public boolean equals(Object arg0) {
		return false;
	}

	@Override
	public int hashCode() {
		return 0;
	}

	public String getCode() {
		return code;
	}

	public Date getStartTime() {
		return startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public CustomerInfo getCustomerInfo() {
		return customerInfo;
	}

	public Double getSum() {
		return sum;
	}

	public Double getSumSquare() {
		return sumSquare;
	}

	public int getSumHouse() {
		return sumHouse;
	}

	public int getSumCustomer() {
		return sumCustomer;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public void setCustomerInfo(CustomerInfo customerInfo) {
		this.customerInfo = customerInfo;
	}

	public void setSum(Double sum) {
		this.sum = sum;
	}

	public void setSumSquare(Double sumSquare) {
		this.sumSquare = sumSquare;
	}

	public void setSumHouse(int sumHouse) {
		this.sumHouse = sumHouse;
	}

	public void setSumCustomer(int sumCustomer) {
		this.sumCustomer = sumCustomer;
	}

	public CodeValue getState() {
		return state;
	}

	public void setState(CodeValue state) {
		this.state = state;
	}

	public Rent getParentRent() {
		return parentRent;
	}

	public void setParentRent(Rent parentRent) {
		this.parentRent = parentRent;
	}

	public Double getHasSum() {
		return hasSum;
	}

	public void setHasSum(Double hasSum) {
		this.hasSum = hasSum;
	}

}
