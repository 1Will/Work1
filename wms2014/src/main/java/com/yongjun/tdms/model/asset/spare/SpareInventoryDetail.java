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

/**
 * <p>Title: SpareInventory
 * <p>Description: 备件盘点明细类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author qs@yj-technology.com
 * @version $Id: SpareInventoryDetail.java 27787 2010-10-14 02:47:51Z zbzhang $
 */
public class SpareInventoryDetail extends BaseInfoEntity {
	private static final long serialVersionUID = 7048020533560805328L;
	private Long currentSysStocks = 0L;		// 当前系统台帐记录库存
	private SpareInventory inventory; 	// 盘点单
	private String code;                //备件编号
	private String name;                //备件名称
	private Double unitPrice=0.0;       //单价
	private String model;               //型号
	private String specification;       //规格
	private String unit;                //单位
	private Long actualNumber;          //实际数量
	private Long inventoryNum;			// 实际盘点数
	private Spare spare; 				// 盘点的备件

	public SpareInventoryDetail() {
		
	}
	
	public long getCurrentSysStocks() {
		return currentSysStocks;
	}
	
	public void setCurrentSysStocks(Long currentSysStocks) {
		this.currentSysStocks = currentSysStocks;
	}

	public SpareInventory getInventory() {
		return inventory;
	}

	public void setInventory(SpareInventory inventory) {
		this.inventory = inventory;
	}

	public long getInventoryNum() {
		return inventoryNum;
	}

	public void setInventoryNum(Long inventoryNum) {
		this.inventoryNum = inventoryNum;
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

	public Long getActualNumber() {
		return actualNumber;
	}

	public void setActualNumber(Long actualNumber) {
		this.actualNumber = actualNumber;
	}

}
