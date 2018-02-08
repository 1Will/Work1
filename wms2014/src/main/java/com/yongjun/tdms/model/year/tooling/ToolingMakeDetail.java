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
import com.yongjun.tdms.model.base.codevalue.ToolingType;
import com.yongjun.tdms.model.year.tooling.quarterPlan.QuarterPlan;
import com.yongjun.tdms.model.year.tooling.yearPlan.YearPlan;
/**
 * 
 * <p>Title: SparePurchaseDetail
 * <p>Description: 工装制作明细类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id: ToolingMakeDetail.java 27787 2010-10-14 02:47:51Z zbzhang $
 */
public class ToolingMakeDetail extends BaseInfoEntity {
	private static final long serialVersionUID = -4858732396870630614L;
	
	//品名
	private String name;
	//规格
	private String specification;
	//型号
	private String model;
	//工装类别
	private ToolingType category;
	//单价
	private Double unitPrice = 0.0;
	//数量
	private Integer number = 0;
	//总价
	private Double allPrice = 0.0;
	//计划完成日期
	private Date planFinishedDate;
	//需求原因
	private String requestReason;
	//工装类别名称
	private String categoryName;
	//备注
	private String comment;
	//列入季度计划 true|false   true表示已列入 默认未列入
	private boolean createQuarterFlag = false;
	//是否已锁定 [默认解锁]
	private boolean lockedFlag = false;
	//详细类别
	private String detailType;
	//关联的年度计划
	private YearPlan yearPlan;
	//关联的季度计划
	private QuarterPlan quarterPlan;
	//关联的年度计划中的工装制作明细
	private ToolingMakeDetail yearToolingMakeDetail;
    //年度计划的工装制作明细关联的季度计划的工装制作明细
	private Set<ToolingMakeDetail> quarterToolingMakeDetail = new HashSet<ToolingMakeDetail>();
	
	
	public ToolingMakeDetail() {
		
	}
	@Override
	public int hashCode() {
		return getId().hashCode();
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) {return true;}
		if (!(o instanceof ToolingMakeDetail)) {
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

	public ToolingType getCategory() {
		return category;
	}

	public void setCategory(ToolingType category) {
		this.category = category;
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

	public Date getPlanFinishedDate() {
		return planFinishedDate;
	}

	public void setPlanFinishedDate(Date planFinishedDate) {
		this.planFinishedDate = planFinishedDate;
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
	public ToolingMakeDetail getYearToolingMakeDetail() {
		return yearToolingMakeDetail;
	}
	public void setYearToolingMakeDetail(ToolingMakeDetail yearToolingMakeDetail) {
		this.yearToolingMakeDetail = yearToolingMakeDetail;
	}
	public boolean isCreateQuarterFlag() {
		return createQuarterFlag;
	}
	public void setCreateQuarterFlag(boolean createQuarterFlag) {
		this.createQuarterFlag = createQuarterFlag;
	}
	public Set<ToolingMakeDetail> getQuarterToolingMakeDetail() {
		return quarterToolingMakeDetail;
	}
	public void setQuarterToolingMakeDetail(
			Set<ToolingMakeDetail> quarterToolingMakeDetail) {
		this.quarterToolingMakeDetail = quarterToolingMakeDetail;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
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

}
