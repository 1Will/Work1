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
package com.yongjun.tdms.model.asset.spare.Inventory;

import com.yongjun.pluto.model.security.Warehouse;
import com.yongjun.tdms.model.BaseInfoEntity;
import com.yongjun.tdms.model.asset.spare.Location;
import com.yongjun.tdms.model.asset.spare.Spare;
import com.yongjun.tdms.model.asset.spare.SpareLocation;
import com.yongjun.tdms.model.asset.spare.warehouseInfo.regional.Regional;
import com.yongjun.tdms.model.base.codevalue.CodeValue;
import com.yongjun.tdms.model.base.codevalue.SpareDetailType;
import com.yongjun.tdms.model.base.codevalue.SpareType;
import com.yongjun.tdms.model.prophase.supplier.Supplier;

/**
 * <p>Title: SpareInventory
 * <p>Description: 备件盘点明细类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author qs@yj-technology.com
 * @version $Id: SpareInventoryDetail.java 32473 2011-09-27 10:16:59Z zbzhang $
 */
public class SpareInventoryDetail extends BaseInfoEntity {
	private static final long serialVersionUID = 7048020533560805328L;
	private Long currentSysStocks;		// 当前系统台帐记录库存
	private SpareInventory inventory; 	// 盘点单
	private String code;                //备件编号
	private String name;                //备件名称
	private Double unitPrice=0.0;       //单价
	private String model;               //型号
	private String specification;       //规格
	private CodeValue unit;                //单位
	private Long actualNumber;          //盘点前数量
	private Double actualTotalPrice;	//盘点前金额
	private Long inventoryNum;			// 实际盘点数
	private Double inventoryTotalPrice;	//盘点金额
	private Spare spare; 				// 盘点的备件
	private String comment;				//盘点备注
	private Location location;          //盘点单明细所关联的库位
	private String locationCode;        //库位号   
	//private String categoryName;       //备件种类
	private SpareLocation spareLocation;  //备件库
	/**
	 * 仓库级别
	 */
	private CodeValue storageGrade;
	/**
	 * 仓库
	 */
	private Warehouse warehouse;
	/**
	 * 差额
	 */
	private Double marginPrice;
	/**
	 * 差异
	 */
	private Long different;
	/**
	 * 种类 冗余
	 */
	private SpareType category; 
	/**
	 * 明细种类 冗余
	 */
	private SpareDetailType spareDetailType;
	
	/**
	 * 生产厂家 冗余
	 */
	private String factoryName;
	/**
	 * 所属设备 冗余
	 */
	private String equipmentName; 
	 
	/**
	 * 设备厂家 冗余
	 */
	private String equFactoryName;
	/**
	 * 订货号  冗余
	 */
	private String orderNo;
	/**
	 * 库区
	 */
	private Regional regional;
	
	public SpareInventoryDetail() {
		
	}
	

	public SpareInventory getInventory() {
		return inventory;
	}

	public void setInventory(SpareInventory inventory) {
		this.inventory = inventory;
	}



	public Spare getSpare() {
		return spare;
	}

	public void setSpare(Spare spare) {
		this.spare = spare;
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


	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

 

	public CodeValue getUnit() {
		return unit;
	}


	public void setUnit(CodeValue unit) {
		this.unit = unit;
	}


	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Long getActualNumber() {
		return actualNumber;
	}

	public void setActualNumber(Long actualNumber) {
		this.actualNumber = actualNumber;
	}

 

	public Long getInventoryNum() {
		return inventoryNum;
	}

	public void setInventoryNum(Long inventoryNum) {
		this.inventoryNum = inventoryNum;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Double getActualTotalPrice() {
		return actualTotalPrice;
	}

	public void setActualTotalPrice(Double actualTotalPrice) {
		this.actualTotalPrice = actualTotalPrice;
	}

	public Double getInventoryTotalPrice() {
		return inventoryTotalPrice;
	}

	public void setInventoryTotalPrice(Double inventoryTotalPrice) {
		this.inventoryTotalPrice = inventoryTotalPrice;
	}


	public Long getCurrentSysStocks() {
		return currentSysStocks;
	}


	public void setCurrentSysStocks(Long currentSysStocks) {
		this.currentSysStocks = currentSysStocks;
	}


	public Location getLocation() {
		return location;
	}


	public void setLocation(Location location) {
		this.location = location;
	}


	public String getLocationCode() {
		return locationCode;
	}


	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}


	/**
	 * @return the spareLocation
	 */
	public SpareLocation getSpareLocation() {
		return spareLocation;
	}


	/**
	 * @param spareLocation the spareLocation to set
	 */
	public void setSpareLocation(SpareLocation spareLocation) {
		this.spareLocation = spareLocation;
	}
 
	public Double getMarginPrice() {
		return marginPrice;
	}


	public void setMarginPrice(Double marginPrice) {
		this.marginPrice = marginPrice;
	}
 
	public CodeValue getStorageGrade() {
		return storageGrade;
	}


	public void setStorageGrade(CodeValue storageGrade) {
		this.storageGrade = storageGrade;
	}


	public Warehouse getWarehouse() {
		return warehouse;
	}


	public void setWarehouse(Warehouse warehouse) {
		this.warehouse = warehouse;
	}


	public String getEquFactoryName() {
		return equFactoryName;
	}


	public void setEquFactoryName(String equFactoryName) {
		this.equFactoryName = equFactoryName;
	}


	public String getFactoryName() {
		return factoryName;
	}


	public void setFactoryName(String factoryName) {
		this.factoryName = factoryName;
	}


	public String getEquipmentName() {
		return equipmentName;
	}


	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}


	public String getOrderNo() {
		return orderNo;
	}


	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}


	public SpareType getCategory() {
		return category;
	}


	public void setCategory(SpareType category) {
		this.category = category;
	}


	public SpareDetailType getSpareDetailType() {
		return spareDetailType;
	}


	public void setSpareDetailType(SpareDetailType spareDetailType) {
		this.spareDetailType = spareDetailType;
	}


	public Regional getRegional() {
		return regional;
	}


	public void setRegional(Regional regional) {
		this.regional = regional;
	}


	public Long getDifferent() {
		return different;
	}


	public void setDifferent(Long different) {
		this.different = different;
	}


 


	 
 

}
