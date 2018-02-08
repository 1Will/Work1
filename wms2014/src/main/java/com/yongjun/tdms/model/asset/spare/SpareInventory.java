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

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.yongjun.tdms.model.BaseInfoEntity;

/**
 * <p>Title: SpareInventory
 * <p>Description: 备件盘点类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author qs@yj-technology.com
 * @version $Id: SpareInventory.java 27787 2010-10-14 02:47:51Z zbzhang $
 */
public class SpareInventory extends BaseInfoEntity {
	private static final long serialVersionUID = -3804593424679367417L;
	private String inventoryNo;		// 盘点单号
	private String name;				// 盘点单名称
	private Date inventoryDateTm; // 盘点日期时间
	private String inventoryPeople; // 盘点人 
	private String comment;			// 盘点备注
	private Set<SpareInventoryDetail> inventoryDetails  = new HashSet<SpareInventoryDetail>();
	private Boolean submit = false;					//是否‘提交’标识

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

	public String getInventoryPeople() {
		return inventoryPeople;
	}

	public void setInventoryPeople(String inventoryPeople) {
		this.inventoryPeople = inventoryPeople;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

}
