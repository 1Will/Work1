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

import com.yongjun.tdms.model.BaseInfoEntity;

public class SpareDetailView extends BaseInfoEntity {

	/**
	 * @author xschen
	 * @version $Id: SpareDetailView.java 32109 2011-05-26 01:09:51Z geliang $
	 */
	private static final long serialVersionUID = 1L;
    //备件编号
	private String spareNo;
	//备件名称
	private String spareName;
    //备件英文名称
	private String spareEnName;
	//单位
	private String  unit;
	//型号规格
	private String modelSpecs;
	//种类
	private String category;
	//明细分类
	private String detailType;
	//部门名称
	private String dept;
	//单价
	private Double unitPrice;
	//库存
	private Long stocks;
	//库存
		private Long safeStocks;
	//总价
	private Double totalPrice;
	//库位号
	private String locationCode;
	//备件类别
	private String toolingDevFlag;
	//仓库
	private Long warehouse;
	//仓库名
	private String warehouseName;
	//库区名
	private String regionalName;
	//库区
	private Long regional;
	public String getRegionalName() {
		return regionalName;
	}

	public void setRegionalName(String regionalName) {
		this.regionalName = regionalName;
	}

	public String getWarehouseName() {
		return warehouseName;
	}

	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}

	@Override
	public int hashCode() {
		return 0;
	}

	@Override
	public boolean equals(Object arg0) {
		return false;
	}
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getDetailType() {
		return detailType;
	}

	public void setDetailType(String detailType) {
		this.detailType = detailType;
	}

	public String getModelSpecs() {
		return modelSpecs;
	}

	public void setModelSpecs(String modelSpecs) {
		this.modelSpecs = modelSpecs;
	}

	public String getSpareName() {
		return spareName;
	}

	public void setSpareName(String spareName) {
		this.spareName = spareName;
	}

	public String getSpareNo() {
		return spareNo;
	}

	public void setSpareNo(String spareNo) {
		this.spareNo = spareNo;
	}

	public Long getStocks() {
		return stocks;
	}

	public void setStocks(Long stocks) {
		this.stocks = stocks;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getLocationCode() {
		return locationCode;
	}

	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}

	public String getSpareEnName() {
		return spareEnName;
	}

	public void setSpareEnName(String spareEnName) {
		this.spareEnName = spareEnName;
	}

	public String getToolingDevFlag() {
		return toolingDevFlag;
	}

	public void setToolingDevFlag(String toolingDevFlag) {
		this.toolingDevFlag = toolingDevFlag;
	}

	public Long getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(Long warehouse) {
		this.warehouse = warehouse;
	}

	public Long getRegional() {
		return regional;
	}

	public void setRegional(Long regional) {
		this.regional = regional;
	}

	public Long getSafeStocks() {
		return safeStocks;
	}

	public void setSafeStocks(Long safeStocks) {
		this.safeStocks = safeStocks;
	}
	

}
