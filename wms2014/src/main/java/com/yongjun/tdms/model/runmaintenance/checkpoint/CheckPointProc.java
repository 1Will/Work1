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

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.model.tracking.CreatedTimeTracking;
import com.yongjun.pluto.model.tracking.CreatorTracking;
import com.yongjun.pluto.model.tracking.LastModifiedTimeTracking;
import com.yongjun.pluto.model.tracking.LastOperatorTracking;
import com.yongjun.pluto.model.tracking.OrganizationTracking;
import com.yongjun.tdms.model.BaseInfoEntity;
import com.yongjun.tdms.model.asset.device.DeviceCard;

/**
 * @author qs
 * @version $Id: CheckPointProc.java 27787 2010-10-14 02:47:51Z zbzhang $
 */
public class CheckPointProc extends BaseInfoEntity implements CreatorTracking,
		CreatedTimeTracking, LastOperatorTracking, LastModifiedTimeTracking, OrganizationTracking {
	private static final long serialVersionUID = 2514628149447702511L;
	private String procNo;
	private String name;
	private Date procExecTime;
	private String content;
	private String summary;
	private String comment;
	private CheckPointPlan plan;
	private String status;
	private String submitComment;
	private String approvers;
	private User manager;
	private DeviceCard device;
    private Set<CheckPointProcDetail> procDetails = new HashSet<CheckPointProcDetail>();

	public CheckPointProc() {
		
	}

	public User getManager() {
		return manager;
	}
	
	public void setManager(User manager) {
		this.manager = manager;
	}
	
	public String getApprovers() {
		return approvers;
	}
	
	public void setApprovers(String approvers) {
		this.approvers = approvers;
	}
	
	public String getSubmitComment() {
		return submitComment;
	}
	
	public void setSubmitComment(String submitComment) {
		this.submitComment = submitComment;
	}
	
	public DeviceCard getDevice() {
		return device;
	}

	public void setDevice(DeviceCard device) {
		this.device = device;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CheckPointPlan getPlan() {
		return plan;
	}

	public void setPlan(CheckPointPlan plan) {
		this.plan = plan;
	}

	public Date getProcExecTime() {
		return procExecTime;
	}

	public void setProcExecTime(Date procExecTime) {
		this.procExecTime = procExecTime;
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

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getProcNo() {
		return procNo;
	}

	public void setProcNo(String procNo) {
		this.procNo = procNo;
	}

	public Set<CheckPointProcDetail> getProcDetails() {
		return this.procDetails;
	}
	
	public void setProcDetails(Set<CheckPointProcDetail> procDetails) {
		this.procDetails = procDetails;
	}
	
	@Override
	public int hashCode() {
		return procNo.hashCode();
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof CheckPointProc)) {
			return false;
		}
		CheckPointProc proc = (CheckPointProc)o;
		if (getId().equals(proc.getId())) {
			return true;
		}
		return false;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
