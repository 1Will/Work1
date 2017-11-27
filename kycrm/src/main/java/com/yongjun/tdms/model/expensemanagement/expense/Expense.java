package com.yongjun.tdms.model.expensemanagement.expense;

import java.util.Date;

import com.yongjun.pluto.model.BaseInfoEntity;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.tdms.model.CustomerRelationship.customerProfiles.CustomerInfo;

/**
 * 水电网、空调、物业收费单
 */
public class Expense extends BaseInfoEntity{

	private static final long serialVersionUID = 1L;
	private String code;
	private Date startTime;
	private Date endTime;
	private CustomerInfo customerInfo;
	private Double waterMoney;//水费
	private Double electricMoney;//电费
	private Double airMoney;//空调费
	private Double netMoney;//网络费
	private Double propertyMoney;//物业费
	private Double sum;//总金额
	private Double sumSquare;//总面积
	private int sumHouse;//房间数
	private int sumCustomer;//企业数
	private CodeValue state;//状态
	private Expense parentEP;//父类
	
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

	public Double getWaterMoney() {
		return waterMoney;
	}

	public Double getElectricMoney() {
		return electricMoney;
	}

	public Double getAirMoney() {
		return airMoney;
	}

	public Double getNetMoney() {
		return netMoney;
	}

	public Double getPropertyMoney() {
		return propertyMoney;
	}

	public Double getSum() {
		return sum;
	}

	public int getSumHouse() {
		return sumHouse;
	}

	public Double getSumSquare() {
		return sumSquare;
	}

	public int getSumCustomer() {
		return sumCustomer;
	}

	public CodeValue getState() {
		return state;
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

	public void setWaterMoney(Double waterMoney) {
		this.waterMoney = waterMoney;
	}

	public void setElectricMoney(Double electricMoney) {
		this.electricMoney = electricMoney;
	}

	public void setAirMoney(Double airMoney) {
		this.airMoney = airMoney;
	}

	public void setNetMoney(Double netMoney) {
		this.netMoney = netMoney;
	}

	public void setPropertyMoney(Double propertyMoney) {
		this.propertyMoney = propertyMoney;
	}

	public void setSum(Double sum) {
		this.sum = sum;
	}

	public void setSumHouse(int sumHouse) {
		this.sumHouse = sumHouse;
	}

	public void setSumSquare(Double sumSquare) {
		this.sumSquare = sumSquare;
	}

	public void setSumCustomer(int sumCustomer) {
		this.sumCustomer = sumCustomer;
	}

	public void setState(CodeValue state) {
		this.state = state;
	}

	public Expense getParentEP() {
		return parentEP;
	}

	public void setParentEP(Expense parentEP) {
		this.parentEP = parentEP;
	}

}
