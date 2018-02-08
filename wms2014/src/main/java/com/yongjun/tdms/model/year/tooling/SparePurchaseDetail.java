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
package com.yongjun.tdms.model.year.tooling;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.yongjun.tdms.model.BaseInfoEntity;
import com.yongjun.tdms.model.asset.spare.Spare;
import com.yongjun.tdms.model.base.codevalue.CodeValue;
import com.yongjun.tdms.model.base.codevalue.SpareDetailType;
import com.yongjun.tdms.model.year.tooling.quarterPlan.QuarterPlan;
import com.yongjun.tdms.model.year.tooling.yearPlan.YearPlan;

/**
 * 
 * <p>Title: SparePurchaseDetail
 * <p>Description: 备件采购明细类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id: SparePurchaseDetail.java 27787 2010-10-14 02:47:51Z zbzhang $
 */
public class SparePurchaseDetail extends BaseInfoEntity {

	private static final long serialVersionUID = 4506785864206443071L;
	
	//品名
	private String name;
	//规格
	private String specification;
	//型号
	private String model;
	//单价
	private Double unitPrice = 0.0;
	//数量
	private Integer number = 0;
	//总价
	private Double allPrice = 0.0;
	//需求日期
	private Date requestDate;
	//使用工装
	private String usedTooling;
	//需求原因
	private String requestReason;
	//备件详细类别名称
	private String categoryName;
	//备件详细类别名称
	private String detailCategoryName;
	//备注
	private String comment;
    //列入季度计划 true|false   true表示已列入 默认为未列入
	private boolean createQuarterFlag = false;
	//是否已锁定 [默认解锁]
	private boolean lockedFlag = false;
	//详细类别
	private String detailType;
	//备件大类
	//private CodeValue category;
	//备件明细类
	//private SpareDetailType detailCategory;
	//关联的年度计划
	private YearPlan yearPlan;
	//关联的季度计划
	private QuarterPlan quarterPlan;
	//关联备件
	private Spare spare;
	//季度计划的备件采购明细关联的年度计划的备件采购明细
	private SparePurchaseDetail yearSparePurchaseDetail;
	//年度计划的备件采购明细关联的季度计划的备件采购明细
	private Set<SparePurchaseDetail> quarterSparePurchaseDetail = new HashSet<SparePurchaseDetail>();
	
	
	public SparePurchaseDetail() {
		
	}
	
	@Override
	public int hashCode() {
		return getId().hashCode();
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) {return true;}
		if (!(o instanceof SparePurchaseDetail)) {
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

	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	public String getRequestReason() {
		return requestReason;
	}

	public void setRequestReason(String requestReason) {
		this.requestReason = requestReason;
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

	public String getUsedTooling() {
		return usedTooling;
	}

	public void setUsedTooling(String usedTooling) {
		this.usedTooling = usedTooling;
	}

	public YearPlan getYearPlan() {
		return yearPlan;
	}

	public void setYearPlan(YearPlan yearPlan) {
		this.yearPlan = yearPlan;
	}

	public QuarterPlan getQuarterPlan() {
		return quarterPlan;
	}

	public void setQuarterPlan(QuarterPlan quarterPlan) {
		this.quarterPlan = quarterPlan;
	}

	public SparePurchaseDetail getYearSparePurchaseDetail() {
		return yearSparePurchaseDetail;
	}

	public void setYearSparePurchaseDetail(
			SparePurchaseDetail yearSparePurchaseDetail) {
		this.yearSparePurchaseDetail = yearSparePurchaseDetail;
	}

	public boolean isCreateQuarterFlag() {
		return createQuarterFlag;
	}

	public void setCreateQuarterFlag(boolean createQuarterFlag) {
		this.createQuarterFlag = createQuarterFlag;
	}

	public Set<SparePurchaseDetail> getQuarterSparePurchaseDetail() {
		return quarterSparePurchaseDetail;
	}

	public void setQuarterSparePurchaseDetail(
			Set<SparePurchaseDetail> quarterSparePurchaseDetail) {
		this.quarterSparePurchaseDetail = quarterSparePurchaseDetail;
	}

	public String getDetailCategoryName() {
		return detailCategoryName;
	}

	public void setDetailCategoryName(String detailCategoryName) {
		this.detailCategoryName = detailCategoryName;
	}

	public boolean isLockedFlag() {
		return lockedFlag;
	}

	public void setLockedFlag(boolean lockedFlag) {
		this.lockedFlag = lockedFlag;
	}

	public String getDetailType() {
		return detailType;
	}

	public void setDetailType(String detailType) {
		this.detailType = detailType;
	}

	public Spare getSpare() {
		return spare;
	}

	public void setSpare(Spare spare) {
		this.spare = spare;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

}
