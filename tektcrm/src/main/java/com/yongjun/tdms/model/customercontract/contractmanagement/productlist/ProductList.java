package com.yongjun.tdms.model.customercontract.contractmanagement.productlist;

import java.util.Date;

import com.yongjun.pluto.model.BaseInfoEntity;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.tdms.model.base.products.Products;
import com.yongjun.tdms.model.customercontract.contractmanagement.ContractManagement;

public class ProductList extends BaseInfoEntity {
	private static final long serialVersionUID = 1L;
	private ContractManagement contractManagement;
	private Products product;//产品
	private CodeValue unit;//单位
	private int count = 1;//数量
	private double unitPrice;//单价
	private double discount = 100.0D;//折扣
	private double totalPrice;//总金额
	private String remark;//备注

	private Date plannedDeliveryDate;//计划交付日期
	private CodeValue qualityControl;//质检
	private int deliveryedCount;//已交付数量
	private String proStatuString;//产品生产状态
	private String isExport;//0为不可导（未作修改），1则需要修改，2则需要插入
	
	@Override
	public boolean equals(Object arg0) {
		return false;
	}
	@Override
	public int hashCode() {
		return 0;
	}
	public ContractManagement getContractManagement() {
		return contractManagement;
	}
	public Products getProduct() {
		return product;
	}
	public CodeValue getUnit() {
		return unit;
	}
	public int getCount() {
		return count;
	}
	public double getUnitPrice() {
		return unitPrice;
	}
	public double getDiscount() {
		return discount;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public String getRemark() {
		return remark;
	}
	public Date getPlannedDeliveryDate() {
		return plannedDeliveryDate;
	}
	public CodeValue getQualityControl() {
		return qualityControl;
	}
	public void setContractManagement(ContractManagement contractManagement) {
		this.contractManagement = contractManagement;
	}
	public void setProduct(Products product) {
		this.product = product;
	}
	public void setUnit(CodeValue unit) {
		this.unit = unit;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public void setPlannedDeliveryDate(Date plannedDeliveryDate) {
		this.plannedDeliveryDate = plannedDeliveryDate;
	}
	public void setQualityControl(CodeValue qualityControl) {
		this.qualityControl = qualityControl;
	}
	public int getDeliveryedCount() {
		return deliveryedCount;
	}
	public void setDeliveryedCount(int deliveryedCount) {
		this.deliveryedCount = deliveryedCount;
	}
	public String getProStatuString() {
		return proStatuString;
	}
	public void setProStatuString(String proStatuString) {
		this.proStatuString = proStatuString;
	}
	public String getIsExport() {
		return isExport;
	}
	public void setIsExport(String isExport) {
		this.isExport = isExport;
	}
	
	
}
