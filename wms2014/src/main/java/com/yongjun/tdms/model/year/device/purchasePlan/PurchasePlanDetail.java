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
package com.yongjun.tdms.model.year.device.purchasePlan;

import java.util.Date;

import com.yongjun.tdms.model.BaseInfoEntity;

/**
 * 
 * <p>Title: PurchasePlanDetail
 * <p>Description: 设备年度采购计划明细类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id: PurchasePlanDetail.java 27787 2010-10-14 02:47:51Z zbzhang $
 */
public class PurchasePlanDetail extends BaseInfoEntity {
	private static final long serialVersionUID = -6629824142242761917L;
	
	//品名
	private String name;
	//规格
	private String specification;
	//型号
	private String model;
	//单价
	private Double unitPrice = 0.0;
	//数量
	private Integer number;
	//总价
	private Double allPrice = 0.0;
	//计划采购日期
	private Date planPurchaseDate;
	//备注
	private String comment;
	//关联的采购计划
	private PurchasePlan purchasePlan;
	
	public PurchasePlanDetail() {}
	@Override
	public int hashCode() {
		return getId().hashCode();
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) {return true;}
		if (!(o instanceof PurchasePlanDetail)) {
			return false;
		}
		return false;
	}

	public Double getAllPrice() {
		return allPrice;
	}

	public void setAllPrice(Double allPrice) {
		this.allPrice = allPrice;
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

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Date getPlanPurchaseDate() {
		return planPurchaseDate;
	}

	public void setPlanPurchaseDate(Date planPurchaseDate) {
		this.planPurchaseDate = planPurchaseDate;
	}

	public PurchasePlan getPurchasePlan() {
		return purchasePlan;
	}

	public void setPurchasePlan(PurchasePlan purchasePlan) {
		this.purchasePlan = purchasePlan;
	}

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

}
