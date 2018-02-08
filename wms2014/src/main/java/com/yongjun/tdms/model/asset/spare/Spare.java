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
package com.yongjun.tdms.model.asset.spare;

import java.util.HashSet;
import java.util.Set;

import com.yongjun.pluto.model.tracking.CreatedTimeTracking;
import com.yongjun.pluto.model.tracking.CreatorTracking;
import com.yongjun.pluto.model.tracking.LastModifiedTimeTracking;
import com.yongjun.pluto.model.tracking.LastOperatorTracking;
import com.yongjun.tdms.model.BaseInfoEntity;
import com.yongjun.tdms.model.SysModel;
import com.yongjun.tdms.model.asset.device.DeviceCard;
import com.yongjun.tdms.model.asset.device.DeviceSpare;
import com.yongjun.tdms.model.base.codevalue.CodeValue;
import com.yongjun.tdms.model.base.codevalue.SpareDetailType;
import com.yongjun.tdms.model.base.codevalue.SpareType;
import com.yongjun.tdms.model.base.document.ApplicationDoc;
import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.User;
import com.yongjun.tdms.model.prophase.supplier.Supplier;

/**
 * @author qs
 * @version $Id: Spare.java 29430 2010-12-14 07:50:23Z jyang $
 */
public class Spare extends BaseInfoEntity implements CreatorTracking,
		CreatedTimeTracking, LastOperatorTracking, LastModifiedTimeTracking {
	private static final long serialVersionUID = 5708508542780732136L;
	// 备件编号
	private String spareNo;
	// 名称（中文）
	private String name;
	// 名称（英文）
	private String enName;
	// 图号
	private String graphNo;
	//订货号
	private String orderNo;
	// 型号
	private String modelSpecs;
	// 规格
	private String specification;
	// 计量单位
	private CodeValue unit;
	// 采购周期
	private Integer purchaseCycle;
	// 安全库存
	private Long safeStock=0L;
	// 当前库存
	private Long stocks=0L;
	private Long disableStocks=0L;
	//期初库存
	private Long previousStocks;
	// 供应商
	private Supplier supplier;
	// 厂家
	private Supplier factory;
	//厂家 所冗余的字段
	private String factoryStr;
	// 备件分类 
	private SpareType category;

	// 备件类型   
	//private SpareType spareType;
	// 备件明细分类
	private SpareDetailType spareDetailType;

	// 所属资产类型 eq. 工装的或设备的
	private SysModel toolingDevFlag;
	// 产地类型，默认为国内
	private ProducingAreaType producingAreaType = ProducingAreaType.LOCAL;
	// 备件属性，默认为普通备件
	private SparePropertyType propertyType = SparePropertyType.NORMAL;
 	// 申购数量 
	private Integer requestNum;
	// 在途采购数量
	private Integer purchaseNumInRoad;
	// 过去3个月使用量
	private Integer usedNumInPast3M;
	// 未来3个月计划使用量
	private Integer planUseNumInFeature3M;
	//是否非标件
	private boolean tenderPartFlag = false;
	//是否易损件
	private boolean wearingPartFlag = false;
	//是否大修件
	private boolean heavyRepairPartFlag = false;
	// 备注
	private String comment;

	// 旧件标示
	private String oldSpare;

	// 备件和资产关联集合
	private Set<DeviceSpare> spares = new HashSet<DeviceSpare>();
	// 备件文档集合
	private Set<ApplicationDoc> spareDoc = new HashSet<ApplicationDoc>();
	//关联备件库台帐
	private Set<SpareLocation> spareLoc = new HashSet<SpareLocation>();
	// 单价
	private Double unitPrice=0.0;

	
	/**
	 	// 用量
	private Integer usingQty;
	//申购编号
	private String requestBillNo;
		// 原产地
	private String originalHabitat;
	//厂家
	private String factory;
	// 使用单位 [多余]
	private Department department;
    //单位名称 [多余]
	private String deptName;
	//  库位号  [多余]
	private String putPostion;
	//保管员[冗余字段]
	private String custos;
	//保管员
	private User spareCustos;
	// 关联设备 [多余]
	private DeviceCard device;   
	// 备件分类  [多余] 
	private CodeValue category;
	*/
	
	public Spare() {
		
	}
	
	public Integer getPlanUseNumInFeature3M() {
		return planUseNumInFeature3M;
	}

	public void setPlanUseNumInFeature3M(Integer planUseNumInFeature3M) {
		this.planUseNumInFeature3M = planUseNumInFeature3M;
	}

	public Integer getPurchaseNumInRoad() {
		return purchaseNumInRoad;
	}

	public void setPurchaseNumInRoad(Integer purchaseNumInRoad) {
		this.purchaseNumInRoad = purchaseNumInRoad;
	}

	public Integer getRequestNum() {
		return requestNum;
	}

	public void setRequestNum(Integer requestNum) {
		this.requestNum = requestNum;
	}

	public Integer getUsedNumInPast3M() {
		return usedNumInPast3M;
	}

	public void setUsedNumInPast3M(Integer usedNumInPast3M) {
		this.usedNumInPast3M = usedNumInPast3M;
	}

	public ProducingAreaType getProducingAreaType() {
		return producingAreaType;
	}

	public void setProducingAreaType(ProducingAreaType producingAreaType) {
		this.producingAreaType = producingAreaType;
	}

	public String getGraphNo() {
		return graphNo;
	}

	public void setGraphNo(String graphNo) {
		this.graphNo = graphNo;
	}

	public SparePropertyType getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(SparePropertyType propertyType) {
		this.propertyType = propertyType;
	}
	
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}


	public SysModel getToolingDevFlag() {
		return toolingDevFlag;
	}

	public void setToolingDevFlag(SysModel toolingDevFlag) {
		this.toolingDevFlag = toolingDevFlag;
	}

	public SpareType getCategory() {
		return category;
	}
	
	public void setCategory(SpareType category) {
		this.category = category;
	}
	
	public Long getStocks() {
		return stocks;
	}
	
	public void setStocks(Long stocks) {
		this.stocks = stocks;
	}
	
	public String getModelSpecs() {
		return modelSpecs;
	}
	
	public void setModelSpecs(String modelSpecs) {
		this.modelSpecs = modelSpecs;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSpareNo() {
		return spareNo;
	}

	public void setSpareNo(String spareNo) {
		this.spareNo = spareNo;
	}

	@Override
	public int hashCode() {
		return spareNo.hashCode();
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) { return true; }
		if (!(o instanceof Spare)) { return false; }
		return true;
	}

	public String getEnName() {
		return enName;
	}

	public void setEnName(String enName) {
		this.enName = enName;
	}

	public Long getSafeStock() {
		return safeStock;
	}

	public void setSafeStock(Long safeStock) {
		this.safeStock = safeStock;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}


	public Integer getPurchaseCycle() {
		return purchaseCycle;
	}

	public void setPurchaseCycle(Integer purchaseCycle) {
		this.purchaseCycle = purchaseCycle;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Set<DeviceSpare> getSpares() {
		return spares;
	}

	public void setSpares(Set<DeviceSpare> spares) {
		this.spares = spares;
	}

	public Set<ApplicationDoc> getSpareDoc() {
		return spareDoc;
	}

	public void setSpareDoc(Set<ApplicationDoc> spareDoc) {
		this.spareDoc = spareDoc;
	}

	public boolean isHeavyRepairPartFlag() {
		return heavyRepairPartFlag;
	}

	public void setHeavyRepairPartFlag(boolean heavyRepairPartFlag) {
		this.heavyRepairPartFlag = heavyRepairPartFlag;
	}

	public boolean isTenderPartFlag() {
		return tenderPartFlag;
	}

	public void setTenderPartFlag(boolean tenderPartFlag) {
		this.tenderPartFlag = tenderPartFlag;
	}

	public boolean isWearingPartFlag() {
		return wearingPartFlag;
	}

	public void setWearingPartFlag(boolean wearingPartFlag) {
		this.wearingPartFlag = wearingPartFlag;
	}

	public CodeValue getUnit() {
		return unit;
	}

	public void setUnit(CodeValue unit) {
		this.unit = unit;
	}

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	public SpareDetailType getSpareDetailType() {
		return spareDetailType;
	}

	public void setSpareDetailType(SpareDetailType spareDetailType) {
		this.spareDetailType = spareDetailType;
	}


	public Long getPreviousStocks() {
		return previousStocks;
	}

	public Supplier getFactory() {
		return factory;
	}

	public void setFactory(Supplier factory) {
		this.factory = factory;
	}

	public void setPreviousStocks(Long previousStocks) {
		this.previousStocks = previousStocks;
	}

	public Set<SpareLocation> getSpareLoc() {
		return spareLoc;
	}

	public void setSpareLoc(Set<SpareLocation> spareLoc) {
		this.spareLoc = spareLoc;
	}
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getFactoryStr() {
		return factoryStr;
	}

	public void setFactoryStr(String factoryStr) {
		this.factoryStr = factoryStr;
	}

	public String getOldSpare() {
		return oldSpare;
	}

	public void setOldSpare(String oldSpare) {
		this.oldSpare = oldSpare;
	}

	public Long getDisableStocks() {
		return disableStocks;
	}

	public void setDisableStocks(Long disableStocks) {
		this.disableStocks = disableStocks;
	}
	
	
}
