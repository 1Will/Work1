package com.yongjun.tdms.model.year.device.purchasePlan;

import java.util.Date;

import com.yongjun.tdms.model.BaseInfoEntity;

public class PurchasePlanView extends BaseInfoEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5861529460677594624L;
	//部门
	private String deptName;
	//年份
	private String year;
	//品名
	private String name;
	//规格
	private String specification;
	//型号
	private String model;
	//单价
	private Float unitPrice;
	//数量
	private Integer number;
	//总价
	private Float allPrice;
	//计划采购日期
	private Date planPurchaseDate;
	//备注
	private String comment;
	//计划总费用
	//private Float planAllFee;
	
	private Long PurchasePlanId;

	

	public Float getAllPrice() {
		return allPrice;
	}

	public void setAllPrice(Float allPrice) {
		this.allPrice = allPrice;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Date getPlanPurchaseDate() {
		return planPurchaseDate;
	}

	public void setPlanPurchaseDate(Date planPurchaseDate) {
		this.planPurchaseDate = planPurchaseDate;
	}

	public Long getPurchasePlanId() {
		return PurchasePlanId;
	}

	public void setPurchasePlanId(Long purchasePlanId) {
		PurchasePlanId = purchasePlanId;
	}

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	public Float getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Float unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

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

	/*public Float getPlanAllFee() {
		return planAllFee;
	}

	public void setPlanAllFee(Float planAllFee) {
		this.planAllFee = planAllFee;
	}*/
}
