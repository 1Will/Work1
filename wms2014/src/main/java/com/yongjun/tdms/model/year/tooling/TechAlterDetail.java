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
import com.yongjun.tdms.model.asset.device.DeviceCard;
import com.yongjun.tdms.model.base.codevalue.ToolingType;
import com.yongjun.tdms.model.year.tooling.quarterPlan.QuarterPlan;
import com.yongjun.tdms.model.year.tooling.yearPlan.YearPlan;

/**
 * 
 * <p>Title: YearPlanDao
 * <p>Description: 技术改造明细类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id: TechAlterDetail.java 27787 2010-10-14 02:47:51Z zbzhang $
 */
public class TechAlterDetail extends BaseInfoEntity {
	private static final long serialVersionUID = -4854031336391559344L;
	//计划费用
	private Double planFee = 0.0;
	//计划完成日期
	private Date planFinishedDate;
	//技术改造内容
	private String techAlterContent;
	//原因
	private String reason;
	//备注
	private String comment;
	//工装编号
	private String toolingNo;
	//工装名称
	private String toolingName;
	//工装型号
	private String toolingModel;
	//工装规格
	private String toolingSpecification;
	//工装类别
	private String toolingCategory;
    //列入季度计划 true|false   true表示已列入 默认为未列入
	private boolean createQuarterFlag = false;
	//是否已锁定 [默认解锁]
	private boolean lockedFlag = false;
	//详细类别
	private String detailType;
	//工装
	private DeviceCard tooling;
	//工状类别
	private ToolingType toolingType;
	//关联的年度计划
	private YearPlan yearPlan;
	//关联的季度计划
	private QuarterPlan quarterPlan;
	//季度计划的维修保养明细关联的年度计划的维修保养明细
	private TechAlterDetail yearTechAlterDetail;
	//年度计划的维修保养明细关联的季度计划的维修保养明细
	private Set<TechAlterDetail> quarterTechAlterDetail = new HashSet<TechAlterDetail>();

	public TechAlterDetail() {
		
	}
	
	@Override
	public int hashCode() {
		return getId().hashCode();
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) {return true;}
		if (!(o instanceof TechAlterDetail)) {
			return false;
		}
		return false;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getPlanFinishedDate() {
		return planFinishedDate;
	}

	public void setPlanFinishedDate(Date planFinishedDate) {
		this.planFinishedDate = planFinishedDate;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getTechAlterContent() {
		return techAlterContent;
	}

	public void setTechAlterContent(String techAlterContent) {
		this.techAlterContent = techAlterContent;
	}

	public ToolingType getToolingType() {
		return toolingType;
	}

	public void setToolingType(ToolingType toolingType) {
		this.toolingType = toolingType;
	}

	public YearPlan getYearPlan() {
		return yearPlan;
	}

	public void setYearPlan(YearPlan yearPlan) {
		this.yearPlan = yearPlan;
	}

	public void setPlanFee(Double planFee) {
		this.planFee = planFee;
	}

	public Double getPlanFee() {
		return planFee;
	}

	public QuarterPlan getQuarterPlan() {
		return quarterPlan;
	}

	public void setQuarterPlan(QuarterPlan quarterPlan) {
		this.quarterPlan = quarterPlan;
	}

	public boolean isCreateQuarterFlag() {
		return createQuarterFlag;
	}

	public void setCreateQuarterFlag(boolean createQuarterFlag) {
		this.createQuarterFlag = createQuarterFlag;
	}

	public TechAlterDetail getYearTechAlterDetail() {
		return yearTechAlterDetail;
	}

	public void setYearTechAlterDetail(TechAlterDetail yearTechAlterDetail) {
		this.yearTechAlterDetail = yearTechAlterDetail;
	}

	public Set<TechAlterDetail> getQuarterTechAlterDetail() {
		return quarterTechAlterDetail;
	}

	public void setQuarterTechAlterDetail(
			Set<TechAlterDetail> quarterTechAlterDetail) {
		this.quarterTechAlterDetail = quarterTechAlterDetail;
	}

	public DeviceCard getTooling() {
		return tooling;
	}

	public void setTooling(DeviceCard tooling) {
		this.tooling = tooling;
	}

	public String getToolingCategory() {
		return toolingCategory;
	}

	public void setToolingCategory(String toolingCategory) {
		this.toolingCategory = toolingCategory;
	}

	public String getToolingModel() {
		return toolingModel;
	}

	public void setToolingModel(String toolingModel) {
		this.toolingModel = toolingModel;
	}

	public String getToolingName() {
		return toolingName;
	}

	public void setToolingName(String toolingName) {
		this.toolingName = toolingName;
	}

	public String getToolingNo() {
		return toolingNo;
	}

	public void setToolingNo(String toolingNo) {
		this.toolingNo = toolingNo;
	}

	public String getToolingSpecification() {
		return toolingSpecification;
	}

	public void setToolingSpecification(String toolingSpecification) {
		this.toolingSpecification = toolingSpecification;
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
