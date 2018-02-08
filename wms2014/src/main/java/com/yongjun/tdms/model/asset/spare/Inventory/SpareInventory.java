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

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.model.security.Warehouse;
import com.yongjun.tdms.model.BaseInfoEntity;
import com.yongjun.tdms.model.base.codevalue.CodeValue;


/**
 * <p>Title: SpareInventory
 * <p>Description: zzb 备件盘点类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author qs@yj-technology.com
 * @version $Id: SpareInventory.java 29778 2010-12-23 04:11:26Z zbzhang $
 */
public class SpareInventory extends BaseInfoEntity {
	private static final long serialVersionUID = -3804593424679367417L;
	private String inventoryNo;		// 盘点单号
	private String name;				// 盘点单名称
	private Date inventoryDateTm; // 盘点日期时间
	private User inventoryPeople; // 盘点人 
 
	private String comment;			// 盘点备注
	//总金额
	private Double totalPrice=0.0;
	private Set<SpareInventoryDetail> inventoryDetails  = new HashSet<SpareInventoryDetail>();
	private Boolean submit = false;					//是否‘提交’标识
	/**
	 * 仓库级别
	 */
	private CodeValue storageGrade;
	/**
	 * 仓库
	 */
	private Warehouse warehouse;
	
	private String oldSpare;
	
	public Boolean getSubmit() {
		return submit;
	}

	public void setSubmit(Boolean submit) {
		this.submit = submit;
	}

	public SpareInventory() {
		
	}
	
	public Set<SpareInventoryDetail> getInventoryDetails() {
		return this.inventoryDetails;
	}
	
	public void setInventoryDetails(Set<SpareInventoryDetail> details) {
		this.inventoryDetails = details;
	}
	
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getInventoryDateTm() {
		return inventoryDateTm;
	}

	public void setInventoryDateTm(Date inventoryDateTm) {
		this.inventoryDateTm = inventoryDateTm;
	}

	public String getInventoryNo() {
		return inventoryNo;
	}

	public void setInventoryNo(String inventoryNo) {
		this.inventoryNo = inventoryNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		return this.inventoryNo.hashCode();
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) { return true;}
		if (o instanceof SpareInventory) { return true; }
		SpareInventory bill = (SpareInventory)o;
		if (bill.getInventoryNo().equals(this.inventoryNo)) {
			return true;
		}
		return false;
	}

	public User getInventoryPeople() {
		return inventoryPeople;
	}

	public void setInventoryPeople(User inventoryPeople) {
		this.inventoryPeople = inventoryPeople;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Override
	public String getDomainModelProperty() {
		// TODO Auto-generated method stub
		return this.getProperties().getProperty("spare_inventory_bill");
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

	public String getOldSpare() {
		return oldSpare;
	}

	public void setOldSpare(String oldSpare) {
		this.oldSpare = oldSpare;
	}

 
}
