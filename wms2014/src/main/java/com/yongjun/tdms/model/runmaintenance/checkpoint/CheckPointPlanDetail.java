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
package com.yongjun.tdms.model.runmaintenance.checkpoint;

import com.yongjun.pluto.model.tracking.CreatedTimeTracking;
import com.yongjun.pluto.model.tracking.CreatorTracking;
import com.yongjun.pluto.model.tracking.LastModifiedTimeTracking;
import com.yongjun.pluto.model.tracking.LastOperatorTracking;
import com.yongjun.pluto.util.GenerateUUID;
import com.yongjun.tdms.model.BaseInfoEntity;

/**
 * @author qs
 * @version $Id: CheckPointPlanDetail.java 27787 2010-10-14 02:47:51Z zbzhang $
 */
public class CheckPointPlanDetail extends BaseInfoEntity implements CreatorTracking,
CreatedTimeTracking, LastOperatorTracking, LastModifiedTimeTracking {
	private static final long serialVersionUID = -8533107669881861316L;
	private String part;
	private String content;
	private String method;
	private String tool;
	private String rule;
	private String comment;
	private double fee;
	private CheckPointPlan plan;
	private CheckPointRuleDetail ruleDetail;
	
	public CheckPointPlanDetail() {

	}
	
	public double getFee() {
		return fee;
	}
	
	public void setFee(double fee) {
		this.fee = fee;
	}
	
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getPart() {
		return part;
	}

	public void setPart(String part) {
		this.part = part;
	}

	public CheckPointPlan getPlan() {
		return plan;
	}

	public void setPlan(CheckPointPlan plan) {
		this.plan = plan;
	}

	public CheckPointRuleDetail getRuleDetail() {
		return ruleDetail;
	}

	public void setRuleDetail(CheckPointRuleDetail ruleDetail) {
		this.ruleDetail = ruleDetail;
	}

	public String getTool() {
		return tool;
	}

	public void setTool(String tool) {
		this.tool = tool;
	}

	@Override
	public int hashCode() {
		if (null == getId()) {
			return GenerateUUID.getInstance().getUUID().hashCode();
		}
		return  getId().hashCode();
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) { return true; }
		if (!(o instanceof CheckPointPlanDetail)) { return false; }
		return true;
	}

	public void createDetailFromRuleDetail(CheckPointRuleDetail ruleDetail) {
		this.ruleDetail = ruleDetail;
		this.part = ruleDetail.getPart();
		this.content = ruleDetail.getContent();
		this.method = ruleDetail.getMethod();
		this.tool = ruleDetail.getTool();
		this.rule = ruleDetail.getRule();
		this.comment = ruleDetail.getComment();
	}

	public String getRule() {
		return rule;
	}

	public void setRule(String rule) {
		this.rule = rule;
	}
}
