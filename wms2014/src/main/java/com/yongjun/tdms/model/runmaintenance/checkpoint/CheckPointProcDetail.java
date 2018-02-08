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
import com.yongjun.tdms.model.BaseInfoEntity;
import com.yongjun.tdms.model.runmaintenance.checkresult.CheckResult;

/**
 * @author qs
 * @version $Id: CheckPointProcDetail.java 27787 2010-10-14 02:47:51Z zbzhang $
 */
public class CheckPointProcDetail extends BaseInfoEntity implements
		CreatorTracking, CreatedTimeTracking, LastOperatorTracking,
		LastModifiedTimeTracking {
	private static final long serialVersionUID = 351361809808574016L;
	private CheckResult checkResult;
	private double fee;
	private String comment;
	private String exceptionDescription;
	private CheckPointProc proc;
	private CheckPointPlanDetail planDetail;

	public CheckPointProcDetail() {
		
	}
	
	public CheckResult getCheckResult() {
		return checkResult;
	}

	public void setCheckResult(CheckResult checkResult) {
		this.checkResult = checkResult;
	}

	public CheckPointProc getProc() {
		return proc;
	}

	public void setProc(CheckPointProc proc) {
		this.proc = proc;
	}

	@Override
	public int hashCode() {
		return getId().hashCode();
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) { return true; }
		if (!(o instanceof CheckPointProcDetail)) { return false; }
		return true;
	}
	
	public CheckPointPlanDetail getPlanDetail() {
		return planDetail;
	}

	public void setPlanDetail(CheckPointPlanDetail planDetail) {
		this.planDetail = planDetail;
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

	public String getExceptionDescription() {
		return exceptionDescription;
	}

	public void setExceptionDescription(String exceptionDescription) {
		this.exceptionDescription = exceptionDescription;
	}
}
