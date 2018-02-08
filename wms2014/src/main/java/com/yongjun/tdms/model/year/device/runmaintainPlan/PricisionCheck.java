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
package com.yongjun.tdms.model.year.device.runmaintainPlan;

import com.yongjun.tdms.model.BaseInfoEntity;

/**
 * 
 * <p>Title: PricisionCheck
 * <p>Description: 运维计划的精度检查类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id: PricisionCheck.java 27787 2010-10-14 02:47:51Z zbzhang $
 */
public class PricisionCheck extends BaseInfoEntity {
	private static final long serialVersionUID = 1832128443013306702L;
	
	//内容
	private String content;
	//费用
	private Double fee = 0.0;
	//备注
	private String comment;
	//关联的运维计划明细
	private RunmaintainPlanDetail runmaintainPlanDetail;

	@Override
	public int hashCode() {
		return getId().hashCode();
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) {return true;}
		if (!(o instanceof PricisionCheck)) {
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Double getFee() {
		return fee;
	}

	public void setFee(Double fee) {
		this.fee = fee;
	}

	public RunmaintainPlanDetail getRunmaintainPlanDetail() {
		return runmaintainPlanDetail;
	}

	public void setRunmaintainPlanDetail(RunmaintainPlanDetail runmaintainPlanDetail) {
		this.runmaintainPlanDetail = runmaintainPlanDetail;
	}

}
