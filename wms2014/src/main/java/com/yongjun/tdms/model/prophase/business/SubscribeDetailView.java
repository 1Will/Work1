/*
 * Copyright (c) 2001-2007 YongJun Technology Pte.,Ltd. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of YongJun
 * Technology Pte.,Ltd. ("Confidential Information"). You shall not disclose
 * such Confidential Information and shall use it only in accordance with the
 * terms of the license agreement you entered into with YongJun.
 * 
 * YONGJUN MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
 * SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, OR
 * NON-INFRINGEMENT. YONGJUN SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY
 * LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
 * DERIVATIVES.
 */
package com.yongjun.tdms.model.prophase.business;

import java.util.Date;

import com.yongjun.tdms.model.BaseInfoEntity;

/**
 * <p>Title:SubscribeDetailView
 * <p>Description:采购单报表视图类</P>
 * <p>Copyright:Copyright (c) 2008 yj-technology</P>
 * <p>Company:www.yj-technology.com</P>
 * @author yli@yj-technology.com
 * @version $Id: SubscribeDetailView.java 2008-12-15 11:27:20 yli$
 */
public class SubscribeDetailView extends BaseInfoEntity {

	private static final long serialVersionUID = 4276044823723284765L;
	//详细类别
	private String detailType;
	//明细图号
	private String graphNo;
	//明细名称
	private String name;
	//类别
	private String categoryName;
	//明细类别
	private String detailCategoryName;
	//型号
	private String model;
	//规格
	private String specification;
	//
	private Double unitPrice;
	//数量
	private Integer amount;
	//单位
	private String calUnit;
	//总价
	private Double totalPrice;
	//需求日期
	private Date reqDate;
	//需求原因
	private String reqReason;
	//备注
	private String comment;
	//状态
	private String status;
	//部门
	private String department;
	
	private Long departmentId;
	
	private String buyingPerson;
	//申购日期
	private Date subscribeDate;
	//申购单ID
	private Long purchaseBillId;
	
	private String category;
	
	private String orderNo;
	
	private String detailCategory;
	
	private Date requireDate;
	
	private String factory;
	
	private String ownedEquipment;
	
	private String equipmentFactory;
	
	private String buyer;
	
	private String stock;
	//总库存
	private Long stocks;
	//采购数量
	private Integer buyeAmount;
	//到货数量
	private Integer arrivalAmount;
	//实到日期
	private Date arrivalDate;
	//供应商
	private String supplierName;
	private String supplier;
	//采购日期
	private Date purchaseDate;
	//申购理由
	private String beizhu;
	//所属设备总数量
	private String sssbzsl;
	//损坏频次
	private String shpc;
	
	
	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public String getSupplierName() {
		return supplierName;
	}
	
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public Date getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public Integer getArrivalAmount() {
		return arrivalAmount;
	}
	public void setArrivalAmount(Integer arrivalAmount) {
		this.arrivalAmount = arrivalAmount;
	}


	public String getEquipmentFactory() {   
		return equipmentFactory;
	}

	public void setEquipmentFactory(String equipmentFactory) {
		this.equipmentFactory = equipmentFactory;
	}

	public String getOwnedEquipment() {
		return ownedEquipment;
	}

	public void setOwnedEquipment(String ownedEquipment) {
		this.ownedEquipment = ownedEquipment;
	}

	public String getDetailCategory() {
		return detailCategory;
	}

	public Date getRequireDate() {
		return requireDate;
	}

	public void setRequireDate(Date requireDate) {
		this.requireDate = requireDate;
	}

	public void setDetailCategory(String detailCategory) {
		this.detailCategory = detailCategory;
	}

	@Override
	public int hashCode() {
		return 0;
	}

	@Override
	public boolean equals(Object arg0) {
		return false;
	}

	public String getBuyingPerson() {
		return buyingPerson;
	}

	public void setBuyingPerson(String buyingPerson) {
		this.buyingPerson = buyingPerson;
	}

	public String getCalUnit() {
		return calUnit;
	}

	public void setCalUnit(String calUnit) {
		this.calUnit = calUnit;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	

	public String getDetailCategoryName() {
		return detailCategoryName;
	}

	public void setDetailCategoryName(String detailCategoryName) {
		this.detailCategoryName = detailCategoryName;
	}

	public String getDetailType() {
		return detailType;
	}

	public void setDetailType(String detailType) {
		this.detailType = detailType;
	}

	public String getGraphNo() {
		return graphNo;
	}

	public void setGraphNo(String graphNo) {
		this.graphNo = graphNo;
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

	public Long getPurchaseBillId() {
		return purchaseBillId;
	}

	public void setPurchaseBillId(Long purchaseBillId) {
		this.purchaseBillId = purchaseBillId;
	}

	public Date getReqDate() {
		return reqDate;
	}

	public void setReqDate(Date reqDate) {
		this.reqDate = reqDate;
	}

	public String getReqReason() {
		return reqReason;
	}

	public void setReqReason(String reqReason) {
		this.reqReason = reqReason;
	}

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}



	public Date getSubscribeDate() {
		return subscribeDate;
	}

	public void setSubscribeDate(Date subscribeDate) {
		this.subscribeDate = subscribeDate;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getFactory() {
		return factory;
	}

	public void setFactory(String factory) {
		this.factory = factory;
	}

	public String getBuyer() {
		return buyer;
	}

	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStock() {
		return stock;
	}

	public void setStock(String stock) {
		this.stock = stock;
	}

	public Long getStocks() {
		return stocks;
	}

	public void setStocks(Long stocks) {
		this.stocks = stocks;
	}

	public Integer getBuyeAmount() {
		return buyeAmount;
	}

	public void setBuyeAmount(Integer buyeAmount) {
		this.buyeAmount = buyeAmount;
	}

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public String getBeizhu() {
		return beizhu;
	}

	public void setBeizhu(String beizhu) {
		this.beizhu = beizhu;
	}

	public String getShpc() {
		return shpc;
	}

	public void setShpc(String shpc) {
		this.shpc = shpc;
	}

	public String getSssbzsl() {
		return sssbzsl;
	}

	public void setSssbzsl(String sssbzsl) {
		this.sssbzsl = sssbzsl;
	}


}
