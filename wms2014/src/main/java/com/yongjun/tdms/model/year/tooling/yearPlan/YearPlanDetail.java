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
package com.yongjun.tdms.model.year.tooling.yearPlan;

import java.util.Date;

import com.yongjun.tdms.model.BaseInfoEntity;
import com.yongjun.tdms.model.asset.device.DeviceCard;
import com.yongjun.tdms.model.asset.spare.Spare;
import com.yongjun.tdms.model.base.codevalue.CodeValue;
import com.yongjun.tdms.model.base.codevalue.SpareDetailType;
import com.yongjun.tdms.model.base.codevalue.SpareType;
import com.yongjun.tdms.model.base.codevalue.ToolingType;

/**
 * <p>Title: YearPlanDetail
 * <p>Description: 年度计划明细类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $
 */
public class YearPlanDetail extends BaseInfoEntity {
	private static final long serialVersionUID = 1988686682233433251L;
	//年度计划明细的类别[工装制作|备件采购|维修保养|技术改造]
	private YearPlanDetailCategory detailType;
	//图号
	private String graphNo;
	//名称
	private String name;
	//类别
	private String categoryName;
	//明细类别
	private String detailCategoryName;
	//规格
	private String specification;
	//型号
	private String model;
	//单价
	private Double unitPrice = 0.0;
	//数量
	private Integer number = 0;
	//总费用
	private Double allPrice = 0.0;
	//需求日期
	private Date requestDate;
	//需求原因
	private String requestReason;
    //列入季度计划 true|false   true表示已列入 默认为未列入
	private boolean createQuarterFlag = false;
	//备注
	private String comment;
	//是否已锁定 [默认解锁]
	private boolean lockedFlag = false;
	//工装类别
	private ToolingType toolingCategory;
	//关联的年度计划
	private YearPlan yearPlan;
	//关联备件
	private Spare spare;
	//关联的工装
	private DeviceCard tooling;
	//关联的备件类别
	private SpareType category;
	//备件明细类
	private SpareDetailType detailCategory;
	//计量单位[工装]
 	private CodeValue calUnit;
	@Override
	public int hashCode() {
		return getId().hashCode();
	}

	public YearPlanDetail() {
		
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == this) {return true;}
		if (o instanceof YearPlanDetail) {
			return true;
		}
		return false;
	}

	public Double getAllPrice() {
		return allPrice;
	}

	public void setAllPrice(Double allPrice) {
		this.allPrice = allPrice;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public boolean isCreateQuarterFlag() {
		return createQuarterFlag;
	}

	public void setCreateQuarterFlag(boolean createQuarterFlag) {
		this.createQuarterFlag = createQuarterFlag;
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

	public String getGraphNo() {
		return graphNo;
	}

	public void setGraphNo(String graphNo) {
		this.graphNo = graphNo;
	}

	public boolean isLockedFlag() {
		return lockedFlag;
	}

	public void setLockedFlag(boolean lockedFlag) {
		this.lockedFlag = lockedFlag;
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

	public Spare getSpare() {
		return spare;
	}

	public void setSpare(Spare spare) {
		this.spare = spare;
	}

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	public ToolingType getToolingCategory() {
		return toolingCategory;
	}

	public void setToolingCategory(ToolingType toolingCategory) {
		this.toolingCategory = toolingCategory;
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

	public DeviceCard getTooling() {
		return tooling;
	}

	public void setTooling(DeviceCard tooling) {
		this.tooling = tooling;
	}

	public SpareType getCategory() {
		return category;
	}

	public void setCategory(SpareType category) {
		this.category = category;
	}

	public SpareDetailType getDetailCategory() {
		return detailCategory;
	}

	public void setDetailCategory(SpareDetailType detailCategory) {
		this.detailCategory = detailCategory;
	}

	public CodeValue getCalUnit() {
		return calUnit;
	}

	public void setCalUnit(CodeValue calUnit) {
		this.calUnit = calUnit;
	}

}
