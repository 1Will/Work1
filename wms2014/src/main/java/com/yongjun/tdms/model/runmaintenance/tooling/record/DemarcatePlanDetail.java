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
package com.yongjun.tdms.model.runmaintenance.tooling.record;

import java.util.Date;

import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.model.tracking.CreatedTimeTracking;
import com.yongjun.pluto.model.tracking.CreatorTracking;
import com.yongjun.pluto.model.tracking.LastModifiedTimeTracking;
import com.yongjun.pluto.model.tracking.LastOperatorTracking;
import com.yongjun.tdms.model.BaseInfoEntity;
import com.yongjun.tdms.model.asset.device.DeviceCard;

/**
 * @author qs
 * @version $Id: DemarcatePlanDetail.java 27787 2010-10-14 02:47:51Z zbzhang $
 */
public class DemarcatePlanDetail extends BaseInfoEntity implements
		CreatorTracking, CreatedTimeTracking, LastOperatorTracking,
		LastModifiedTimeTracking {
	private static final long serialVersionUID = -5895991727272503388L;
	private String demarcateResult;
	private Date thisDemarcateDateTm;
	private String comment;
	private User manager;
	private DeviceCard tooling;
	private DemarcatePlan demarcatePlan;
	
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public DemarcatePlan getDemarcatePlan() {
		return demarcatePlan;
	}
	public void setDemarcatePlan(DemarcatePlan demarcatePlan) {
		this.demarcatePlan = demarcatePlan;
	}
	public String getDemarcateResult() {
		return demarcateResult;
	}
	public void setDemarcateResult(String demarcateResult) {
		this.demarcateResult = demarcateResult;
	}
	public Date getThisDemarcateDateTm() {
		return thisDemarcateDateTm;
	}
	public void setThisDemarcateDateTm(Date thisDemarcateDateTm) {
		this.thisDemarcateDateTm = thisDemarcateDateTm;
	}
	public DeviceCard getTooling() {
		return tooling;
	}
	public void setTooling(DeviceCard tooling) {
		this.tooling = tooling;
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
	public User getManager() {
		return manager;
	}
	public void setManager(User manager) {
		this.manager = manager;
	}
}
