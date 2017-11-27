package com.yongjun.tdms.model.customercontract.contractmanagement.productlist;

import java.util.Date;

import com.yongjun.pluto.model.BaseInfoEntity;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.tdms.model.base.house.House;
import com.yongjun.tdms.model.base.products.Products;
import com.yongjun.tdms.model.customercontract.contractmanagement.ContractManagement;

public class ProductList extends BaseInfoEntity {
	private static final long serialVersionUID = 1L;
	private ContractManagement contractManagement;
	private Products product;
	private CodeValue unit;
	private int count = 1;
	private double unitPrice;
	private double discount = 100.0D;
	private double totalPrice;
	private Date startTime; 
	private int month;
	private Date endTime;
	private String remark;
	private House house;

	public boolean equals(Object arg0) {
		return false;
	}

	public int hashCode() {
		return 0;
	}

	public int getCount() {
		return this.count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public double getDiscount() {
		return this.discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public Products getProduct() {
		return this.product;
	}

	public void setProduct(Products product) {
		this.product = product;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public double getTotalPrice() {
		return this.totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public CodeValue getUnit() {
		return this.unit;
	}

	public void setUnit(CodeValue unit) {
		this.unit = unit;
	}

	public double getUnitPrice() {
		return this.unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public ContractManagement getContractManagement() {
		return this.contractManagement;
	}

	public void setContractManagement(ContractManagement contractManagement) {
		this.contractManagement = contractManagement;
	}

	public Date getStartTime() {
		return startTime;
	}

	public int getMonth() {
		return month;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public House getHouse() {
		return house;
	}

	public void setHouse(House house) {
		this.house = house;
	}
}
