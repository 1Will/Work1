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
 * @author zhangshimin
 * @version $Id: ToolingPurchaseBillDetailView.java 28867 2010-11-23 02:55:55Z qcshen $
 */
public class ToolingPurchaseBillDetailView extends BaseInfoEntity{
	private static final long serialVersionUID = 1L;
	//关联采购单Id
	private Long purchaseBillId;
	//图号
	private String graphNo;
	//品名
	private String name;
	//部门
	private String department;
	//类别
	private String categoryName;
	//明细分类 工装明细|备件采购明细|维修保养|技术改造
	private String detailType;
	//明细类别
	private String detailCategoryName;
	//型号
	private String model;
	//规格
	private String specification;
	//数量
	private Integer amount;
	//单位
	private String calUnit;
	//单价
	private Double unitPrice;
	//总价
	private Double allPrice;
	//要求交货日期
	private Date reqDeliveryDate;
	
	private Date actualDeliveryDate;
	//采购日期
	private Date purchaseDate;
	
	//采购单名称
	private String purchaseBillName;
	//采购单明细状态
	private String status;
	//备注
	private String comment;
	
	private String factory;
	
	private String buser;
	
	//收货地址
	private String consigneeAdd;
	//收货人
	private String consigneeName;
	//收货电话
	private String consigneeTel;
	//收货传真
	private String consigneeFax;
	
	private String address;                     //地址
	
	private String postCode;					//邮编
	private String taxId;						//税号
	private String bank;						//开户行
	private String bankAccount;					//银行帐号
	
	private Double total;
	
	private String filialeName;
	
	private String supplierName;
	
	private Double duty;
	
	private String billNo;
	
	private String orderNo;
	
	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public String getFilialeName() {
		return filialeName;
	}

	public void setFilialeName(String filialeName) {
		this.filialeName = filialeName;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
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

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
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
	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	

	public Double getAllPrice() {
		return allPrice;
	}

	public void setAllPrice(Double allPrice) {
		this.allPrice = allPrice;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Long getPurchaseBillId() {
		return purchaseBillId;
	}

	public void setPurchaseBillId(Long purchaseBillId) {
		this.purchaseBillId = purchaseBillId;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	

	public Date getReqDeliveryDate() {
		return reqDeliveryDate;
	}

	public void setReqDeliveryDate(Date reqDeliveryDate) {
		this.reqDeliveryDate = reqDeliveryDate;
	}

	public String getPurchaseBillName() {
		return purchaseBillName;
	}

	public void setPurchaseBillName(String purchaseBillName) {
		this.purchaseBillName = purchaseBillName;
	}

	

	public String getDetailType() {
		return detailType;
	}

	public void setDetailType(String detailType) {
		this.detailType = detailType;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public String getConsigneeAdd() {
		return consigneeAdd;
	}

	public void setConsigneeAdd(String consigneeAdd) {
		this.consigneeAdd = consigneeAdd;
	}

	public String getConsigneeFax() {
		return consigneeFax;
	}

	public void setConsigneeFax(String consigneeFax) {
		this.consigneeFax = consigneeFax;
	}

	public String getConsigneeName() {
		return consigneeName;
	}

	public void setConsigneeName(String consigneeName) {
		this.consigneeName = consigneeName;
	}

	public String getConsigneeTel() {
		return consigneeTel;
	}

	public void setConsigneeTel(String consigneeTel) {
		this.consigneeTel = consigneeTel;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getTaxId() {
		return taxId;
	}

	public void setTaxId(String taxId) {
		this.taxId = taxId;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public Date getActualDeliveryDate() {
		return actualDeliveryDate;
	}

	public void setActualDeliveryDate(Date actualDeliveryDate) {
		this.actualDeliveryDate = actualDeliveryDate;
	}

	public String getFactory() {
		return factory;
	}

	public void setFactory(String factory) {
		this.factory = factory;
	}

	

	public String getBuser() {
		return buser;
	}

	public void setBuser(String buser) {
		this.buser = buser;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Double getDuty() {
		return duty;
	}

	public void setDuty(Double duty) {
		this.duty = duty;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

}
