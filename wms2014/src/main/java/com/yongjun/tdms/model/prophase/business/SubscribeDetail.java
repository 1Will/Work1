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

import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.model.tracking.CreatedTimeTracking;
import com.yongjun.pluto.model.tracking.CreatorTracking;
import com.yongjun.pluto.model.tracking.LastModifiedTimeTracking;
import com.yongjun.pluto.model.tracking.LastOperatorTracking;
import com.yongjun.tdms.model.BaseInfoEntity;
import com.yongjun.tdms.model.asset.device.DeviceCard;
import com.yongjun.tdms.model.asset.spare.Spare;
import com.yongjun.tdms.model.base.codevalue.CodeValue;
import com.yongjun.tdms.model.base.codevalue.SpareDetailType;
import com.yongjun.tdms.model.base.codevalue.SpareType;
import com.yongjun.tdms.model.base.codevalue.ToolingType;
import com.yongjun.tdms.model.prophase.supplier.Supplier;
import com.yongjun.tdms.model.year.tooling.quarterPlan.QuarterPlanDetail;
import com.yongjun.tdms.model.year.tooling.yearPlan.YearPlanDetailCategory;

/**
 * @author qs
 * @version $Id: SubscribeDetail.java 30990 2011-03-09 02:02:11Z zbzhang $
 */
@SuppressWarnings("serial")
public class SubscribeDetail extends BaseInfoEntity implements CreatorTracking,
CreatedTimeTracking, LastOperatorTracking, LastModifiedTimeTracking{
	//图号
	private String graphNo;
	//品名
	private String name;
	//型号
	private String model;
	//规格
	private String specification;
	//建议供应商名称
	private String supplierName;
	//生产厂家
	private Supplier factory;
	//设备厂家
	private Supplier equFactory;
	//生产厂家 所冗余的字段
	private String factoryStr;
	//设备厂家 所冗余的字段
	private String equFactoryStr;
	//数量
	private Integer amount=0;
	//单价
	private Double unitPrice=0.0;
	//采购单转换状态
	private String changePurchaseStatus;
	//总价
	private Double totalPrice=0.0;
	//需求日期  改为 交货日期
	private Date requireDate;
	//需求原因
	private String reqReason;
	//[工装]标识是需求原因[设备]标识是备注
	private String comment;
	//采购人
	private User buyer;
	//采购数量
	private Integer buyeAmount;
	//采购日期
	private Date purchaseDate;
	//到货数量
	private Integer arrivalAmount;
	//到货日期 改为 实到日期
	private Date arrivalDate;
	//申购部门
	private String department;
	//订货号|备件台帐 所冗余的字段
	private String orderNo;
	//所属设备|备件台帐 所冗余的字段
	private String ownedEquipment;
	//设备厂家|备件台帐 所冗余的字段
	private String equipmentFactory;
	
	//备件分类|工装分类 所冗余的字段
	private String categoryName;
	//备件明细分类|工装明细分类|所冗余的字段
	private String detailCategoryName;
	//备件类别
	private SpareType category;
	//备件明细类
	private SpareDetailType detailCategory;
    //工装类别
	private ToolingType toolingCategory;
	//申购单所关联的设备管理中的备件台帐
	private Spare spare;
	//申购单状态
	private SubscribeDetailStatus status=SubscribeDetailStatus.NEW;
	//关联的申购单
	private Subscribe subscribe;
	//关联的申购汇总单
	private SubscribeCollectBill subscribeCollectBill;
	//计量单位[工装]
 	private CodeValue calUnit;
 	
 	
    //季度计划明细的类别[工装制作|备件采购|维修保养|技术改造]
	private YearPlanDetailCategory detailType; 
	//所关联的工装
	private DeviceCard tooling;
	//审购单所关联的季度计划明细
	private QuarterPlanDetail quarterPlanDetail ;
	
	//是否采购
	private boolean stocked = false;
	
	//备注 zzb 2011-03-08
	private String beizhu;
	//所属设备总数量
	private String sssbzsl;
	//损坏频次
	private String shpc;
	
	
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

	public SubscribeDetail() {
		
	}
	
	public String getStatusTxt() {
		return this.status == SubscribeDetailStatus.NEW ? "NEW" : "PURCHASEED";
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


	public Subscribe getSubscribe() {
		return subscribe;
	}

	public void setSubscribe(Subscribe subscribe) {
		this.subscribe = subscribe;
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
		return 0;
	}

	@Override
	public boolean equals(Object arg0) {
		return false;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public Date getRequireDate() {
		return requireDate;
	}

	public void setRequireDate(Date requireDate) {
		this.requireDate = requireDate;
	}

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	public SubscribeDetailStatus getStatus() {
		return status;
	}

	public void setStatus(SubscribeDetailStatus status) {
		this.status = status;
	}

	public String getChangePurchaseStatus() {
		return changePurchaseStatus;
	}

	public void setChangePurchaseStatus(String changePurchaseStatus) {
		this.changePurchaseStatus = changePurchaseStatus;
	}

	public QuarterPlanDetail getQuarterPlanDetail() {
		return quarterPlanDetail;
	}

	public void setQuarterPlanDetail(QuarterPlanDetail quarterPlanDetail) {
		this.quarterPlanDetail = quarterPlanDetail;
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

	public String getReqReason() {
		return reqReason;
	}

	public void setReqReason(String reqReason) {
		this.reqReason = reqReason;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public CodeValue getCalUnit() {
		return calUnit;
	}

	public void setCalUnit(CodeValue calUnit) {
		this.calUnit = calUnit;
	}

	public Integer getArrivalAmount() {
		return arrivalAmount;
	}

	public void setArrivalAmount(Integer arrivalAmount) {
		this.arrivalAmount = arrivalAmount;
	}

	public Date getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public Integer getBuyeAmount() {
		return buyeAmount;
	}

	public void setBuyeAmount(Integer buyeAmount) {
		this.buyeAmount = buyeAmount;
	}

	public User getBuyer() {
		return buyer;
	}

	public void setBuyer(User buyer) {
		this.buyer = buyer;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public Supplier getFactory() {
		return factory;
	}

	public void setFactory(Supplier factory) {
		this.factory = factory;
	}

	public SubscribeCollectBill getSubscribeCollectBill() {
		return subscribeCollectBill;
	}

	public void setSubscribeCollectBill(SubscribeCollectBill subscribeCollectBill) {
		this.subscribeCollectBill = subscribeCollectBill;
	}

	public String getEquipmentFactory() {
		return equipmentFactory;
	}

	public void setEquipmentFactory(String equipmentFactory) {
		this.equipmentFactory = equipmentFactory;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getOwnedEquipment() {
		return ownedEquipment;
	}

	public void setOwnedEquipment(String ownedEquipment) {
		this.ownedEquipment = ownedEquipment;
	}

	public Supplier getEquFactory() {
		return equFactory;
	}

	public void setEquFactory(Supplier equFactory) {
		this.equFactory = equFactory;
	}

	public boolean isStocked() {
		return stocked;
	}

	public void setStocked(boolean stocked) {
		this.stocked = stocked;
	}

	public String getEquFactoryStr() {
		return equFactoryStr;
	}

	public void setEquFactoryStr(String equFactoryStr) {
		this.equFactoryStr = equFactoryStr;
	}

	public String getFactoryStr() {
		return factoryStr;
	}

	public void setFactoryStr(String factoryStr) {
		this.factoryStr = factoryStr;
	}

	public String getBeizhu() {
		return beizhu;
	}

	public void setBeizhu(String beizhu) {
		this.beizhu = beizhu;
	}

//	public boolean isCallFlag() {
//		return callFlag;
//	}
//
//	public void setCallFlag(boolean callFlag) {
//		this.callFlag = callFlag;
//	}
}
