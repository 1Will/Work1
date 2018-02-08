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

import com.yongjun.pluto.model.security.Department;
import com.yongjun.tdms.model.BaseInfoEntity;
import com.yongjun.tdms.model.asset.device.DeviceCard;
import com.yongjun.tdms.model.asset.spare.Spare;
import com.yongjun.tdms.model.base.codevalue.CodeValue;
import com.yongjun.tdms.model.base.codevalue.SpareDetailType;
import com.yongjun.tdms.model.base.codevalue.SpareType;
import com.yongjun.tdms.model.base.codevalue.ToolingType;
import com.yongjun.tdms.model.prophase.supplier.Supplier;
import com.yongjun.tdms.model.year.tooling.yearPlan.YearPlanDetailCategory;

/**
 * @author qs
 * @version $Id: PurchaseBillDetail.java 29652 2010-12-20 06:02:15Z zbzhang $
 */
@SuppressWarnings("serial")
public class PurchaseBillDetail extends BaseInfoEntity {
	//图号
	private String graphNo;
	//品名
	private String name;
	//型号
	private String model;
	//规格
	private String specification;
	//数量
	private Integer amount=0;
	//单价 税后
	private Double unitPrice;
	//总价
	private Double totalPrice;
	//预期到货日期  交货日期
	private Date reqDeliveryDate;
	//采购单明细状态
	private PurchaseBillDtlStatus status=PurchaseBillDtlStatus.NEW;
	//采购单所关联的申购单
	private Subscribe subcribe;
	//备注
	private String comment;
	//采购单明细所关联的供应商
	private Supplier supplier;
	//采购单明细所关联的采购单
	private PurchaseBill purchaseBill;
	//采购单明细所关联的申购单明细
	private SubscribeDetail subscribeDetail;
	//申购单所关联的设备管理中的备件台帐
	private Spare spare;
	//采购部门冗余字段
	private String department;
	//采购单明细关联的申购部门
	private Department depart;
	//订货号|备件台帐 所冗余的字段
	private String orderNo;
	//生产厂家
	private Supplier factory;
	//实际到货日期
	private Date actualDeliveryDate=null;
   //到货数量
	private Integer arrivalAmount = 0;
	/*-----------------------新增加的字段------------------------------*/
    //采购单明细的类别[工装制作|备件采购|维修保养|技术改造]
	private YearPlanDetailCategory detailType; 
	//备件分类所冗余的字段
	private String categoryName;
	//备件明细分类所冗余的字段
	private String detailCategoryName;
	//所关联的工装
	private DeviceCard tooling;
	//关联的备件类别
	private SpareType category;
	//备件明细类
	private SpareDetailType detailCategory;
    //工装类别
	private ToolingType toolingCategory;
	//计量单位[工装]
 	private CodeValue calUnit;
 	
 	/**
 	 * 税前价格 zzb
 	 */
 	private Double taxPrice;
 	
 	/**
 	 * 税前总价 zzb
 	 */
 	private Double taxTotalPrice;
 	
	public PurchaseBillDetail() {
		
	}

	public Date getReqDeliveryDate() {
		return reqDeliveryDate;
	}
	
	public void setReqDeliveryDate(Date reqDeliveryDate) {
		this.reqDeliveryDate = reqDeliveryDate;
	}
	
	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
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

	public PurchaseBill getPurchaseBill() {
		return purchaseBill;
	}

	public void setPurchaseBill(PurchaseBill purchaseBill) {
		this.purchaseBill = purchaseBill;
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

	public Subscribe getSubcribe() {
		return subcribe;
	}

	public void setSubcribe(Subscribe subcribe) {
		this.subcribe = subcribe;
	}

	public SubscribeDetail getSubscribeDetail() {
		return subscribeDetail;
	}

	public void setSubscribeDetail(SubscribeDetail subscribeDetail) {
		this.subscribeDetail = subscribeDetail;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public PurchaseBillDtlStatus getStatus() {
		return status;
	}

	public void setStatus(PurchaseBillDtlStatus status) {
		this.status = status;
	}

	public String getExecStatus() {
		if ("unSPECT".equals(status.toString())) {
			return "未验收";
		} else if ("INSPECTED".equals(status.toString())) {
			return "已验收";
		} else  {
			return "新建";
		} 
	}

	public Spare getSpare() {
		return spare;
	}

	public void setSpare(Spare spare) {
		this.spare = spare;
	}

	public String getGraphNo() {
		return graphNo;
	}

	public void setGraphNo(String graphNo) {
		this.graphNo = graphNo;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public SpareType getCategory() {
		return category;
	}

	public void setCategory(SpareType category) {
		this.category = category;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public SpareDetailType getDetailCategory() {
		return detailCategory;
	}

	public void setDetailCategory(SpareDetailType detailCategory) {
		this.detailCategory = detailCategory;
	}

	public String getDetailCategoryName() {
		return detailCategoryName;
	}

	public void setDetailCategoryName(String detailCategoryName) {
		this.detailCategoryName = detailCategoryName;
	}

	public YearPlanDetailCategory getDetailType() {
		return detailType;
	}

	public void setDetailType(YearPlanDetailCategory detailType) {
		this.detailType = detailType;
	}

	public DeviceCard getTooling() {
		return tooling;
	}

	public void setTooling(DeviceCard tooling) {
		this.tooling = tooling;
	}

	public ToolingType getToolingCategory() {
		return toolingCategory;
	}

	public void setToolingCategory(ToolingType toolingCategory) {
		this.toolingCategory = toolingCategory;
	}

	public CodeValue getCalUnit() {
		return calUnit;
	}

	public void setCalUnit(CodeValue calUnit) {
		this.calUnit = calUnit;
	}

	public Date getActualDeliveryDate() {
		return actualDeliveryDate;
	}

	public void setActualDeliveryDate(Date actualDeliveryDate) {
		this.actualDeliveryDate = actualDeliveryDate;
	}

	public Integer getArrivalAmount() {
		return arrivalAmount;
	}

	public void setArrivalAmount(Integer arrivalAmount) {
		this.arrivalAmount = arrivalAmount;
	}

	public Department getDepart() {
		return depart;
	}

	public void setDepart(Department depart) {
		this.depart = depart;
	}

	public Supplier getFactory() {
		return factory;
	}

	public void setFactory(Supplier factory) {
		this.factory = factory;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Double getTaxPrice() {
		return taxPrice;
	}

	public void setTaxPrice(Double taxPrice) {
		this.taxPrice = taxPrice;
	}

	public Double getTaxTotalPrice() {
		return taxTotalPrice;
	}

	public void setTaxTotalPrice(Double taxTotalPrice) {
		this.taxTotalPrice = taxTotalPrice;
	}

}
