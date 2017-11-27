package com.yongjun.tdms.model.customercontract.contractmanagement.houseList;

import java.util.Date;

import com.yongjun.pluto.model.BaseInfoEntity;
import com.yongjun.tdms.model.base.house.House;
import com.yongjun.tdms.model.customercontract.contractmanagement.ContractManagement;

public class HouseList extends BaseInfoEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ContractManagement contractManagement;
	private House house;
	private Double price;//价格 =房租+综合服务费
	private int month;
	private Double sum;
	private Double rent;//房租
	private Double service;//综合服务费
	private Date startTime; 
	private Date endTime;
	private Double discount;
	private String remark;
	private boolean isuse; 

	@Override
	public boolean equals(Object paramObject) {
		return false;
	}

	@Override
	public int hashCode() {
		return 0;
	}

	public ContractManagement getContractManagement() {
		return contractManagement;
	}

	public House getHouse() {
		return house;
	}

	public Double getPrice() {
		return price;
	}

	public int getMonth() {
		return month;
	}

	public Double getSum() {
		return sum;
	}

	public Date getStartTime() {
		return startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setContractManagement(ContractManagement contractManagement) {
		this.contractManagement = contractManagement;
	}

	public void setHouse(House house) {
		this.house = house;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public void setSum(Double sum) {
		this.sum = sum;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public boolean getIsuse() {
		return isuse;
	}

	public void setIsuse(boolean isuse) {
		this.isuse = isuse;
	}

	public Double getRent() {
		return rent;
	}

	public Double getService() {
		return service;
	}

	public void setRent(Double rent) {
		this.rent = rent;
	}

	public void setService(Double service) {
		this.service = service;
	}

}
